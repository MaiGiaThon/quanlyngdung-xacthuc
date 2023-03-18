package Quanly.Service;

import Quanly.Model.State;
import Quanly.Model.User;

import java.util.Optional;

public interface UserService {
    public User login(String email, String pass);
    public Boolean logout(String email);

    public User addUser(String fullname, String email, String pass);
    public User addUserAutoActive(String fullname, String email, String pass);
    public Boolean activativeUser(String activate_code);

    public Boolean updatePass(String email, String pass);
    public Boolean updateEmail(String email, String newEmail);

    public Optional<User> findByEmail(String email);
    public User findById(String id);
}
