package com.cdolinta.controller;

import com.cdolinta.model.dto.UserDto;

import com.cdolinta.service.RoleService;
import com.cdolinta.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

//    @GetMapping
//    public String listPage(@RequestParam(required = false) String usernameFilter,
//                           @RequestParam(required = false) String emailFilter,
//                           Model model) {
//        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
//        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";
//        model.addAttribute("users", userRepository.usersByFilter(usernameFilter, emailFilter));
//        return "user";
//    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String usernameFilter,
                           @RequestParam(required = false) String emailFilter,
                           @RequestParam(required = false) Optional<Integer> page,
                           @RequestParam(required = false) Optional<Integer> size,
                           @RequestParam(required = false) Optional<String> sortField,
                           Model model) {
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
//        model.addAttribute("users", service.findAllByFilter(usernameFilter, emailFilter, page, size));

        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";

        model.addAttribute("users", userService.findAllByFilter(usernameFilter, emailFilter, pageValue, sizeValue, sortFieldValue));
        return "user";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userService.findUserById(id).orElseThrow(() -> new RuntimeException("User not found !!!")));
        return "user_form";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserDto());
        return "user_form";
    }
    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }
    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping
    public String saveUser(@Valid @ModelAttribute(value = "user") UserDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        if (!dto.getPassword().equals(dto.getMatchingPassword())) {
            bindingResult.rejectValue("password", "Password dont match");
        }
        userService.save(dto);
        return "redirect:/user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute(value = "user") UserDto dto) {
        userService.save(dto);
        return "redirect:/user";
    }


}