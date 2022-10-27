package com.cdolinta.service;

import com.cdolinta.model.Role;
import com.cdolinta.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private RoleRepository repository;

    public List<Role> findAll(){
        return  repository.findAll();
    }
}
