package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "username", nullable = false)
    private String username;

    @Setter
    @Getter
    @Column(name = "email", nullable = false)
    private String email;

    // Изменено на camelCase
    @Setter
    @Getter
    @Column(name = "hash_password", nullable = true)
    private String hashPassword;

    @Column(name = "users_roles", nullable = true)
    private String usersRoles;


}
