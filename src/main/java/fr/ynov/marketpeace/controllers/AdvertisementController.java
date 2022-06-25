package fr.ynov.marketpeace.controllers;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.services.AdvertisementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for advertisements
 * Need authorized JWT token to all entry point, excepted for "/" to show
 * advertisements even when user is not logged in
 */
@RestController
@RequestMapping(path = "/api/advertisements")
@SecurityRequirement(name = "bearerAuth")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    /**
     * Advertisement controller constructor
     * @param advertisementService Service used to manipulate advertisements
     */
    @Autowired
    AdvertisementController(AdvertisementService advertisementService){
        this.advertisementService = advertisementService;
    }

    /**
     * Find all advertisements
     * @return all advertisements from database
     */
    @Operation(description = "Return all advertisements in database")
    @GetMapping
    public Iterable<Advertisement> findAll(){
        return advertisementService.findAll();
    }

    /**
     * Find specific advertisement with id
     * @param id Desired advertisement's id
     * @return Return corresponding Advertisement or throw AdNotFoundException if there is no advertisement with the given id
     * {@link fr.ynov.marketpeace.exceptions.AdNotFoundException}
     */
    @Operation(description = "Find advertisement associated to given id")
    @SecurityRequirement(name = "bearer")
    @GetMapping("/{id}")
    public Advertisement findAdById(@NonNull @PathVariable Long id) {
        return advertisementService.findAdById(id);
    }

    /**
     * Update existing advertisement
     * Throw AdNotFoundException if there is no corresponding ad in database
     * @param ad New advertisement to save
     * {@link fr.ynov.marketpeace.exceptions.AdNotFoundException}
     */
    @Operation(description = "Save new advertisement or update existing one")
    @SecurityRequirement(name = "bearer")
    @PutMapping
    public void save(@RequestBody Advertisement ad){
        advertisementService.save(ad);
    }

    /**
     * Delete advertisement corresponding to this id
     * Throw AdNotFoundException if there is no corresponding Advertisement
     * {@link fr.ynov.marketpeace.exceptions.AdNotFoundException}
     * @param id Advertisement's id to delete
     */
    @Operation(description = "Delete advertisement associated to given id")
    @SecurityRequirement(name = "bearer")
    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable Long id){
        advertisementService.deleteById(id);
    }
}
