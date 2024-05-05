package com.arch.desafio.controllers.dto;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;

public record BuildTokenData(long expiresIn, JwtClaimsSet claims) {
}
