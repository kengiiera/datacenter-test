package com.recharges.recharge_frontend.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class AuthService {
    private static final String BACKEND_URL = "http://localhost:8080/api/auth/login";

    public String authenticate(String username, String password) throws Exception {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newBuilder().withConfig(config).build();
            String json = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);
                Response response = client.target(BACKEND_URL)
                                  .request(MediaType.APPLICATION_JSON)
                                  .post(Entity.json(json));
    
     
        if (response.getStatus() != 200) {
            throw new Exception("Credenciales inválidas");
        }

        return response.readEntity(String.class);
    }
}
