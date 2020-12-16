package jdbc.models;


import java.util.List;

public class BasicUser {
    private Integer id;
    private String keywords;
    private String lastname;
    private String firstname;
    private String username;
    private List<Telephone> telephones;

    public BasicUser(){}
    public BasicUser(Integer account,
             String keywords,
             String lastname,
             String firstname,
             String username){
        this.id = account;
        this.keywords = keywords;
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
    }
    private List<Order> orders;


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer account) {
        this.id = account;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }
}


