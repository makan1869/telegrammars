package ir.serenade.telegrammars.config;

import ir.serenade.telegrammars.bot.TelegrammarsBot;
import ir.serenade.telegrammars.domain.Role;
import ir.serenade.telegrammars.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by serenade on 8/6/17.
 */

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TelegrammarsBot telegrammarsBot;

    Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if(adminRole == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        Role operatorRole = roleRepository.findByName("ROLE_OPERATOR");
        if(operatorRole == null) {
            roleRepository.save(new Role("ROLE_OPERATOR"));
        }
        Role userRole = roleRepository.findByName("ROLE_USER");
        if(userRole == null) {
            roleRepository.save(new Role("ROLE_USER"));
        }

        try {
            TelegramBotsApi telegramBotsApi = createTelegramBotsApi();
            try {
                // Register long polling bots. They work regardless type of TelegramBotsApi we are creating
                telegramBotsApi.registerBot(telegrammarsBot);
            } catch (TelegramApiException e) {
                logger.error("Error Registering Bots", e);
            }
        } catch (Exception e) {
            logger.error("Error Registering Bots", e);
        }

    }

    private static TelegramBotsApi createTelegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = createLongPollingTelegramBotsApi();
        return telegramBotsApi;
    }

    private static TelegramBotsApi createLongPollingTelegramBotsApi() {
        return new TelegramBotsApi();
    }
}