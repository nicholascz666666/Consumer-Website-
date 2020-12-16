package jdbc.models;


public class Address {

    private Integer id;

    private boolean defau;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private Integer zipcode;

    private Customer customer;

    public Address(Integer id, boolean defau, String address1,
                   String address2,
                   String city,
                   String state,
                   String countr,
                   Integer zipcode) {
        this.id = id;
        this.defau = defau;
        this.address1 =address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = countr;
        this.zipcode = zipcode;
    }
    public Address(){}

    @Override
    public  String toString(){return id+ ","+ address1+ "," + address2+ ","+ city+ ","+state+ ","+country+ ","+zipcode;}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDefau() {
        return defau;
    }

    public void setDefau(boolean defau) {
        this.defau = defau;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
