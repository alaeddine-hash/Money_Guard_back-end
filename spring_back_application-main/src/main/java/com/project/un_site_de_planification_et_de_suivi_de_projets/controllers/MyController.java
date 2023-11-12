package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.project.un_site_de_planification_et_de_suivi_de_projets.services.FlaskApiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {
    private final FlaskApiService flaskApiService;
    private final String flaskApiUrl = "http://localhost:5000/predict";  // Update with your Flask API URL

    private final RestTemplate restTemplate;


    public MyController(FlaskApiService flaskApiService, RestTemplate restTemplate) {
        this.flaskApiService = flaskApiService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/invokeFlaskApi")
    public String invokeFlaskApi() {
        return flaskApiService.callFlaskApi();
    }

    @PostMapping("/api/invokeFlaskApi")
    public String invokeFlaskApi(@RequestBody String description) {
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with the description
        HttpEntity<String> requestEntity = new HttpEntity<>(description, headers);

        // Send the POST request to the Flask API
        ResponseEntity<String> response = restTemplate.postForEntity(flaskApiUrl, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return "Error: " + response.getStatusCodeValue();
        }
    }
}
