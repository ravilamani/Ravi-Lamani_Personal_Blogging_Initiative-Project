package com.blog.Payload;

public class AuthResponse {
    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter for jwt

    public String getJwt() {
        return jwt;
    }
}

