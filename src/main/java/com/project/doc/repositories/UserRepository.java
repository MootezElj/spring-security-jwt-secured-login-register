package com.project.doc.repositories;

import com.project.doc.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public AppUser findByMail(String mail);
    public List<AppUser> findAllByRoles_RoleName(String roleName);
}
