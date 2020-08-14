package com.codingsession.tbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.codingsession.tbd.api.model", "com.codingsession.tbd"})
@SpringBootApplication
public class TbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TbdApplication.class, args);
    }

}
