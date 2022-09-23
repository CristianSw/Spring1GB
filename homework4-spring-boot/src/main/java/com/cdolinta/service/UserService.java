package com.cdolinta.service;

import com.cdolinta.model.QUser;
import com.cdolinta.model.User;
import com.cdolinta.model.dto.UserDto;
import com.cdolinta.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> findAllByFilter(String usernameFilter, String emailFilter){
        QUser user = QUser.user;
        BooleanBuilder predicate = new BooleanBuilder();

        if (usernameFilter != null && !usernameFilter.isBlank()){
            predicate.and(user.username.contains(usernameFilter.trim()));
        }

        if (emailFilter != null && !emailFilter.isBlank()){
            predicate.and(user.email.contains(emailFilter.trim()));
        }
        return StreamSupport.stream(userRepository.findAll(predicate).spliterator(),true)
                .map(userFromDb -> {
                    UserDto dto = new UserDto();
                    dto.setId(userFromDb.getId());
                    dto.setUsername(userFromDb.getUsername());
                    dto.setEmail(dto.getEmail());
                    dto.setPassword(dto.getPassword());
                    return dto;
                }).collect(Collectors.toList());
    }

    public Optional<UserDto> findUserById(Long id){
        return userRepository.findById(id).map(UserService::userToUserDto);
    }

    public UserDto save(UserDto dto){
        userRepository.save(new User(dto.getId(),dto.getUsername(), dto.getEmail(), dto.getPassword()));
    }

    public static UserDto userToUserDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
