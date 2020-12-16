package jdbc.controller;

import jdbc.daos.AddressDao;
import jdbc.models.Address;
import jdbc.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AddressController {
    @Autowired
    AddressDao addressDao;
    @RequestMapping("/addAddress")
    public String addAddress(HttpServletRequest request, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        String country = request.getParameter("country");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        Address address = new Address(0,false,address1,address2,city,state,country,Integer.parseInt(zipcode));
        addressDao.addAddress(address,customer.getId());
        return "redirect:showAllAddress";
    }
    @RequestMapping("/showAllAddress")
    public String showAllAddress(HttpServletRequest request, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Address> list = addressDao.getAddressByCid(customer.getId());
        session.setAttribute("addresses",list);
        return "address";
    }

}
