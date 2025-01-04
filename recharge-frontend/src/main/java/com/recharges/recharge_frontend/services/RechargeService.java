package com.recharges.recharge_frontend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recharges.recharge_frontend.dto.RechargeRequest;
import org.glassfish.jersey.client.ClientConfig;

import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RechargeService {

    private static final String BACKEND_URL = "http://localhost:8080/api/recharges";
    private static final Logger LOGGER = Logger.getLogger(RechargeService.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Client client;

    public RechargeService() {
        ClientConfig config = new ClientConfig();
        this.client = ClientBuilder.newBuilder().withConfig(config).build();
    }

    public void createRecharge(String operator, String phoneNumber, double amount) throws Exception {
        RechargeRequest request = new RechargeRequest(operator, phoneNumber, amount);
        String jsonRequest = objectMapper.writeValueAsString(request);

        String token = getToken();
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("Token no encontrado en la sesión, cierra sesión e inicia nuevamente.");
        }

        try (Response response = client.target(BACKEND_URL)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .post(Entity.json(jsonRequest))) {

            if (response.getStatus() != 200) {
                String errorMessage = response.readEntity(String.class);
                LOGGER.log(Level.SEVERE, "Error en la recarga: {0}", errorMessage);
                throw new Exception("Error en la recarga: " + errorMessage);
            }

            LOGGER.info("Recarga realizada con éxito.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al realizar la recarga", e);
            throw e;
        }
    }

    private String getToken() {
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
