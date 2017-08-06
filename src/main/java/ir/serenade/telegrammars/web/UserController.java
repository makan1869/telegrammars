package ir.serenade.telegrammars.web;

import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.domain.validator.UserValidator;
import ir.serenade.telegrammars.service.SecurityService;
import ir.serenade.telegrammars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * Created by serenade on 8/1/17.
 */
@Controller
public class UserController {


    @Autowired
    JedisPool jedisPool;

    @Autowired
    UserService userService;


    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(path = "/login")
    public String loginWithTelegram() {
        String uuid = UUID.randomUUID().toString();
        jedisPool.getResource().setex("login_"+uuid, 60 , new Date().toString());
        return "redirect:https://telegram.me/tlgrammarsBot?start="+uuid;
    }

    @RequestMapping(path = "/telegram/{uuid}")
    public String AutologinWithTelegram(@PathVariable("uuid") String uuid, HttpServletRequest request, HttpServletResponse response) {
        String userId = jedisPool.getResource().get("telegram_"+uuid);
        User user = userService.findById(Long.valueOf(userId));
        if(user != null) {
            authenticateUserAndSetSession(user,request);
        }
        return "home.html";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

}
