package fr.ynov.marketpeace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found exception. Throw if a User is not found with id
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")
public class UserNotFoundException extends RuntimeException {
    /**
     * Constructor UserNotFoundException
     * @param id User id searched
     */
    public UserNotFoundException(String id) {
        super("No customer found with parameter: " + id);
    }
}
