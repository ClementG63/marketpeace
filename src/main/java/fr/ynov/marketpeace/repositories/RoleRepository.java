package fr.ynov.marketpeace.repositories;

import fr.ynov.marketpeace.entities.ERole;
import fr.ynov.marketpeace.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Roles repository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find role by name
     * @param name Role name
     * @return Return role if corresponding role or null if no role associated with name
     */
    Optional<Role> findByName(ERole name);
}
