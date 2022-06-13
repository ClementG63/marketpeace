package fr.ynov.marketpeace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String parameter) {
        super("No customer found with parameter: " + parameter);
    }
}
