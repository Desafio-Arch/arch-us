package com.arch.desafio.controllers;

import com.arch.desafio.controllers.dto.CreateUserDTO;
import com.arch.desafio.controllers.dto.UserDTO;
import com.arch.desafio.models.Enums.enumRole;
import com.arch.desafio.models.Role;
import com.arch.desafio.models.User;
import com.arch.desafio.services.RoleService;
import com.arch.desafio.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Controlador CRUD de usuários")
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserDTO user) {
        if (userService.findByUserName(user.username()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var roleForBaseUser = roleService.findRoleByNameContainingIgnoreCase(enumRole.BASIC.name());
        var userEntity = new User();
        userEntity.setUserName(user.username());
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        userEntity.setRoles(Set.of(roleForBaseUser));
        return new ResponseEntity<>(userService.save(userEntity), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obter todos os usuários", description = "Retorna todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = this.userService.findAllUser();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
