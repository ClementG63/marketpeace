package fr.ynov.marketpeace.repositories;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.exceptions.AdNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
    @Override
    Optional<Advertisement> findById(Long aLong) throws AdNotFoundException;

    @Override
    void deleteById(Long aLong) throws AdNotFoundException;
}
