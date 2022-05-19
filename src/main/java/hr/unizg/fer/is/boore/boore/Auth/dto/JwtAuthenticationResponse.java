package hr.unizg.fer.is.boore.boore.Auth.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class JwtAuthenticationResponse {

    public JwtAuthenticationResponse(String token, String name){
        this.accessToken = token;
        this.name = name;
    }

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";
    private String name;
}
