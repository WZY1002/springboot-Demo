//package config;
//
//public interface RedisClient {
//    String set(String key, String value);
//
//    String get(String key);
//
//    Long del(String key);
//
//    Long hset(String key, String field, String value);
//
//    String hget(String key, String field);
//
//    Long hdel(String key, String... fields);
//
//    Long setnx(String key, String value);
//
//    Long hsetnx(String key, String field, String value);
//
//    Long expire(String key, int seconds);
//
//    Long incr(String key);
//
//    Long decr(String key);
//
//    Boolean exists(String key);
//
//    Long persist(String key);
//
//    Long ttl(String key);
//
//    Long lpush(String key, String... strings);
//
//    String rpop(String key);
//}
