package fr.ynov.marketpeace.services;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.exceptions.AdNotFoundException;
import fr.ynov.marketpeace.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public record AdvertisementService(AdvertisementRepository repository) {
    @Autowired
    public AdvertisementService {}

    public Iterable<Advertisement> findAll() {
        return repository.findAll();
    }

    public Advertisement findAdById(Long id) throws AdNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new AdNotFoundException(id.toString())
        );
    }

    public Advertisement save(Advertisement u) {
        return repository.save(u);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
