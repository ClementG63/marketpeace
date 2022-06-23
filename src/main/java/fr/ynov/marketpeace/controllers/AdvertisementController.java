package fr.ynov.marketpeace.controllers;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.services.AdvertisementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/advertisements")
@SecurityRequirement(name = "bearerAuth")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @Autowired
    AdvertisementController(AdvertisementService advertisementService){
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public Iterable<Advertisement> findAll(){
        return advertisementService.findAll();
    }

    @GetMapping("/{id}")
    public Advertisement findAdById(@NonNull @PathVariable Long id) {
        return advertisementService.findAdById(id);
    }

    @PostMapping
    public void save(@RequestBody Advertisement ad){
        advertisementService.save(ad);
    }

    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable Long id){
        advertisementService.deleteById(id);
    }
}
