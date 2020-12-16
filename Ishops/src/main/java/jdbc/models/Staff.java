package jdbc.models;

public class Staff extends BasicUser{

    private Integer start;
    private Integer end;
    public Staff(){}
    public Staff(Integer account,
                 String keywords,
                 String lastname,
                 String firstname,
                 String username,Integer start, Integer end
    ) {super( account,
            keywords,
            lastname,
            firstname,
            username);
    this.start = start;
    this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
