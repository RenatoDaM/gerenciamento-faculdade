package com.gerenciamentofaculdade.gerenciamentofaculdade;

import com.gerenciamentofaculdade.gerenciamentofaculdade.security.auth.AuthenticationService;
import com.gerenciamentofaculdade.gerenciamentofaculdade.security.auth.RegisterRequest;
import com.gerenciamentofaculdade.gerenciamentofaculdade.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GerenciamentoFaculdadeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoFaculdadeApplication.class, args);
    }
    /*@Bean
    public CommandLineRunner commandLineRunner(
        AuthenticationService service
    ) {
        return args -> {
            var admin = new RegisterRequest();
            admin.setFirstname("Admin");
            admin.setLastname("Admin");
            admin.setEmail("admin@mail.com");
            admin.setPassword("password");
            admin.setRole(Role.ADMIN);
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = new RegisterRequest();
            manager.setFirstname("Admin");
            manager.setLastname("Admin");
            manager.setEmail("manager@mail.com");
            manager.setPassword("password");
            manager.setRole(Role.ADMIN);
            System.out.println("Manager token: " + service.register(manager).getAccessToken());
        };
    }*/
}
