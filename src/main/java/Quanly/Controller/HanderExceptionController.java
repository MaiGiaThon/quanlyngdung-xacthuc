package Quanly.Controller;

import Quanly.Exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HanderExceptionController {
    @ExceptionHandler(UserException.class)
    public String handlerUserException(UserException ex, Model model){
        model.addAttribute("Error", ex);
        return "Error";
    }
}
