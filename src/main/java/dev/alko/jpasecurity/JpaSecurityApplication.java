package dev.alko.jpasecurity;

import dev.alko.jpasecurity.model.Post;
import dev.alko.jpasecurity.model.User;
import dev.alko.jpasecurity.repository.PostRepository;
import dev.alko.jpasecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JpaSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository, UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            postRepository.save(Post.builder()
                            .title("Hello, World")
                            .slug("hello-world")
                            .content("Welcome to my blog")
                            .author("Aleksandr Kolosov")
                            .build()
                    //new Post("Hello, World", "hello-world", "Welcome to my blog", "Aleksandr Kolosov")
            );
            userRepository.save(User.builder()
                            .username("admin")
                            .password(encoder.encode("password"))
                            .roles("ROLE_ADMIN,ROLE_USER")
                    .build());

            userRepository.save(User.builder()
                    .username("user")
                    .password(encoder.encode("password"))
                    .roles("ROLE_USER")
                    .build());
        };
    }
}
