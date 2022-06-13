package fr.ynov.marketpeace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Ad not found")
public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException(String id){
        super("Ad not found with id: "+id);
    }
}
