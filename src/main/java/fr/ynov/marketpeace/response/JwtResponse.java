package fr.ynov.marketpeace.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * JWT Response
 */
@Data
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    /**
     * All args constructor
     * @param accessToken JWT Token
     * @param id User's id
     * @param username User's username
     * @param email User's mail address
     * @param roles User's roles
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}