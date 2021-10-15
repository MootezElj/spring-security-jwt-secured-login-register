package com.project.doc;

import com.project.doc.entities.AppRole;
import com.project.doc.entities.AppUser;
import com.project.doc.security.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DocAppApplication implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(DocAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        accountService.saverUser(new AppUser("mootez","Mootez","elj","Mootez@gmail.com"
//                ,"124", "121 2156 25 21 121 34"));
//        accountService.saverUser(new AppUser("Hliwi","Doc","Hl","Hl@gmail.com","15622"));
//        accountService.saveRole(new AppRole(null,"PATIENT"));
//        accountService.saveRole(new AppRole(null,"DOCTOR"));
//        accountService.addRoleToUser("Hliwi", "DOCTOR");
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
