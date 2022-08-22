package com.cdolinta.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    private Long id;
    private String username;
    public User(String username) {
        this.username = username;
    }
}