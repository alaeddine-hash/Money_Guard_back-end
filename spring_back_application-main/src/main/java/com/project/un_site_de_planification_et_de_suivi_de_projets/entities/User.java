package com.project.un_site_de_planification_et_de_suivi_de_projets.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@AllArgsConstructor
public  class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;

    // @Column(nullable = false)
    private String lastname ;

    //   @Column(nullable = false)
    private LocalDate birthday ;

    //  @Column(nullable = false)
    private String phone ;
    private String email ;

    //  @Column(nullable = false)
    private String username ;

    //  @Column(nullable = false)
    private String password ;

    @OneToOne
    Image image;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Message> getMessage_one() {
        return message_one;
    }

    public void setMessage_one(Set<Message> message_one) {
        this.message_one = message_one;
    }

    public Set<Message> getMessage_two() {
        return message_two;
    }

    public void setMessage_two(Set<Message> message_two) {
        this.message_two = message_two;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy="sender")
    private Set<Message> message_one;

    @OneToMany(mappedBy="recipient")
    private Set<Message> message_two;

    @OneToMany(mappedBy="client")
    private Set<Favorite> favorites;

    @OneToMany(mappedBy="client")
    private Set<Rating> clientRatings;

    @OneToMany(mappedBy="provider")
    private Set<Rating> providerRatings;

    @OneToMany(mappedBy="client")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy="provider")
    private Set<Solution> solutions;


    public User(String username, String name, String lastname, LocalDate birthday, String phone, String email, String password ) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.password = password;}


    public User() {

    }

    public Set<Role> getRoles() {
        return roles;
    }
}
