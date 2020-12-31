package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class RestEndpointController {

    @GetMapping("/api/sols/full")
    public JsonNode apiFull(String name) throws IOException {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestService restService = new RestService(restTemplateBuilder);
        ObjectMapper objectMapper = new ObjectMapper();
        // JsonNode payload = objectMapper.readTree(new FileInputStream("/Users/jkey3/IdeaProjects/InSight/src/main/java/com/springboot/app/InsightWeather/ExampleJSON.json"));
        JsonNode payload = objectMapper.readTree(restService.getSols());
        return payload;
    }

    @GetMapping("/api/sols/summary")
    public Sol[] apiSummary(String name) throws IOException {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestService restService = new RestService(restTemplateBuilder);
        ObjectMapper objectMapper = new ObjectMapper();
        // JsonNode payload = objectMapper.readTree(new FileInputStream("/Users/jkey3/IdeaProjects/InSight/src/main/java/com/springboot/app/InsightWeather/ExampleJSON.json"));
        JsonNode payload = objectMapper.readTree(restService.getSols());
        SolsArray solsArray = new SolsArray(payload);
        return solsArray.getSols();
    }



}
