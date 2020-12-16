package jdbc.models;

public class Order  {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private Priviledge role;
    private Integer itemNum;
    private Integer sum;
    private Item item;

    public Order(){}
    public Order(Integer id,Priviledge role, Integer itemNum,Item item){
        this.id = id;
        this.item = item;
        this.role =role;
        this.itemNum = itemNum;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public Priviledge getRole() {
        return role;
    }

    public void setRole(Priviledge role) {
        this.role = role;
    }


    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }


}
