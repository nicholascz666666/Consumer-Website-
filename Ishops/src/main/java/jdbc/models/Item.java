package jdbc.models;

import java.util.List;

public class Item {
    private Integer id;
    private String name;
    private String discription;
    private Integer price;
    private Integer instock;
    private int sellerId;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Item(){}
    public Item(Integer id,
             String name,
             String discription,
             Integer price,
             Integer instock,int sellerId){
        this.id = id;
        this.name= name;
        this.discription = discription;
        this.price = price;
        this.instock=instock;
        this.sellerId = sellerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getInstock() {
        return instock;
    }

    public void setInstock(Integer stock) {
        this.instock = stock;
    }
}
