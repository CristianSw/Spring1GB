package com.cdolinta.controller;

import com.cdolinta.model.QUser;
import com.cdolinta.model.User;
import com.cdolinta.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final UserRepository userRepository;

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
                           Model model) {
        QUser user = QUser.user;
        BooleanBuilder predicate = new BooleanBuilder();

        if (usernameFilter != null && !usernameFilter.isBlank()){
            predicate.and(user.username.contains(usernameFilter.trim()));
        }

        if (emailFilter != null && !emailFilter.isBlank()){
            predicate.and(user.email.contains(emailFilter.trim()));
        }
        model.addAttribute("users", userRepository.findAll(predicate));

        return "user";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "user_form";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User(""));
        return "user_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable long id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("password", "Password not match");
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userRepository.save(user);
        return "redirect:/user";
    }


}