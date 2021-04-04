package bookstore.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class);
        System.out.println("App started");
    }
}