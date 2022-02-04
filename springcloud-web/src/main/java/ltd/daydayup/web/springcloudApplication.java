package ltd.daydayup.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther lipengcheng
 * @date 2022/2/4 02:21
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class springcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(springcloudApplication.class,args);
    }
}
