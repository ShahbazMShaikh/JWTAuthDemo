package jwtDemoApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JWTSpringBootApplication {

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(JWTSpringBootApplication.class, args);
    }
}
