package com.cdolinta.model.mapper;

import com.cdolinta.model.User;
import com.cdolinta.model.dto.UserDto;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserDtoMapper {
    @Mapping(target = "password", ignore = true)
    UserDto map(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password",target = "password", qualifiedByName = "encode")
    User map(UserDto dto, @Context PasswordEncoder encoder);

    @Named("encode")
    default String encode(String password, @Context PasswordEncoder encoder) {
        return encoder.encode(password);
    }
}
