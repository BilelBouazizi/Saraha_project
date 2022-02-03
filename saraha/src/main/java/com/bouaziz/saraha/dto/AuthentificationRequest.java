package com.bouaziz.saraha.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthentificationRequest {
    private String email;
    private String password;
}
