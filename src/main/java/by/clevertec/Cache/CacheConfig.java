package by.clevertec.Cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Value("${cache.type}")
    private String algorithm;

    @Value("${cache.size}")
    private String maxSize;

    public String getAlgorithm() {
        return algorithm;
    }

    public int getMaxSize() {
        return Integer.parseInt(maxSize);
    }
}

