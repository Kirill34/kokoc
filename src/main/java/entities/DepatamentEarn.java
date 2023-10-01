package entities;

public class DepatamentEarn {
    private Long departamentId;
    private String departamentName;
    private int money;


    public DepatamentEarn(Long departamentId, String departamentName, int money) {
        this.departamentId = departamentId;
        this.departamentName = departamentName;
        this.money = money;
    }

    public Long getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Long departamentId) {
        this.departamentId = departamentId;
    }

    public String getDepartamentName() {
        return departamentName;
    }

    public void setDepartamentName(String departamentName) {
        this.departamentName = departamentName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
