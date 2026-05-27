package com.SerratecFlix.trabalhoApi.Security;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String senha;
}
