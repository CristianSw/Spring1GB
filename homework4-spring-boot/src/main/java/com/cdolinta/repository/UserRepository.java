package com.cdolinta.repository;

import com.cdolinta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUsernameLike(String usernameFilter);
    List<User> findAllByEmailIgnoreCase(String emailFilter);

}
