package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class ControllerRole {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有角色
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        List<Role> roleList = roleService.findAll(page,size);
        ModelAndView view = new ModelAndView();
        PageInfo pageInfo = new PageInfo(roleList);
        view.addObject("pageInfo",pageInfo);
        view.setViewName("role-list");
        return view;
    }

    /**
     * 增加角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }


    /**
     * 查询该角色可添加的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        ModelAndView view = new ModelAndView();

       Role role = roleService.findById(roleId);

        List<Permission> permissions = roleService.findOtherPermission(roleId);
        view.addObject("role",role);
        view.addObject("permissionList",permissions);

        view.setViewName("role-permission-add");

        return view;
    }


    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId, String[] ids) throws Exception{
        roleService.addPermissionToRole(roleId,ids);

        return "redirect:findAll.do";
    }

    /*@RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        ModelAndView view = new ModelAndView();

        Role role = roleService.findById(roleId);


    }*/
}
