package fr.ynov.marketpeace.services;

import java.util.List;
import java.util.Optional;
import fr.ynov.marketpeace.entities.User;
import org.springframework.stereotype.Service;
import fr.ynov.marketpeace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public record UserService(UserRepository repository) {
    @Autowired
    public UserService {}

    public User save(User u) {
        return repository.save(u);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
