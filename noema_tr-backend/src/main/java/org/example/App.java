package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.context.ConfigurableApplicationContext;
import org.example.model.User;
import org.example.repository.UserRepository;

@SpringBootApplication
public class App {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        MongoDBConnectionTest connectionTest = context.getBean(MongoDBConnectionTest.class);
        connectionTest.testConnection();
        App app = context.getBean(App.class);
        app.fetchAllUsers();
    }

    @Bean
    public RouterFunction<?> routes(WelcomeHandler welcomeHandler, GreetingHandler greetingHandler) {
        return welcomeHandler.routes()
                .and(greetingHandler.routes());
    }

    public void fetchAllUsers() {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
