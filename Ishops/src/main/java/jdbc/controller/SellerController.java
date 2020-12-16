package jdbc.controller;

import jdbc.daos.ItemDao;
import jdbc.daos.SellerDao;
import jdbc.models.Item;
import jdbc.models.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SellerController {
    @Autowired
    SellerDao sellerDao;
    @Autowired
    ItemDao itemDao;

    @RequestMapping("/sellerLogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password =request.getParameter("password");
        Seller seller = sellerDao.login(username,password);
        if(seller!=null){
            request.getSession().setAttribute("seller",seller);
            List<Item> list = itemDao.getItemsByIdSellerId(seller.getId());
            request.getSession().setAttribute("myProducts",list);
            return "sellerIndex";
        }else{
            return "sellerLogin";
        }
    }
    @RequestMapping("/sellerRegister")
    public String sellerRegister(HttpServletRequest request){
        String username = request.getParameter("username");
        String keywords =request.getParameter("keywords");
        String lastName = request.getParameter("lastName");
        String firstName =request.getParameter("firstName");
        Seller seller = new Seller(0,keywords,lastName,firstName,username,0);
        sellerDao.add(seller);
        return "sellerLogin";
    }
    @RequestMapping("/addItem")
    public String addItem(HttpServletRequest request){
        Seller seller = (Seller)request.getSession().getAttribute("seller");
        String name =request.getParameter("name");
        String price =request.getParameter("price");
        String instock =request.getParameter("instock");
        String discription =request.getParameter("discription");
        Item item = new Item(0,name,discription,Integer.parseInt(price),Integer.parseInt(instock),seller.getId());
        itemDao.addItem(item);
        List<Item> list = itemDao.getItemsByIdSellerId(seller.getId());
        request.getSession().setAttribute("myProducts",list);
        return "sellerIndex";
    }
    @RequestMapping("/deleteItem")
    public String deleteItem(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("itemId"));
        itemDao.deleteById(id);
        Seller seller = (Seller)request.getSession().getAttribute("seller");
        List<Item> list = itemDao.getItemsByIdSellerId(seller.getId());
        request.getSession().setAttribute("myProducts",list);
        return "sellerIndex";
    }  @RequestMapping("/updateItem")
    public String updateItem(HttpServletRequest request){
        Seller seller = (Seller)request.getSession().getAttribute("seller");
        int id = Integer.parseInt(request.getParameter("id"));
        String name =request.getParameter("name");
        String price =request.getParameter("price");
        String instock =request.getParameter("instock");
        String discription =request.getParameter("discription");
        Item item = new Item(id,name,discription,Integer.parseInt(price),Integer.parseInt(instock),seller.getId());
        itemDao.updateItem(item);
        List<Item> list = itemDao.getItemsByIdSellerId(seller.getId());
        request.getSession().setAttribute("myProducts",list);
        return "sellerIndex";
    }


}
