package redis.myredis.singleconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单机redispool配置
 *
 * @author wzy
 * @create 2017/12/29 9:47
 **/
@Configuration
public class JedisPoolSingleCon {

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean("jedisPool")
    public JedisPool jedisPool(JedisPoolConfig jedispoolconfig,
                               @Value("${spring.redis.host}") String host,
                               @Value("${spring.redis.port}") Integer port,
                               @Value("${spring.redis.timeout}") Integer timeout,
                               @Value("${spring.redis.password}") String password,
                               @Value("${spring.redis.database}") Integer database) {
        jedispoolconfig = jedisPoolConfig;
        return new JedisPool(jedispoolconfig, host, port, timeout, password, database);
    }
}
