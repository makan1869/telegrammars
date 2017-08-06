package ir.serenade.telegrammars.service.impl;

import ir.serenade.telegrammars.domain.Role;
import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.repository.RoleRepository;
import ir.serenade.telegrammars.repository.UserRepository;
import ir.serenade.telegrammars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * Created by serenade on 8/1/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private HttpSession httpSession;

    public final String CURRENT_USER_KEY = "CURRENT_USER";


    /*
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }
    */

    @Override
    public User save(User user) {
        if(user.isPasswordReset() && user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public User getLoggedInUser(Boolean forceFresh) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) httpSession.getAttribute(CURRENT_USER_KEY);
        if(forceFresh || httpSession.getAttribute(CURRENT_USER_KEY) == null) {
            user = userRepository.findByUsername(userName);
            httpSession.setAttribute(CURRENT_USER_KEY, user);
        }
        return user;
    }

}
