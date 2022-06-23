package fr.ynov.marketpeace.repositories;

import fr.ynov.marketpeace.entities.Advertisement;
import fr.ynov.marketpeace.exceptions.AdNotFoundException;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Advertisement repository
 */
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
    /**
     * Find advertisement by id
     * @param aLong advertisement's id
     * @return Advertisement found with given id or Null if not found
     */
    @Override
    Optional<Advertisement> findById(Long aLong);

    @Override
    void deleteById(Long aLong) throws AdNotFoundException;
}
