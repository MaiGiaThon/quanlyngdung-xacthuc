package Quanly.Controller;

import Quanly.Exception.UserException;
import Quanly.Request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping
    public String Homepage(){
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model){
        model.addAttribute("loginrequest", new LoginRequest("",""));
        return "login";
    }
    @PostMapping("login")
    public  String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, BindingResult result){
        if(result.hasErrors()){
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegister(){
        return "register";
    }

    @GetMapping("foo")
    public  String foo(){
        throw  new UserException("Some Error");
    }
}

