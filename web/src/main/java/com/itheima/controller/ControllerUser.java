package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ControllerUser {
    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户
     * page,size两个参数必须是引用类型,LogAop类中要获取方法参数的字节码对象,而基本数据类型没有字节码对象
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue ="4") Integer size) throws Exception{
        ModelAndView view = new ModelAndView();

       List<UserInfo> userList = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userList);
        view.addObject("pageInfo",pageInfo);
        view.setViewName("user-list");

        return view;
    }

    /**
     * 保存用户
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView view = new ModelAndView();
        UserInfo userInfoList = userService.findById(id);
        view.addObject("user",userInfoList);
        view.setViewName("user-show");
        return view;
    }

    /**
     * 查询该用户可以添加的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception{
        UserInfo userInfo = userService.findById(userId);

        List<Role> roles = userService.findOtherRole(userId);


        ModelAndView view = new ModelAndView();

        view.addObject("user",userInfo);
        view.addObject("roleList",roles);

        view.setViewName("user-role-add");
        return view;
    }


    /**
     * 给用户添加角色
     * @param userId
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId, String[] ids) throws Exception{
        userService.addRoleToUser(userId,ids);

        return "redirect:findAll.do";
    }
}
