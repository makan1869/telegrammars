package ir.serenade.telegrammars;

import ir.serenade.telegrammars.bot.TelegrammarsBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
public class TelegrammarsApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TelegrammarsApplication.class, args);
	}


}
