package ir.serenade.telegrammars.service;

import ir.serenade.telegrammars.domain.Role;
import ir.serenade.telegrammars.domain.User;

/**
 * Created by serenade on 8/1/17.
 */
public interface UserService {

    User save(User user);

    User findById(Long id);

    User findByUsername(String username);

    User findByChatId(Long chatId);

    Role findRoleByName(String name);

    User getLoggedInUser(Boolean forceFresh);
}
