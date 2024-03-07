package homework1;

import java.io.Serializable;

public class Data implements Serializable {
    private String id;
    private double money;

    public Data(String id, int money) {
        this.id = id;
        this.money = money;
    }

    public Data() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
