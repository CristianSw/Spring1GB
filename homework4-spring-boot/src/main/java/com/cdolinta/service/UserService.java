package com.cdolinta.service;

import com.cdolinta.model.User;
import com.cdolinta.model.dto.UserDto;
import com.cdolinta.model.mapper.UserDtoMapper;
import com.cdolinta.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public Page<UserDto> findAllByFilter(String usernameFilter, String emailFilter, int page, int size, String sortField) {
//        QUser user = QUser.user;
//        BooleanBuilder predicate = new BooleanBuilder();
//
//        if (usernameFilter != null && !usernameFilter.isBlank()) {
//            predicate.and(user.username.contains(usernameFilter.trim()));
//        }
//
//        if (emailFilter != null && !emailFilter.isBlank()) {
//            predicate.and(user.email.contains(emailFilter.trim()));
//        }
//        return StreamSupport.stream(userRepository.findAll(predicate, PageRequest.of(page, size)).spliterator(), true)
//                .map(mapper::map);

        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";
        return userRepository.usersByFilter(usernameFilter, emailFilter, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    public Optional<UserDto> findUserById(Long id) {
        return userRepository.findById(id).map(mapper::map);
    }

    public void save(UserDto userDto) {
        userRepository.save(mapper.map(userDto, passwordEncoder));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public org.springframework.security.core.userdetails.User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList())
                )).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
