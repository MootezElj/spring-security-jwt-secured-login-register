package com.project.doc.security.controller;

import com.project.doc.entities.AppUser;
import com.project.doc.security.services.AccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AccountResource {

    private final AccountService accountService;
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @GetMapping("/patients/")
    public List<AppUser> findAllPatients(){
        return this.accountService.findAllPatients();
    }

    @GetMapping("/users/username/{username}/")
    public AppUser findUserByUsername(@PathVariable String username){
        return accountService.findUserByUsername(username);
    }

    @GetMapping("/users/id/{id}")
    public AppUser findUserById(@PathVariable Long id){
        return accountService.findUserById(id);
    }

    @PostMapping("/register-doc/")
    public AppUser registerDoctor(@RequestBody  AppUser user){
        return  accountService.saveDoctor(user);
    }

    @PostMapping("/register-pat/")
    public AppUser registerPatient(@RequestBody  AppUser user){
        return accountService.savePatient(user);
    }

    @RequestMapping("/user/")
    public AppUser user(Principal user) {
        log("Request to get current user");
        return accountService.findUserByUsername(user.getName());
    }
    private void log(String msg){
        log.debug(msg);
    }
}
