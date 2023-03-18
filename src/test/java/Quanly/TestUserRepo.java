package Quanly;

import Quanly.Model.State;
import Quanly.Model.User;
import Quanly.Responsitory.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static  org.assertj.core.api.Assertions.*;
//@SpringBootTest
public class TestUserRepo {
    @Test
    public void Testadduser(){
        UserRepo userRepo = new UserRepo();
            User user = userRepo.addUser("Mai 111", "mai111@gmail.com", "Mai111111Mai", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    public void TestadduserWithPendingState(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Mai 111", "mai111@gmail.com", "Mai111111Mai");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void TestisEmailExits(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("Mai 111", "mai111@gmail.com", "Mai111111Mai");
        userRepo.addUser("Mai 222", "mai222@gmail.com", "Mai222222Mai");
        userRepo.addUser("Mai 333", "mai333@gmail.com", "Mai333333Mai");

        assertThat(userRepo.isEmailExitst("mai111@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExitst("mai222@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExitst("mai333@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExitst("mai444@gmail.com")).isFalse();
        assertThat(userRepo.isEmailExitst("mai555@gmail.com")).isFalse();
    }

    @Test
    public void TestfindByEmail(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("Mai 111", "mai111@gmail.com", "Mai111111Mai");
        userRepo.addUser("Mai 222", "mai222@gmail.com", "Mai222222Mai");
        userRepo.addUser("Mai 333", "mai333@gmail.com", "Mai333333Mai");

        assertThat(userRepo.findByEmail("mai111@gmail.COM")).isPresent();
        assertThat(userRepo.findByEmail("mai222@gmail.COM")).isPresent();
        assertThat(userRepo.findByEmail("mai444@gmail.COM")).isNotPresent();
    }
}
