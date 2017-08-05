package ir.serenade.telegrammars.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
