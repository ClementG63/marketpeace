package fr.ynov.marketpeace.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Message response for API response
 */
@Data
@Getter
@Setter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}