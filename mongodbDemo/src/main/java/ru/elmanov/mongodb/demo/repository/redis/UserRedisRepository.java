package ru.elmanov.mongodb.demo.repository.redis;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.elmanov.mongodb.demo.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRedisRepository implements RedisRepository<User> {

    public static final String REDIS_KEY = "USER_REDIS_KEY";

    final RedisTemplate<String, Object> redisTemplate;
    HashOperations<String, String, Object> opsForHash;

    public UserRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        opsForHash = redisTemplate.opsForHash();
    }

    @Override
    public List<User> findAll() {
        return opsForHash.values(REDIS_KEY).stream().map(e -> (User) e).collect(Collectors.toList());
    }

    @Override
    public void add(User user) {
        opsForHash.put(REDIS_KEY, user.getId(), user);
    }

    @Override
    public void delete(String id) {
        opsForHash.delete(id);
    }

    @Override
    public User find(String id) {
        return (User) opsForHash.get(REDIS_KEY, id);
    }

//    public static void main(String[] args) {
//        JedisPool pool = new JedisPool("localhost", 6379, "default", "redis-password");
//
//        try (Jedis jedis = pool.getResource()) {
//            // Store & Retrieve a simple string
//            jedis.set("foo", "bar");
//            System.err.println(jedis.get("foo")); // prints bar

            // Store & Retrieve a HashMap
//            Map<String, String> hash = new HashMap<>();;
//            hash.put("name", "John");
//            hash.put("surname", "Smith");
//            hash.put("company", "Redis");
//            hash.put("age", "29");
//            jedis.hset("user-session:123", hash);
//            System.out.println(jedis.hgetAll("user-session:123"));
//            // Prints: {name=John, surname=Smith, company=Redis, age=29}
//        }
//    }
}
