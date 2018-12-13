package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import com.itheima.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
       try {
           userInfo = userDao.findByUsername(username);
       }catch (Exception e){

       }
        User user = new User(userInfo.getUsername(),
                userInfo.getPassword(),
                userInfo.getStatus() == 0? false : true,
                true,true,true,
                getAuthority(userInfo.getRoles()));
       return user;
    }



    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
         return  userDao.findAll();
    }

    /**
     * 保存用户
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 查询用户可以添加的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRole(String userId) throws Exception {
        return userDao.findOtherRole(userId);
    }

    /**
     * 遍历添加角色
     * @param userId
     * @param ids
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String roleId : ids) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
