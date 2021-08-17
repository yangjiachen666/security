package com.xyx;

import com.xyx.utils.cache.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ApplicationRunningBefore implements CommandLineRunner {

    private final RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        redisCache.deleteAllKeys();
        log.info("Clear Redis Success");
    }
}
