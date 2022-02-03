package com.bouaziz.saraha.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthentificationResponse {
    private String token;
}
