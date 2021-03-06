package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@RequestParam(name="home", required=false, defaultValue="") String name, Model model) throws IOException {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestService restService = new RestService(restTemplateBuilder);
        ObjectMapper objectMapper = new ObjectMapper();
        // JsonNode payload = objectMapper.readTree(restService.getSols());
        JsonNode payload = objectMapper.readTree(getClass().getClassLoader().getResourceAsStream("ExampleJSON.json"));
        try {
            SolsArray solsArray = new SolsArray(payload);
            model.addAttribute("solsCalendar", solsArray.getSols());
            model.addAttribute("mostRecentSol", solsArray.getMostRecentSol());
            model.addAttribute("solsTable", solsArray.getSolsReversed());
            return "home";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

}