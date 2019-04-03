package redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.Set;

public class redisDemo {

    @Autowired
    private static RedisTemplate redisTemplate;

    public static void main(String[] args) {
      setDemo();
    }

    /**
     * redisTemplate操作set
     * @author wzy
     * @since 2019/4/3 22:17
     **/
    private static void setDemo() {
        SetOperations opsForSet = redisTemplate.opsForSet();
        opsForSet.add("k1","v1");
        opsForSet.add("k2","v2");
        opsForSet.add("k3","v3");

        Set members = redisTemplate.opsForSet().members("k1");
        System.out.println(members);

    }


}
