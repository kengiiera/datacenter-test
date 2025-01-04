package com.recharges.recharge_frontend.services;

import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recharges.recharge_frontend.beans.StatisticsBean.StatisticsResponse;

public class StatisticsService {
    private static final String BACKEND_URL = "http://localhost:8080/api/recharges/stats";

    private final ObjectMapper objectMapper;
    private final Client client;

    public StatisticsService() {
        ClientConfig config = new ClientConfig();
        this.client = ClientBuilder.newBuilder().withConfig(config).build();
        this.objectMapper = new ObjectMapper();
    }

    public StatisticsResponse getStatistics() throws Exception {
        String token = extractToken();
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("Token no encontrado en la sesión, cierra sesión e inicia nuevamente.");
        }

        WebTarget target = client.target(BACKEND_URL);
        String response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .get(String.class);

        return objectMapper.readValue(response, StatisticsResponse.class);
    }


    private String extractToken() {
        Object tokenObject = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("token");

                if (tokenObject instanceof String) {
                    String tokenJson = (String) tokenObject;
                    int start = tokenJson.indexOf(":\"") + 2;
                    int end = tokenJson.lastIndexOf("\"");
                    return tokenJson.substring(start, end);
                }

        return null;
    }
}
