package com.jukusoft.browsergame.controller.user;

public class RegistrationRequest {

    private String registerPassword;
    private String prename;
    private String lastname;
    private String username;
    private String password;
    private String mail;

    public RegistrationRequest(String registerPassword, String prename, String lastname, String username, String password, String mail) {
        this.registerPassword = registerPassword;
        this.prename = prename;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public String getPrename() {
        return prename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

}
