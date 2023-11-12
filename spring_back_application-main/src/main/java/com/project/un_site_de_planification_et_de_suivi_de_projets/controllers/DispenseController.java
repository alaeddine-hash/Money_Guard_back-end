package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.un_site_de_planification_et_de_suivi_de_projets.config.FileUploadUtil;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Category;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Dispense;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Image;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.User;
import com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request.DescriptionDTO;
import com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request.DispenseDTO;
import com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request.DispensesStatisticsDTO;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.CategoryService;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.DispenseService;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.ImageService;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dispenses")
public class DispenseController {
    private final DispenseService dispenseService;

    private final CategoryService categoryService;


    private final String flaskApiUrl = "http://localhost:5000/predict";  // Update with your Flask API URL

    private final RestTemplate restTemplate;
    private final UserService userService;

    @Autowired
    public DispenseController(DispenseService dispenseService, CategoryService categoryService, RestTemplate restTemplate, UserService userService) {
        this.dispenseService = dispenseService;
        this.categoryService = categoryService;
        this.restTemplate = restTemplate;
        this.userService = userService;
    }
    @PostMapping("/add")
    @ResponseBody
    public Dispense addNewDispense(@RequestBody DispenseDTO dispenseDTO) throws JsonProcessingException {
        Dispense dispense = new Dispense(dispenseDTO.getId(),dispenseDTO.getAmount(), dispenseDTO.getDate(), dispenseDTO.getDescription(),
                                dispenseDTO.getPaymentMethod());
        dispense.setUser(userService.findUserById(dispenseDTO.getProviderId()));

        String categorieName = "";

        String description = dispenseDTO.getDescription();

        // Create the request entity with the description
        // Create a DescriptionDTO instance with the desired description
        DescriptionDTO descriptionDTO = new DescriptionDTO(dispenseDTO.getDescription());

        // Use Jackson to serialize the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(descriptionDTO);

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

// Create the request entity with the JSON data
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
       // Send the POST request to the Flask API
        ResponseEntity<String> response = restTemplate.postForEntity(flaskApiUrl, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
             categorieName = response.getBody();
        } else {
            categorieName = "Error: " + response.getStatusCodeValue();
        }

        Category categorie = categoryService.getCategorieByName(categorieName);
        if (categorie == null){
            categorie = new Category(categorieName, "no descreption yet");
            categorie = categoryService.addCategory(categorie);
        }
        dispense.setCategory(categorie);


        return dispenseService.addDispense(dispense);
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Dispense> getAllDispenses(){
        return dispenseService.findAllDispenses();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Dispense getDispenseById(@PathVariable("id") long id){
        return dispenseService.findDispenseById(id);
    }

    @GetMapping("/id/{id}/userId")
    @ResponseBody
    public List<Dispense> getDispenseByUserId(@PathVariable("id") long id){
        User user = userService.findUserById(id);
        List<Dispense> dispenses = (List<Dispense>) user.getDispenses();
        return dispenses;
    }


    @PutMapping("/update")
    @ResponseBody
    public void updateDispense(@RequestBody Dispense dispense){dispenseService.updateDispense(dispense); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteDispense(@PathVariable("id") long id){dispenseService.deleteDispense(id); }

    @PostMapping("/add/{sId}")
    @ResponseBody
    public ResponseEntity<?> addNewDispense(@RequestBody Dispense dispense, @PathVariable long sId) {
        Dispense dispenseobj = new Dispense();
        User user = userService.findUserById(sId);
        if (user == null){
            return ResponseEntity.badRequest().body("user not found");
        }
        List<Dispense> dispenses;
        dispenses = (List<Dispense>) user.getDispenses();
        dispenses.add(dispense);
        user.setDispenses((List<Dispense>) dispenses);
        userService.updateUser(user);
        dispenseobj.setUser(user);
        dispenseService.updateDispense(dispenseobj);
        return ResponseEntity.ok(dispenseobj);
    }


    @GetMapping("/statistics/{userId}")
    public List<DispensesStatisticsDTO> getExpenseStatistics(@PathVariable("userId") long userId) {
        List<DispensesStatisticsDTO> statistics = dispenseService.calculateDispensesStatistics(userId);
        return statistics;
    }



}
