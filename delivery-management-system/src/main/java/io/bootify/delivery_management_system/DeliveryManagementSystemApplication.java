package io.bootify.delivery_management_system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
//@MapperScan("mapper")
public class DeliveryManagementSystemApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DeliveryManagementSystemApplication.class, args);
        log.info("App Start>>>..........");
    }

}
