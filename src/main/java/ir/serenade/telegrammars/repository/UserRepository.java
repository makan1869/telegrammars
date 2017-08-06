package ir.serenade.telegrammars.repository;

import ir.serenade.telegrammars.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by serenade on 8/1/17.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User findByChatId(Long chatId);
}
