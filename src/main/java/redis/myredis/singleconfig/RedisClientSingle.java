package redis.myredis.singleconfig;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.myredis.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Redis客户端单机版
 */
@Component
public class RedisClientSingle implements RedisClient {

    @Autowired
    private JedisPool jedisPool;

    private Jedis jedis;

    @Override
    public String set(String key, String value) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key) {

        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long del(String key) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.del(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hset(key, field, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hget(String key, String field) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.hget(key, field);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hdel(String key, String... fields) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hdel(key, fields);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setnx(String key, String value) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.setnx(key, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hsetnx(key, field, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.expire(key, seconds);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incr(String key) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.incr(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long decr(String key) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.decr(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        try {
            jedis = jedisPool.getResource();
            Boolean result = jedis.exists(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long persist(String key) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.persist(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.ttl(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lpush(String key, String... strings) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.lpush(key, strings);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String rpop(String key) {
        try {
            jedis = jedisPool.getResource();
            String result = jedis.rpop(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String sset(String key, List<T> list) {
        return null;
    }

    @Override
    public Long rpush(String key, Object val) {
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.lpush(key, (String) val);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

