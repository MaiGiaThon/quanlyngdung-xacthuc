package Quanly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping
    public String Homepage(){
        return "index";
    }

    @GetMapping("login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("register")
    public String showRegister(){
        return "register";
    }
}