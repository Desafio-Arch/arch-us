package com.arch.desafio.controllers;

import com.arch.desafio.controllers.dto.BuildTokenData;
import com.arch.desafio.controllers.dto.LoginDTO;
import com.arch.desafio.controllers.dto.TokenLoginDTO;
import com.arch.desafio.services.UserService;
import com.arch.desafio.utils.AuthToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final JwtEncoder jwtEncoder;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(JwtEncoder jwtEncoder, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping()
    public ResponseEntity<TokenLoginDTO> login(@RequestBody LoginDTO loginRequest) {
        var user = userService.findByUsername(loginRequest.username());
        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid!");
        }
        BuildTokenData result = AuthToken.getBuildTokenData(user.get());
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(result.claims())).getTokenValue();
        return ResponseEntity.ok(new TokenLoginDTO(jwtValue, result.expiresIn()));
    }

}
