package fr.ynov.marketpeace.repositories;

import java.util.List;
import java.util.Optional;
import fr.ynov.marketpeace.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * User repository
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Find all user in database
     * @return list of users
     */
    List<User> findAll();

    /**
     * Find user corresponding with given username
     * @param username User's username
     * @return User if corresponding user or null if no user found
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Find user by id
     * @param id User's id
     * @return User if corresponding user or null if no user found
     */
    @Override
    Optional<User> findById(Long id);

    /**
     * Delete user by id
     * @param id User's id
     */
    @Override
    void deleteById(Long id);

    /**
     * Check if user exists with given id
     * @param aLong user's id
     * @return true if exists, false if not
     */
    @Override
    boolean existsById(Long aLong);

    /**
     * Check if user exists with given username
     * @param username User's username
     * @return true if exists, false if not
     */
    boolean existsByUsername(String username);

    /**
     * Check if user exists with given mailAddress
     * @param mailAddress User's username
     * @return true if exists, false if not
     */
    boolean existsByMailAddress(String mailAddress);
}
