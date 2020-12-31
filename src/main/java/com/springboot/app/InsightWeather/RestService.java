package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Collections;

@Service
public class RestService {
    private final RestTemplate restTemplate;
    private final String apiKey = System.getenv().get("API_KEY");

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }

    public String getSols() {
        String url = String.format(
                "https://api.nasa.gov/insight_weather/?api_key=%s&feedtype=json&ver=1.0",
                this.apiKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity request = new HttpEntity(headers);
        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = null;
        try {
            ResponseEntity<Resource> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, request, Resource.class);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                System.out.println("Sols request errors: " + responseEntity.getStatusCode().getReasonPhrase());
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode errorObj = mapper.createObjectNode();
                errorObj.put("status", String.valueOf(responseEntity.getStatusCode()));
                errorObj.put("error", responseEntity.getStatusCode().getReasonPhrase());
                return errorObj.toString();
            }
            in = responseEntity.getBody().getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((in)));
            if (bufferedReader != null) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
            in.close();
        } catch(Exception e) {
            throw new RuntimeException("Exception while calling url: " + url, e);
        }

        return stringBuilder.toString();
    }

}
