package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IRoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permission : ids) {
            roleDao.addPermissionToRole(roleId,permission);
        }
    }
}
