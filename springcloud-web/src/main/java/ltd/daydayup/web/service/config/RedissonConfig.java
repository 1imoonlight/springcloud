package ltd.daydayup.web.service.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lipengcheng
 * @date 2022-02-24
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.hostName}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisHostName + ":" + redisPort)
                .setDatabase(0)
                .setKeepAlive(true)
                .setPingConnectionInterval(1000);

        return (Redisson) Redisson.create(config);
    }
}
