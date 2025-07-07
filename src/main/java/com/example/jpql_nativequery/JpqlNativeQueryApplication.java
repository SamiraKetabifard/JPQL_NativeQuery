package com.example.jpql_nativequery;

import com.example.jpql_nativequery.entity.User;
import com.example.jpql_nativequery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
    public class JpqlNativeQueryApplication {

        public static void main(String[] args) {
            SpringApplication.run(JpqlNativeQueryApplication.class, args);
        }

        private final UserRepository userRepository;

        public JpqlNativeQueryApplication(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Bean
        public CommandLineRunner loadData() {
            return args -> {
                userRepository.deleteAll();
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                List<User> users = Arrays.asList(
                        new User("samira", false, 45000, simpleDate.parse("1995-06-19")),
                        new User("nima", true, 39900, simpleDate.parse("2002-05-06")),
                        new User("ali", false, 32000, simpleDate.parse("1990-05-06")),
                        new User("reza", true, 35000, simpleDate.parse("2005-05-06")),
                        new User("hani", true, 55000, simpleDate.parse("2006-05-06")),
                        new User("kati", true, 88888, simpleDate.parse("1998-05-06"))
                );
                userRepository.saveAll(users);
            };
        }

    }
