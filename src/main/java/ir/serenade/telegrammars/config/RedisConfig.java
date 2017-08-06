package ir.serenade.telegrammars.config;

import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * Created by serenade on 8/5/17.
 */

@Component
public class RedisConfig {

    @Bean
    JedisPool jedisPool() {
        return new JedisPool();
    }
}
