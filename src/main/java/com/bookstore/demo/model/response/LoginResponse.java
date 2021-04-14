package com.bookstore.demo.model.response;

//This class will send back the JWD token
//if the username and password is correct
public class LoginResponse {
    private String JWT;

    public LoginResponse(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }
}
