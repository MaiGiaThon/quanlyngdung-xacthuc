package Quanly.Hash;

public interface Hashing {
    public  String hashPass(String pass);
    public boolean validatePass(String orginalPass, String storedPass);
}
