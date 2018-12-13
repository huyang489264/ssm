package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll(int page, int size) throws Exception;

    void save(Permission permission) throws Exception;
}
