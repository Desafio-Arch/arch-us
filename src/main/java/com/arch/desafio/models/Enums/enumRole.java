package com.arch.desafio.models.Enums;

import lombok.Getter;

@Getter
public enum enumRole {
    ADMIN(1L),
    BASIC(2L);
    final long roleId;
    enumRole(long roleId) {
        this.roleId = roleId;
    }
}
