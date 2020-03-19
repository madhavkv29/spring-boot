package com.dataeconomy.proessApp.config;

import com.dataeconomy.proessApp.controller.ItemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfiguration.class);

    @Value("${spring.cache.names}")
    public String[] cacheNames;
    @Autowired
    public CacheManager cacheManager;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(cacheNames);
    }

   // @ConditionalOnProperty(name = "spring.cache.autoexpiry", value = "true")
    @Scheduled(fixedDelayString = "${spring.cache.expire.delay:50000}")
    public void cacheEvict() {
        cacheManager.getCacheNames().stream()
                .map(cacheManager::getCache)
                .forEach(Cache::clear);
        LOGGER.info("Cache is cleared " + cacheManager.getCacheNames());
    }
}
