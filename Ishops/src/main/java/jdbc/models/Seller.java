package jdbc.models;

import java.util.List;

public class Seller extends BasicUser {

    private Integer number_deals;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Seller(){}
    public Seller(Integer account,
                  String keywords,
                  String lastname,
                  String firstname,
                  String username, Integer number_deals){
        super( account,
                 keywords,
                 lastname,
                 firstname,
                 username);
        this.number_deals = number_deals;
    }
    public Integer getNumber_deals() {
        return number_deals;
    }

    public void setNumber_deals(Integer number_deals) {
        this.number_deals = number_deals;
    }
}
