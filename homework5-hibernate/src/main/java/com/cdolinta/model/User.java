package com.cdolinta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "Select u from User u"),
        @NamedQuery(name = "countAllUsers", query = "Select count(u) from User u"),
        @NamedQuery(name = "deleteUserById", query = "delete from User u where u.id = :id")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 1024)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    private List<Contact> contacts;

    @OneToOne(mappedBy = "user",
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Customer customer;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
