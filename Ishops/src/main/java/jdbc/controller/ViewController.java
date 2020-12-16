package jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/showIndex")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("/showRegister")
    public String showRegister(){
        return "register";
    }
    @RequestMapping("/showCreateOrder")
    public String showCreateOrder(){
        return "order";
    }
    @RequestMapping("/showUserCenter")
    public String showUserCenter(){
        return "userCenter";
    }
    @RequestMapping("/showSellerLogin")
    public String showSellLogin(){
        return "sellerLogin";
    }
    @RequestMapping("/showSellerRegister")
    public String showSellRegister(){
        return "sellerRegister";
    }
    @RequestMapping("/showSellerIndex")
    public String showSellIndex(){
        return "sellerIndex";
    }
    @RequestMapping("/showAddProduct")
    public String showAddProduct(){
        return "addProduct";
    }
    @RequestMapping("/showUpdateItem")
    public String showUpdateItem(){
        return "updateProduct";
    }
}
