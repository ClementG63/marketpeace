package fr.ynov.marketpeace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advertisement not found exception. Throw if an Ad is not found with id
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Ad not found")
public class AdNotFoundException extends RuntimeException {
    /**
     * Constructor AdNotFoundException
     * @param id Advertisement id searched
     */
    public AdNotFoundException(String id){
        super("Ad not found with id: "+id);
    }
}
