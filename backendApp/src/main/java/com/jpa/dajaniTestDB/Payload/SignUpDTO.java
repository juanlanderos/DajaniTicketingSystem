package com.jpa.dajaniTestDB.Payload;

import lombok.Data;

@Data
public class SignUpDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
