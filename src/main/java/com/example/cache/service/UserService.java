package com.example.cache.service;

import com.example.cache.config.CacheConfig;
import com.example.cache.domain.entitiy.RedisHashUser;
import com.example.cache.domain.entitiy.User;
import com.example.cache.repository.RedisHashUserRepository;
import com.example.cache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RedisHashUserRepository redisHashUserRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;

    public User getUser(final Long id) {
        String key = "user:%d".formatted(id);
        User cachedUser = redisTemplate.opsForValue().get(key);
        if(cachedUser != null) {
            return cachedUser;
        }
        User user = userRepository.findById(id).orElseThrow();
        redisTemplate.opsForValue().set(key, user, Duration.ofSeconds(30));
        return user;
    }

    public RedisHashUser getRedisHashUser(final Long id) {
        RedisHashUser cachedUser = redisHashUserRepository.findById(id).orElseGet(() -> {
            User user = userRepository.findById(id).orElseThrow();
            return redisHashUserRepository.save(RedisHashUser.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build());
        });
        return cachedUser;
    }

    @Cacheable(cacheNames = CacheConfig.CACHE1, key="'user:' + #id")
    public User getCacheUser(final Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
