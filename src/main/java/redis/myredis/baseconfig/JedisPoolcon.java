package redis.myredis.baseconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis公共配置
 * @author  wzy
 * @create  2017/12/29 9:46
 **/
@Configuration
public class JedisPoolcon {
    @Bean("jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.poolConfig.maxTotal}") Integer maxTotal,
                                       @Value("${spring.redis.poolConfig.maxWaitMillis}") Integer maxWaitMillis,
                                       @Value("${spring.redis.poolConfig.testOnBorrow}") Boolean testOnBorrow,
                                       @Value("${spring.redis.poolConfig.testWhileIdle}") Boolean testWhileIdle,
                                       @Value("${spring.redis.poolConfig.maxIdle}") Integer maxIdle,
                                       @Value("${spring.redis.poolConfig.timeBetweenEvictionRunsMillis}") Integer timeBetweenEvictionRunsMillis,
                                       @Value("${spring.redis.poolConfig.numTestsPerEvictionRun}") Integer numTestsPerEvictionRun,
                                       @Value("${spring.redis.poolConfig.minEvictableIdleTimeMillis}") Integer minEvictableIdleTimeMillis) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return jedisPoolConfig;
    }

}