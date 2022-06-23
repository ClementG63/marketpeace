package fr.ynov.marketpeace.services;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.exceptions.AdNotFoundException;
import fr.ynov.marketpeace.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Advertisements service
 */
@Service
public class AdvertisementService {
    private final AdvertisementRepository repository;

    /**
     * Advertisements service constructor
     * @param repository Advertisements repository
     */
    @Autowired
    public AdvertisementService(AdvertisementRepository repository) {
        this.repository = repository;
    }

    /**
     * Find all advertisements in database
     * @return List of advertisements
     */
    public Iterable<Advertisement> findAll() {
        return repository.findAll();
    }

    /**
     * Find advertisement by id
     * @param id Advertisement's id
     * @return Corresponding advertisements
     * @throws AdNotFoundException if no advertisement found with given id
     */
    public Advertisement findAdById(Long id) throws AdNotFoundException {
        Optional<Advertisement> result = repository.findById(id);
        if(!result.isPresent()){
            throw new AdNotFoundException("Advertisement not found with id: "+id);
        }
        return result.get();
    }

    /**
     * Create advertisement or update existing one
     * @param u Advertisement to create or update
     */
    public void save(Advertisement u) {
        repository.save(u);
    }

    /**
     * Delete advertisement corresponding to given id
     * @param id Advertisement's id to delete
     * @throws AdNotFoundException if no advertisement found with given id
     */
    public void deleteById(Long id) throws AdNotFoundException{
        if(findAdById(id) != null){
            repository.deleteById(id);
        } else {
            throw new AdNotFoundException("Advertisement not found with id: "+id);
        }
    }
}
