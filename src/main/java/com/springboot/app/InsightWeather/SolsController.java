package com.springboot.app.InsightWeather;

// import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Controller
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolsController {

    @GetMapping("/")
    public String sols(@RequestParam(name="sols", required=false, defaultValue="") String name, Model model) throws IOException {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestService restService = new RestService(restTemplateBuilder);
        ObjectMapper objectMapper = new ObjectMapper();

        // JsonNode rootNode = objectMapper.readTree(new FileInputStream("/Users/jkey3/IdeaProjects/InSight/src/main/java/com/springboot/app/InsightWeather/ExampleJSON.json"));
        JsonNode rootNode = objectMapper.readTree(restService.getSols());

        JsonNode solKeyNodes = rootNode.get("sol_keys");
        Iterator<JsonNode> solKeys = solKeyNodes.elements();

        ArrayNode solsData = objectMapper.createArrayNode();
        ObjectNode lastSolNode = null;

        int i = 0;
        while (solKeys.hasNext()) {
            String solKey = solKeys.next().asText();
            ObjectNode sol = objectMapper.convertValue(rootNode.path(solKey), ObjectNode.class);
            sol.put("id", solKey);
            solsData.add(sol);
            if (i == solKeyNodes.size() - 1) {
                lastSolNode = sol;
            }
            i++;
        }

        Sol mostRecentSol = objectMapper.convertValue(lastSolNode, Sol.class);
        model.addAttribute("mostRecentSol", mostRecentSol);

        Sol[] sols = objectMapper.convertValue(solsData, Sol[].class);
        model.addAttribute("solsCalendar", sols);
        model.addAttribute("solsTable", reverseSols(sols, sols.length));

        return "sols";
    }

    private static Sol[] reverseSols(Sol[] original, int length) {
        Sol[] reversed = new Sol[length];

        int j = length;

        for (int i = 0; i < length; i++) {
            reversed[j - 1] = original[i];
            j -= 1;
        }

        return reversed;
    }

}