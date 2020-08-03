package com.kimhong.thymeleaf.service.Imp;

import com.kimhong.thymeleaf.model.Role;
import com.kimhong.thymeleaf.repository.admin.mybatis.RoleRepository;
import com.kimhong.thymeleaf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> select() {
        return roleRepository.select();
    }
}
