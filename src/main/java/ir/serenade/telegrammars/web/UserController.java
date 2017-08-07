package ir.serenade.telegrammars.web;

import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.domain.validator.UserValidator;
import ir.serenade.telegrammars.service.SecurityService;
import ir.serenade.telegrammars.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    SecurityService securityService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(path = "/telegram")
    public String loginWithTelegram() {
        String uuid = UUID.randomUUID().toString();
        jedisPool.getResource().setex("login_"+uuid, 60 , new Date().toString());
        return "redirect:https://telegram.me/tlgrammarsBot?start="+uuid;
    }

    @RequestMapping(path = "/telegram/token/{uuid}")
    public String AutologinWithTelegram(@PathVariable("uuid") String uuid, HttpServletRequest request, HttpServletResponse response) {
        String userId = jedisPool.getResource().get("telegram_"+uuid);
        if(userId != null) {
            User user = userService.findById(Long.valueOf(userId));
            if(user != null) {
                user.setPassword(uuid);
                userService.save(user);
                securityService.autologin(user.getUsername(),uuid);
            }
            return "redirect:/home";
        } else {
            return "error/403";
        }
    }

    @RequestMapping(value="/user/profile-picture", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] profilePicture() throws IOException {
        User u = userService.getLoggedInUser(false);
        if(u != null && u.getProfilePhoto() != null) {
            return IOUtils.toByteArray(new URL(u.getProfilePhoto()).openStream());
        } else {
            return null;
        }
    }

    @RequestMapping(value="/user/name", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String name() throws IOException {
        User u = userService.getLoggedInUser(false);
        if(u != null) {
            return u.getFirstName()+ " " + u.getLastName();
        } else {
            return null;
        }
    }


}
