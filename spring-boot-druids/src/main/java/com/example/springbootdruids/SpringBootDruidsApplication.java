package com.example.springbootdruids;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class SpringBootDruidsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDruidsApplication.class, args);
    }

}
