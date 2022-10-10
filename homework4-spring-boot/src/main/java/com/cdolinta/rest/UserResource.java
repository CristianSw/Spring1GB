package com.cdolinta.rest;

import com.cdolinta.model.dto.UserDto;
import com.cdolinta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

    @GetMapping("/{id}")
    public UserDto form(@PathVariable("id") long id, Model model) {
        UserDto userDto = service.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userDto;
    }


    @GetMapping
    public List<UserDto> listPage(@RequestParam(required = false) String usernameFilter,
                                  @RequestParam(required = false) String emailFilter,
                                  @RequestParam(required = false) Optional<Integer> page,
                                  @RequestParam(required = false) Optional<Integer> size,
                                  @RequestParam(required = false) Optional<String> sortField,
                                  Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";

        Page<UserDto> allByFilter = service.findAllByFilter(usernameFilter, emailFilter, pageValue, sizeValue, sortFieldValue);
        List<UserDto> users = allByFilter.get().toList();
        return users;
    }


    @PostMapping
    public UserDto saveUser(@RequestBody UserDto dto) {
        if (dto.getId() != null){
            throw new RuntimeException("Created user shouldn't have ID");
        }
            service.save(dto);
        return dto;
    }

}
