package jdbc.controller;

import jdbc.daos.OrderDao;
import jdbc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderDao orderDao;
    @RequestMapping("/createOrder")
    public String createOrder(HttpSession session){
        Item item = (Item) session.getAttribute("item");
        Customer customer =(Customer)session.getAttribute("customer");
        orderDao.addOrder(item, customer.getId());
        return "orderSuccess";
    }
    @RequestMapping("/showOrderList")
    public String showOrder(HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> list = orderDao.getAllByUserId(customer.getId());
        System.out.println(list.size());
        session.setAttribute("orders",list);
        return "orderList";
    }

}










