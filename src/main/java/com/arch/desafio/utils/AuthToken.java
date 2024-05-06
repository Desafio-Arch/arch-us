package com.arch.desafio.utils;

import com.arch.desafio.controllers.dto.BuildTokenData;
import com.arch.desafio.models.Role;
import com.arch.desafio.models.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthToken {
    private static final String APP_DESAFIO_ARCH = "APP_DESAFIO_ARCH";

    public static BuildTokenData getBuildTokenData(User user) {
        var now = Instant.now();
        var expiresIn = 5000L;

        var scopes = Optional.of(user).get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer(APP_DESAFIO_ARCH)
                .subject(Optional.of(user).get().getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();
        return new BuildTokenData(expiresIn, claims);
    }
}
