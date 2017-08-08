package ir.serenade.telegrammars.repository;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Token {
    String value;
    long expirationDate;

    public Token(String value, long expirationDate) {
        this.value = value;
        this.expirationDate = expirationDate;
    }
}

@ComponentScan
@Component
@Scope("singleton")
public class TokenRepository {

    private Map<String, Token> tokenMap = new HashMap<>();

    public  void set(String key, String value, int seconds) {
        long expiration = new Date().getTime() + seconds * 1000;
        tokenMap.put(key, new Token(value, expiration));
    }

    public  String get(String key) {
        Token token = tokenMap.get(key);
        if(token != null && token.expirationDate > new Date().getTime()) {
            return token.value;
        } else {
            return null;
        }
    }


}
