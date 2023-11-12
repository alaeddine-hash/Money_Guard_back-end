package com.project.un_site_de_planification_et_de_suivi_de_projets.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskApiService {
    @Value("${flask.api.url}") // Define this property in your application.properties or application.yml
    private String flaskApiUrl;



    private final RestTemplate restTemplate;

    public FlaskApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callFlaskApi() {
        ResponseEntity<String> response = restTemplate.getForEntity(flaskApiUrl, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null; // Handle error cases as needed
    }

}
