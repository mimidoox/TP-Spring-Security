package ma.projet.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }

	    @PostMapping("/login")
	    public String submitLoginForm() {
	        // TODO: authenticate user and redirect to home page
	        return "login";
	    }
}
