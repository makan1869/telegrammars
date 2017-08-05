package ir.serenade.telegrammars;

import ir.serenade.telegrammars.bot.TelegrammarsBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skuptsov.telegram.bot.platform.config.BotPlatformConfiguration;
import ru.skuptsov.telegram.bot.platform.config.BotPlatformStarter;

@SpringBootApplication
public class TelegrammarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegrammarsApplication.class, args);
	}
}
