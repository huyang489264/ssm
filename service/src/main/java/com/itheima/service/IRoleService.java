package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {


    List<Role> findAll(int page, int size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermission(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] ids) throws Exception;
}
