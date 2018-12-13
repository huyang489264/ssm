package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ControllerProduct {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue ="4") Integer size) throws Exception {

        ModelAndView view = new ModelAndView();
        List<Product> list = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        view.addObject("pageInfo",pageInfo);
        view.setViewName("product-list");
        return view;
    }

    /**
     * 保存
     * @param product
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }
}
