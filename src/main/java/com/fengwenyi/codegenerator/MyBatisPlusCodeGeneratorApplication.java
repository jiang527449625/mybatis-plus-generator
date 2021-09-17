package com.fengwenyi.codegenerator;

import com.fengwenyi.apistarter.EnableApiStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-12
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableApiStarter
public class MyBatisPlusCodeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusCodeGeneratorApplication.class, args);
    }

}
