package com.project.doc.security.services;

import com.project.doc.entities.AppRole;
import com.project.doc.entities.AppUser;

import java.util.List;

public interface AccountService {
    public AppUser saverUser(AppUser user);
    public AppUser saveDoctor(AppUser user);
    public AppUser savePatient(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username, String roleName);
    public AppUser findUserByUsername(String username);
    public AppUser findUserById(Long id);
    public List<AppUser> findAllPatients();
}
