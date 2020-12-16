package jdbc.controller;

import jdbc.daos.CustomerDao;
import jdbc.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    CustomerDao cd ;

    @RequestMapping("/userLogin")
    public String userLogin(HttpServletRequest request, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = cd.login(username,password);
        if(customer==null){
            return "login";
        }else{
            session.setAttribute("customer",customer);
            return "index";
        }
    }

    @RequestMapping("/userRegister")
    public String userRegister(HttpServletRequest request){
        String username = request.getParameter("username");
        String keywords = request.getParameter("keywords");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Customer customer = new Customer(0,keywords,lastName,firstName,username);
        cd.add(customer);
        return "login";
    }



}
