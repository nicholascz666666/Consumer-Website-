package jdbc.controller;

import jdbc.daos.ItemDao;
import jdbc.daos.SellerDao;
import jdbc.models.Item;
import jdbc.models.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemDao itemDao ;
    @Autowired
    SellerDao sellerDao;

    @RequestMapping("/searchItems")
    public String searchItems(HttpServletRequest request, HttpSession session){
        String keywords = request.getParameter("keywords");
        List<Item> list = itemDao.getItemsByKeywords(keywords);
        session.setAttribute("products",list);
        return "list";
    }
    @RequestMapping("/showProductDetail")
    public String showDetail(HttpServletRequest request, HttpSession session){
        int id = Integer.parseInt(request.getParameter("id"));
        Item item = itemDao.getItemById(id);
        Seller seller = sellerDao.getSellerById(item.getSellerId());
        session.setAttribute("item",item);
        session.setAttribute("seller",seller);
        return "product_detail";
    }
}
