package ir.serenade.telegrammars.bot;

import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.repository.TokenRepository;
import ir.serenade.telegrammars.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendContact;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by serenade on 8/5/17.
 */

@Component
public class TelegrammarsBot extends TelegramLongPollingBot {

    Logger logger = LoggerFactory.getLogger(TelegrammarsBot.class);

    @Autowired
    UserService userService;



    @Value("${telegrammars.login.bot.name}")
    private String botName;

    @Value("${telegrammars.login.bot.token}")
    private String botToken;

    @Value("${telegrammmars.domain.name}")
    private String domainName;

    @Autowired
    private TokenRepository tokenRepository;

    /*
    @Autowired
    TelegramBotApi telegramBotApi;




    @MessageMapping(regexp = "/start .+")
    public Reply loginTelegram(UpdateEvent updateEvent) {
        String _uuid = updateEvent.getUpdate().getMessage().getText().substring(7);
        String date = jedisPool.getResource().get("login_"+_uuid);
        if(date != null) {
            Chat chat = updateEvent.getUpdate().getMessage().getChat();
            User user = userService.findByChatId(chat.getId());
            if(user == null) {
                user = new User(chat.getFirstName(), chat.getLastName(), chat.getUserName(), chat.getId(),null);
                user.setRoles(new HashSet<>(Arrays.asList(userService.findRoleByName("ROLE_USER"))));
                user = userService.save(user);
            }
            String uuid = UUID.randomUUID().toString();
            jedisPool.getResource().setex("telegram_"+uuid, 60 , user.getId()+"");
        } else {
            return ReplyTo.to(updateEvent).withMessage("User Not Found");
        }
    }

    @MessageMapping(regexp = ".*")
    public Reply logTelegram(UpdateEvent updateEvent) {
        System.out.println(updateEvent);
        return null;
    }
    */


    public Message sendMessage(Long chatId, String text) {
        try {
            return sendMessage(new SendMessage().setChatId(chatId)
                    .setText(text));
        } catch (TelegramApiException e) {
            logger.error("Error Sending Message" , e);
        }
        return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/start ")) {
            String _uuid = update.getMessage().getText().substring(7);
            String date = tokenRepository.get("login_"+_uuid);
            if(date != null) {
                Chat chat = update.getMessage().getChat();
                User user = userService.findByChatId(chat.getId());
                if(user == null) {
                    user = new User(chat.getFirstName(), chat.getLastName(), chat.getUserName(), chat.getId(),null);
                    user.setRoles(new HashSet<>(Arrays.asList(userService.findRoleByName("ROLE_USER"))));
                    user = userService.save(user);
                }

                if(user.getPhoneNumber() == null) {
                    try {
                        KeyboardButton yes = new KeyboardButton("Yes").setRequestContact(true);
                        KeyboardButton no = new KeyboardButton("No");
                        KeyboardRow buttons = new KeyboardRow();
                        buttons.add(yes);
                        buttons.add(no);
                        sendMessage(new SendMessage().setChatId(update.getMessage().getChatId())
                                .setText("Please share your contact").
                                        setReplyMarkup(new ReplyKeyboardMarkup().setKeyboard(Arrays.asList(buttons)).setResizeKeyboard(true).setOneTimeKeyboard(true)));
                    } catch (Exception e) {
                        logger.error("Error Sending Message" , e);
                    }
                } else {
                    String uuid = UUID.randomUUID().toString();
                    tokenRepository.set("telegram_"+uuid, user.getId()+"", 300);
                    try {
                        sendMessage(new SendMessage().setChatId(update.getMessage().getChatId())
                                .setText("Please click this link to finish your signup at *Telegrammars*: \n" +
                                        "[Click here]("+ "http://"+domainName+"/telegram/token/" + uuid  +")"
                                )
                                .setParseMode(ParseMode.MARKDOWN));

                    } catch (TelegramApiException e) {
                        logger.error("Error Sending Message" , e);
                    }
                }


            } else {
                try {
                    sendMessage(new SendMessage().setChatId(update.getMessage().getChatId())
                            .setText("User Not Found"));

                } catch (Exception e) {
                    logger.error("Error Sending Message" , e);
                }

            }
        } else if(update.hasMessage() && update.getMessage().getContact() != null) {
            User user = userService.findByChatId(update.getMessage().getChatId());
            if(user != null) {
                user.setPhoneNumber(update.getMessage().getContact().getPhoneNumber());
                try {
                    UserProfilePhotos photos = getUserProfilePhotos(new GetUserProfilePhotos().setUserId(user.getChatId().intValue()).setOffset(0).setLimit(1));
                    if(photos.getPhotos().size() > 0) {
                        String photoUrl = getFile(new GetFile().setFileId(photos.getPhotos().get(0).get(0).getFileId())).getFileUrl(getBotToken());
                        user.setProfilePhoto(photoUrl);
                    }
                } catch (TelegramApiException e) {
                    logger.error("Error Getting Profile Photos",e);
                }
                userService.save(user);

                String uuid = UUID.randomUUID().toString();
                tokenRepository.set("telegram_"+uuid, user.getId()+"", 300);

                try {
                    sendMessage(new SendMessage().setChatId(update.getMessage().getChatId())
                            .setText("Please click this link to finish your signup at *Telegrammars*: \n" +
                                    "[Click here]("+ "http://"+domainName+"/telegram/token/" + uuid  +")"
                            )
                            .setParseMode(ParseMode.MARKDOWN));

                } catch (TelegramApiException e) {
                    logger.error("Error Sending Message" , e);
                }
            } else {
                sendMessage(update.getMessage().getChatId(),"You should register first");
            }

        }
    }

    @Override
    public String getBotUsername() {
        return "@" + botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
