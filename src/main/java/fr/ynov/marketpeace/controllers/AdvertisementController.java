package fr.ynov.marketpeace.controllers;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.services.AdvertisementService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/advertisements")
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

    @PutMapping
    public void saveAd(@RequestBody Advertisement ad){
        advertisementService.save(ad);
    }

    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable Long id){
        advertisementService.deleteById(id);
    }
}
