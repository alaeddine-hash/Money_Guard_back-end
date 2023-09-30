package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.un_site_de_planification_et_de_suivi_de_projets.config.FileUploadUtil;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.*;
import com.project.un_site_de_planification_et_de_suivi_de_projets.repos.RoleRepository;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.ImageService;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.NotificationService;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.project.un_site_de_planification_et_de_suivi_de_projets.entities.ERole.ROLE_USER;


@RestController
@RequestMapping("/api/users")
public class UserController {


   private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    private ImageService imageService;


     private  final  NotificationService notificationService;
    @Autowired
    public UserController(UserService userService, NotificationService notificationService, ImageService imageService, RoleRepository roleRepository) {

        this.userService = userService;
        this.imageService = imageService ;
        this.roleRepository = roleRepository;
        this.notificationService = notificationService;

    }

    @PostMapping("/add")
    @ResponseBody
    public User add_New_User(@RequestBody User user) {
        return userService.addUser(user);
    }

    @JsonIgnore
    @GetMapping("/all")
    @ResponseBody
    public List<User> all_users(){
        return userService.findAllUsers();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public User user_id(@PathVariable("id") long id){
        return userService.findUserById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void updateUser(@RequestBody User user){
        userService.updateUser(user); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void supp_user(@PathVariable("id") long id){
        userService.deleteUser(id); }

    @PostMapping("/{sid}/add-image")
    public void addImage(@PathVariable Long sid, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        User user = userService.findUserById(sid);

        userService.addUser(user);
        if (!multipartFile.isEmpty()) {
            String orgFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
            String uploadDir = "users-photos/";
            String fileName = "user-" + user.getId() + ext;
            Image img = new Image(multipartFile, fileName, ext, multipartFile.getBytes());
            Image image = imageService.addImageLa(img);
            user.setImage(image);
            FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
            userService.updateUser(user);
        }
    }



    @RequestMapping(value = "/{sid}/display-image")
    public void getUserPhoto(HttpServletResponse response, @PathVariable("sid") long sid) throws Exception {
        User user = userService.findUserById(sid);
        Image image = user.getImage();

        if(image != null) {
            response.setContentType(image.getFileType());
            InputStream inputStream = new ByteArrayInputStream(image.getData());
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }


}
