package hr.unizg.fer.is.boore.boore.Auth.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class JwtAuthenticationResponse {

    public JwtAuthenticationResponse(String token, String name, String role, Integer id){
        this.accessToken = token;
        this.name = name;
        this.role = role;
        this.id = id;
    }

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";
    private String name;
    private String role;
    private Integer id;
}
