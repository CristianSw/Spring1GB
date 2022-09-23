package com.cdolinta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank(message = "can not be empty!!!")
    @Column(nullable = false, unique = true)
    private String username;

    //    @NotBlank
    //    @Email
    @Column(nullable = false, unique = true)
    private String email;

    //    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple")
    @Column(nullable = false, length = 1024)
    private String password;

    public User(String username) {
        this.username = username;
    }
}