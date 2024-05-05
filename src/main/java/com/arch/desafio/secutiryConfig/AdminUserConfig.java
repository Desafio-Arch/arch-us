package com.arch.desafio.secutiryConfig;

import com.arch.desafio.models.Enums.enumRole;
import com.arch.desafio.models.Role;
import com.arch.desafio.models.User;
import com.arch.desafio.services.RoleService;
import com.arch.desafio.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleService roleService,
                           UserService userService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        this.roleService.saveRole(new Role("admin", 1));
        this.roleService.saveRole(new Role("basic", 2));

        var roleAdmin = this.roleService.findRoleByNameContainingIgnoreCase(enumRole.ADMIN.name());
        var userAdmin = userService.findByUserName("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin already exists");
                },
                () -> {
                    var user = new User();
                    user.setUserName("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userService.save(user);
                }
        );

    }
}
