package ltd.daydayup.web;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * springcloudApplication
 *
 * @auther lipengcheng
 * @date 2022/2/4 02:21
 */
@EnableDiscoveryClient
public class springcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplication.class,args);
    }
}
