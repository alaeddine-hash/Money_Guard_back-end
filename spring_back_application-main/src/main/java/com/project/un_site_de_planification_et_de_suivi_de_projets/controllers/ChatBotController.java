package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request.ChatBotRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Value("${flask.microservice.url}")
    private String flaskMicroserviceUrl;  // Set this in your application.properties or application.yml

    @PostMapping("/generate")
    @ResponseBody
    public ResponseEntity<String> generate(@RequestBody ChatBotRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ChatBotRequest> requestBody = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                flaskMicroserviceUrl + "/generate",
                HttpMethod.POST,
                requestBody,
                String.class  // Change here
        );

        String response = responseEntity.getBody();
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error communicating with the Flask microservice.");
        }
    }
}
