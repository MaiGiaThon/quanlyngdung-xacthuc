package Quanly;

import Quanly.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements ApplicationRunner {
    @Autowired UserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.addUserAutoActive("Admin", "thonmaigia@gmail.com","malang1120");
        userService.addUser("Join", "Admin@gmail.com","12345");
    }
}
