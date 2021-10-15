package com.project.doc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String lastName;
    private String firstName;
    private String mail;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();
    private String securityNumber;

    public AppUser(String username, String lastName, String firstName, String mail, String password) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mail = mail;
        this.password = password;
    }

    public AppUser(String username, String lastName, String firstName, String mail, String password, String securityNumber) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mail = mail;
        this.password = password;
        this.securityNumber = securityNumber;
    }

    public boolean isDoctor(){
        return roles.stream().anyMatch(r-> r.getRoleName().equalsIgnoreCase("doctor"));
    }
}
