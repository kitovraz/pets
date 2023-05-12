package ru.elmanov.jwt.security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.elmanov.jwt.security.demo.model.Role;
import ru.elmanov.jwt.security.demo.model.User;
import ru.elmanov.jwt.security.demo.service.RoleService;
import ru.elmanov.jwt.security.demo.service.UserService;

import java.util.List;

@SpringBootApplication
public class JwtSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        return args -> {
            var roleUser = Role.builder().name("ROLE_USER").build();
            var roleAdmin = Role.builder().name("ROLE_ADMIN").build();
            roleService.save(roleUser);
            roleService.save(roleAdmin);

            var user = User.builder()
                    .username("max")
                    .password(passwordEncoder.encode("123"))
//                    .password("123")
                    .roles(List.of(roleUser))
                    .build();
            var admin = User.builder()
                    .username("nick")
                    .password(passwordEncoder.encode("qwe"))
//                    .password("qwe")
                    .roles(List.of(roleUser, roleAdmin))
                    .build();

            userService.save(user);
            userService.save(admin);
        };
    }

}
