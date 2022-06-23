package fr.ynov.marketpeace.services;

import java.util.List;
import java.util.Optional;
import fr.ynov.marketpeace.entities.User;
import fr.ynov.marketpeace.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import fr.ynov.marketpeace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User service
 */
@Service
public class UserService {
    private final UserRepository repository;

    /**
     * UserService constructor
     * @param repository User repository
     */
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Create user or update existing one
     * @param u User to create or update
     * @return return the created/updated user
     */
    public User save(User u) {
        return repository.save(u);
    }

    /**
     * Find all users in database
     * @return list of users in database
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Find user by id
     * @param id User's id to find
     * @return Corresponding user to given id
     * @throws UserNotFoundException if no user associated to the given id
     */
    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> result = repository.findById(id);
        if(!result.isPresent()){
            throw new UserNotFoundException("User not found with id: "+id);
        } else {
            return result.get();
        }
    }

    /**
     * Check if user exists with given ID
     * @param id User's id
     * @return true if existing, false if not
     */
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    /**
     * Delete user corresponding with given id
     * @param id user's id
     */
    public void delete(Long id) {
        if(findUserById(id) != null){
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id: "+id);
        }
    }
}
