package redis.myredis.clusterconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * redis集群配置
 * @author  wzy
 * @create  2017/12/29 9:47
 **/
@Configuration
public class RedisClusterCon {

    @Value("${spring.redisCluter.clusterNodes}")
    private String clusterNodes;

    @Value("${spring.redisCluter.connectionTimeout}")
    private Integer connectionTimeout;

    @Value("${spring.redisCluter.password}")
    private String password;

    @Value("${spring.redisCluter.soTimeout}")
    private int soTimeout;

    @Value("${spring.redisCluter.maxAttempts}")
    private int maxAttempts;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean("jedisCluster")
    public JedisCluster jedisCluster(){
        String[] serverNodes = clusterNodes.split(",");
        Set<HostAndPort> set =new HashSet<HostAndPort>();
        String[] ipAndPorts;
        for(String host:serverNodes){
            ipAndPorts=host.split(":");
            set.add(new HostAndPort(ipAndPorts[0].trim(),Integer.valueOf(ipAndPorts[1].trim())));
        }
        JedisCluster jedisCluster = new JedisCluster(set, Integer.valueOf(connectionTimeout), Integer.valueOf(soTimeout), Integer.valueOf(maxAttempts),password, jedisPoolConfig);
        return jedisCluster;
    }
//    @Bean
//    public RedisClientCluster redisClientCluster(){
//        return new RedisClientCluster();
//    }

}
