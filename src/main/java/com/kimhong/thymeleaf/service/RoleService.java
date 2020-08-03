package com.kimhong.thymeleaf.service;

import com.kimhong.thymeleaf.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    List<Role> select();
}
