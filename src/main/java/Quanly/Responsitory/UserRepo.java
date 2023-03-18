package Quanly.Responsitory;

import Quanly.Model.State;
import Quanly.Model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<String, User>();

    public User addUser(String fullname, String email, String hashed_pass){
        return addUser(fullname,email,hashed_pass,State.PENDING);
    }
    public User addUser(String fullname, String email, String hashed_pass, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .fullname(fullname)
                .email(email)
                .hashed_pass(hashed_pass)
                .state(state)
                .build();
        users.put(id, user);
        return user;
    }

    public boolean isEmailExitst(String Email){
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(Email)).count()>0;
    }

    public Optional<User> findByEmail(String email){
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}

