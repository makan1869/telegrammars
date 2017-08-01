package ir.serenade.telegrammars.service;

import ir.serenade.telegrammars.domain.User;

/**
 * Created by serenade on 8/1/17.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
