package com.recharges.recharge_frontend.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.recharges.recharge_frontend.services.AuthService;

@Named("loginBean")
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    private String token;
    private final AuthService authService = new AuthService();

    public String login() {
        try {
            token = authService.authenticate(username, password);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("token", token);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
            return "dashboard?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {

            facesContext.getExternalContext().invalidateSession();

            return "login?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cerrar sesión."));
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
