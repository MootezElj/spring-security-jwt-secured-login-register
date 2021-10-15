package com.project.doc.security.services.impl;

import com.project.doc.entities.AppRole;
import com.project.doc.entities.AppUser;
import com.project.doc.repositories.RoleRepository;
import com.project.doc.repositories.UserRepository;
import com.project.doc.security.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saverUser(AppUser user) {
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public AppUser saveDoctor(AppUser user) {
        user.setSecurityNumber(null);
        return saveUserAndAssignRole(user, "DOCTOR");
    }

    @Override
    public AppUser savePatient(AppUser user) {
        return saveUserAndAssignRole(user, "PATIENT");
    }

    private AppUser saveUserAndAssignRole(AppUser user, String role){
        saverUser(user);
        if(!userExistsByMailOrUsername(user.getMail(), user.getUsername())){
            throw  new IllegalArgumentException("user with this username or mail exists !");
        }
        this.addRoleToUser(user.getUsername(), role);
        return user;
    }

    private boolean userExistsByMailOrUsername(String mail, String username){
        return ((userRepository.findByUsername(username) != null) || (userRepository.findByUsername(mail)) != null);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User cannot be found"));
    }

    @Override
    public List<AppUser> findAllPatients() {
        return userRepository.findAllByRoles_RoleName("PATIENT");
    }
}
