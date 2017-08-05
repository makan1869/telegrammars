package ir.serenade.telegrammars.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import ru.skuptsov.telegram.bot.platform.client.TelegramBotApi;
import ru.skuptsov.telegram.bot.platform.client.command.Reply;
import ru.skuptsov.telegram.bot.platform.client.command.ReplyTo;
import ru.skuptsov.telegram.bot.platform.handler.annotation.MessageHandler;
import ru.skuptsov.telegram.bot.platform.handler.annotation.MessageMapping;
import ru.skuptsov.telegram.bot.platform.model.UpdateEvent;

/**
 * Created by serenade on 8/5/17.
 */

@MessageHandler
public class TelegrammarsBot {

    @MessageMapping(regexp = "/start .+")
    public Reply sayHi(UpdateEvent updateEvent) {
        return ReplyTo.to(updateEvent).withMessage(updateEvent.getUpdate().getMessage().getText() + " Hi there!")
                .setCallback(message -> System.out.println("Message sent"));
    }


}
