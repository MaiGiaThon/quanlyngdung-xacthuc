package Quanly.Service;

import Quanly.Exception.UserException;
import Quanly.Hash.Hashing;
import Quanly.Model.State;
import Quanly.Model.User;
import Quanly.Responsitory.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceMemory implements UserService{
    private UserRepo userRepo;
    private Hashing hashing;

    @Override
    public User login(String email, String pass){
        Optional<User> o_user = userRepo.findByEmail(email);
        //Check User co ton tai hay khong
        if(!o_user.isPresent()){
            throw new UserException("User is not found!");
        }
        //Check trang thai cua User
        User user = o_user.get();
        if(user.getState() != State.ACTIVE){
            throw new UserException("User is not Activated!");
        }
        //Check password cua User
        if( (hashing.validatePass(pass, user.getHashed_pass()))){
            return user;
        } else{
            throw new UserException("Password is incorrect!");
        }
    }

    @Override
    public Boolean logout(String email) {
        return null;
    }

    @Override
    public User addUser(String fullname, String email, String pass) {
        return userRepo.addUser(fullname,email,pass);
    }

    @Override
    public User addUserAutoActive(String fullname, String email, String pass) {
        return userRepo.addUser(fullname,email,pass,State.ACTIVE);
    }

    @Override
    public Boolean activativeUser(String activate_code) {
        return null;
    }

    @Override
    public Boolean updatePass(String email, String pass) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
