package ir.serenade.telegrammars.web;

import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.domain.validator.UserValidator;
import ir.serenade.telegrammars.service.SecurityService;
import ir.serenade.telegrammars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.UUID;

/**
 * Created by serenade on 8/1/17.
 */
@Controller
public class UserController {


    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(path = "/login")
    public String loginWithTelegram() {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put("LOGIN",uuid,new Date());
        return "redirect:https://telegram.me/tlgrammarsBot?start="+uuid;
    }

}
