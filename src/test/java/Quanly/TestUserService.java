package Quanly;

import Quanly.Exception.UserException;
import Quanly.Model.User;
import Quanly.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static  org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TestUserService {
    @Autowired private UserService userService;

    @Test
    public void addUser(){
        User user = userService.addUserAutoActive("Mai 111", "Mai111@gmail.com", "Mai111111Mai");

        assertThat(user).isNotNull();
    }

    @Test
    public void login_when_account_is_not_found(){
        assertThatThrownBy(() -> {
            userService.login("Mai4444@gmail.com","Mai111111Mai");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User is not found!");
    }

    @Test
    public void login_when_account_is_pending(){
        userService.addUser("Mai 111", "Mai111@gmail.com", "Mai111111Mai");
        assertThatThrownBy(() -> {
            userService.login("Mai111@gmail.com","Mai111111Mai");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User is not Activated!");

        /*assertThat(userService.login("Mai111@gmail.com","Mai111111Mai")).isNotNull();
        assertThat(userService.login("Mai111@gmail.com","Mai222222Mai")).isNull();*/
    }

    @Test
    public void login_when_passworld_is_incorrect(){
        userService.addUserAutoActive("Mai 111", "Mai111@gmail.com", "Mai111111Mai");
        assertThatThrownBy(() -> {
            userService.login("Mai111@gmail.com","Mai4444Mai");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("Password is incorrect!");
    }

    @Test
    public void login_succesfull(){
        userService.addUserAutoActive("Mai 111", "Mai111@gmail.com", "Mai111111Mai");
        User user = userService.login("Mai111@gmail.com","Mai111111Mai");
        assertThat(user).isNotNull();
    }
}
