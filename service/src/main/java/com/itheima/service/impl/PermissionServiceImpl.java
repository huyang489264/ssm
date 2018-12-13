package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements IPermissionService{

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }


    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
