package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Results( id = "result",value =   {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType =java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findByUserId"))
    })
    @Select("select * from users where username = #{username}")
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;



    @ResultMap("result")
    @Select("select * from users where id = #{id}")
    UserInfo findById(String id) throws Exception;



    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId} )")
    List<Role> findOtherRole(String userId) throws Exception;


    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
