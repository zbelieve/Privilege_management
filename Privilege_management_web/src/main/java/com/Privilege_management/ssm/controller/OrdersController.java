package com.Privilege_management.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.Privilege_management.ssm.domain.Orders;
import com.Privilege_management.ssm.service.IOrdersService;
import com.Privilege_management.ssm.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;
//    //查询全部订单--未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = ordersService.findAll();
//        //绑定要输出的数据,前面的引号是给这个绑定的数据给命名，这样再jsp页面就可以直接使用它
//        mv.addObject("ordersList",ordersList);
//        //输出名字为想要呈现的jsp页面
//        mv.setViewName("orders-list");
//        return mv;
//    }
    //查询全部订单--分页
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size)throws Exception{

        ModelAndView mv = new ModelAndView();
        //这是分页的数据
        List<Orders> ordersList = ordersService.findAll(page,size);
        //使用PageInfo的构造器将ordersList传入，它本来是一个分页的bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    //查询订单详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
