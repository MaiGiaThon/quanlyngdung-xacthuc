package Quanly.Hash;

import org.springframework.stereotype.Component;

@Component
public class Pass implements Hashing{
    @Override
    public String hashPass(String pass) {
        return null;
    }

    @Override
    public boolean validatePass(String orginalPass, String storedPass) {
        return (orginalPass.equals(storedPass)) ? true : false;
    }
}
