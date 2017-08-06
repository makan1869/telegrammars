package ir.serenade.telegrammars.repository;

import ir.serenade.telegrammars.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by serenade on 8/1/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);

}
