package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Results({
            @Result(id = true,property ="id",column ="id"),
            @Result(property ="roleName",column = "roleName"),
            @Result(property ="roleDesc",column = "roleDesc"),
            @Result(property ="permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findById")),
    })
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    List<Role> findByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll() throws Exception;


    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;


    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId) throws Exception;


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId} )")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission values (#{permission},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permission") String permission) throws Exception;
}
