package ir.serenade.telegrammars.bot;

import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import redis.clients.jedis.JedisPool;
import ru.skuptsov.telegram.bot.platform.client.TelegramBotApi;
import ru.skuptsov.telegram.bot.platform.client.command.Reply;
import ru.skuptsov.telegram.bot.platform.client.command.ReplyTo;
import ru.skuptsov.telegram.bot.platform.handler.annotation.MessageHandler;
import ru.skuptsov.telegram.bot.platform.handler.annotation.MessageMapping;
import ru.skuptsov.telegram.bot.platform.model.UpdateEvent;

import java.util.UUID;

/**
 * Created by serenade on 8/5/17.
 */

@MessageHandler
public class TelegrammarsBot {

    @Autowired
    UserService userService;

    @Autowired
    TelegramBotApi telegramBotApi;

    @Autowired
    JedisPool jedisPool;


    @MessageMapping(regexp = "/start .+")
    public Reply loginTelegram(UpdateEvent updateEvent) {
        String _uuid = updateEvent.getUpdate().getMessage().getText().substring(7);
        String date = jedisPool.getResource().get("login_"+_uuid);
        if(date != null) {
            Chat chat = updateEvent.getUpdate().getMessage().getChat();
            User user = userService.findByChatId(chat.getId());
            if(user == null) {
                user = new User(chat.getFirstName(), chat.getLastName(), chat.getUserName(), chat.getId(),null);
                user = userService.save(user);
            }
            String uuid = UUID.randomUUID().toString();
            jedisPool.getResource().setex("telegram_"+uuid, 60 , user.getId()+"");

            return ReplyTo.to(updateEvent).withMessage("http://localhost:8080/telegram/" + uuid);

        } else {
            return ReplyTo.to(updateEvent).withMessage("User Not Found");
        }
    }


}
