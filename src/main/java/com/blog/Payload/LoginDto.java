package com.blog.Payload;


import lombok.*;


@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;


}
