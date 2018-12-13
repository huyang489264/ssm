package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class ControllerOrders {
    @Autowired
    private IOrdersService ordersService;


    /**
     * 查询全部订单
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue ="4") Integer size) throws Exception {

        List<Orders> list = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView view = new ModelAndView();
        view.addObject("pageInfo",pageInfo);
        view.setViewName("orders-list");
        return view;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId) throws Exception{
        Orders orders = ordersService.findById(ordersId);
        ModelAndView view = new ModelAndView();
        view.addObject("orders",orders);
        view.setViewName("orders-show");
        return view;
    }
}
