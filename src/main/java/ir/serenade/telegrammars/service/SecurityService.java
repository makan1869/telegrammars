package ir.serenade.telegrammars.service;

/**
 * Created by serenade on 8/1/17.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
