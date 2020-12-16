package jdbc.models;


import java.util.List;


public class Customer extends BasicUser{

    private List<Address> addresses;
    public Customer(){}
    public Customer(Integer account,
                    String keywords,
                    String lastname,
                    String firstname,
                    String username){
        super(account,
                 keywords,
                 lastname,
                 firstname,
                 username);}

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
