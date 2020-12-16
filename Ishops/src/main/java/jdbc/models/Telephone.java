package jdbc.models;

public class Telephone {

    private Integer id;
    private Integer phone;
    private boolean defau;
    private TelephoneType type;
    public Telephone(){}
    public Telephone(Integer id,
             Integer phone,
             boolean defau,
             TelephoneType type){
        this.id = id;
        this.phone = phone;
        this.defau = defau;
        this.type = type;
    }
    private BasicUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public boolean isDefau() {
        return defau;
    }

    public void setDefau(boolean defau) {
        this.defau = defau;
    }

    public TelephoneType getType() {
        return type;
    }

    public void setType(TelephoneType type) {
        this.type = type;
    }

    public BasicUser getUser() {
        return user;
    }

    public void setUser(BasicUser user) {
        this.user = user;
    }

}
