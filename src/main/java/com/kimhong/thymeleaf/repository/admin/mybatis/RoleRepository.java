package com.kimhong.thymeleaf.repository.admin.mybatis;

import com.kimhong.thymeleaf.model.Role;
import com.kimhong.thymeleaf.repository.admin.mybatis.provider.RoleProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {
    @SelectProvider(type = RoleProvider.class, method = "selectRoleSql")
    List<Role> select();
}
