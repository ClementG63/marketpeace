package fr.ynov.marketpeace.services;

import fr.ynov.marketpeace.entities.User;
import fr.ynov.marketpeace.entities.UserDetailsImpl;
import fr.ynov.marketpeace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * User detail service (Spring) implementation
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * User detail service implementation
     * @param userRepository user repository
     */
    @Autowired
    UserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Load user by username
     * @param username User's username
     * @return User details corresponding to given username
     * @throws UsernameNotFoundException thrown if no user corresponding to given username
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        } else {
            return UserDetailsImpl.build(user.get());
        }
    }
}
