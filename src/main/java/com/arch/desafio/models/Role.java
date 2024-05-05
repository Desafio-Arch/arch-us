package com.arch.desafio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
