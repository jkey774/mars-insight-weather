package com.springboot.app.InsightWeather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

public class SolsArray {

    private ObjectMapper objectMapper;

    private Sol[] sols;

    public SolsArray(JsonNode payload) {
        this.objectMapper = new ObjectMapper();
        this.sols = buildSols(payload);
    }

    public Sol[] getSols() {
        return sols;
    }

    public Sol getMostRecentSol() {
        return sols[sols.length - 1];
    }

    public Sol[] getSolsReversed() {
        Sol[] reversed = new Sol[sols.length];

        int j = sols.length;

        for (int i = 0; i < sols.length; i++) {
            reversed[j - 1] = sols[i];
            j -= 1;
        }

        return reversed;
    }

    private Sol[] buildSols(JsonNode payload) {
        JsonNode solIdsNode = payload.get("sol_keys");
        Iterator<JsonNode> solIds = solIdsNode.elements();
        ArrayNode solsArrayNode = objectMapper.createArrayNode();

        while (solIds.hasNext()) {
            String id = solIds.next().asText();
            if (id == null)
                continue;
            ObjectNode sol = objectMapper.convertValue(payload.path(id), ObjectNode.class);
            if (sol == null)
                continue;
            sol.put("id", id);
            solsArrayNode.add(sol);
        }

        return objectMapper.convertValue(solsArrayNode, Sol[].class);
    }

}
