package fr.ynov.marketpeace.repositories;

import java.util.List;
import java.util.Optional;
import fr.ynov.marketpeace.entities.User;
import fr.ynov.marketpeace.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findUserByUsername(String username) throws UserNotFoundException;
    @Override
    Optional<User> findById(Long id) throws UserNotFoundException;
    @Override
    void deleteById(Long id);
    @Override
    boolean existsById(Long aLong);
    boolean existsByUsername(String username);
    boolean existsByMailAddress(String mailAddress);
}
