package ir.serenade.telegrammars.repository;


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
public class TokenRepository {

    private static Map<String, Token> tokenMap = new HashMap<>();

    public static void set(String key, String value, int seconds) {
        long expiration = new Date().getTime() + seconds * 1000;
        tokenMap.put(key, new Token(value, expiration));
    }

    public static String get(String key) {
        Token token = tokenMap.get(key);
        if(token != null && token.expirationDate > new Date().getTime()) {
            return token.value;
        } else {
            return null;
        }
    }


}
