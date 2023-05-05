package ru.elmanov.mongodb.demo.repository.redis;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.elmanov.mongodb.demo.model.User;

import java.util.List;
import java.util.Objects;
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
        if (Objects.nonNull(user)) {
            opsForHash.put(REDIS_KEY, user.getId(), user);
        }
    }

    @Override
    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            opsForHash.delete(id);
        }

        JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    @Override
    public User find(String id) {
        if (StringUtils.isNotBlank(id)) {
            return (User) opsForHash.get(REDIS_KEY, id);
        }

        return null;
    }

}
