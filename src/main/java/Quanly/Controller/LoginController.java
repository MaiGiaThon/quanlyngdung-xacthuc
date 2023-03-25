package Quanly.Controller;

import Quanly.DTO.UserDTO;
import Quanly.Exception.UserException;
import Quanly.Model.User;
import Quanly.Request.LoginRequest;
import Quanly.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired UserService userService;

    @GetMapping
    public String Homepage(Model model, HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null){
            model.addAttribute("user", userDTO);
        }
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model){
        model.addAttribute("loginrequest", new LoginRequest("",""));
        return "login";
    }
    @PostMapping("login")
    public  String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        User user;
        try{
            user = userService.login(loginRequest.email(), loginRequest.pass());
            session.setAttribute("user", new UserDTO(user.getId(), user.getFullname(), user.getEmail()));
            return "redirect:/";
        }catch ( UserException ex){
            switch (ex.getMessage()){
                case "User is not found!":
                    result.addError(new FieldError("loginrequest", "email", "Email does not exist!"));
                    break;
                case "User is not Activated!":
                    result.addError(new FieldError("loginrequest", "email", "User is not Activated!"));
                    break;
                case "Password is incorrect!":
                    result.addError(new FieldError("loginrequest", "pass", "Password is incorrect!"));
                    break;
            }
            return "login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegister(){
        return "register";
    }

    @GetMapping("admin")
    public String showAdmin(Model model, HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null){
            return "admin";
        }else return "redirect:/";
    }

    @GetMapping("foo")
    public  String foo(){
        throw  new UserException("Some Error");
    }
}

