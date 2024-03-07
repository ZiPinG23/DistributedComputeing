package homework1;

import java.io.Serializable;

public class Operation implements Serializable {
    private String id;
    private double money;
    private int type;
    //1表示充值,
    // 2表示消费,
    // 3表示充值成功,
    // 4表示消费成功,
    // 5表示消费失败余额不足,
    // 6表示结束操作,
    // 7表示卡号不存在

    public Operation() {
    }

    public Operation(String id, double money, int type) {
        this.id = id;
        this.money = money;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "卡号:"+id+(type == 1 ?"重置":"消费")+money+"元";
    }
}
