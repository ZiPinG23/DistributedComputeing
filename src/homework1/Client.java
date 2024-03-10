package homework1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    //1表示充值,
    // 2表示消费,
    // 3表示充值成功,
    // 4表示消费成功,
    // 5表示消费失败余额不足,
    // 6表示结束操作,
    // 7表示卡号不存在
    public static void main(String[] args) {
        String id;
        double money;
        Socket server = null;
        int type=0;
        try {
            server = new Socket("127.0.0.1", 4444);// 向本机4444端口发出客户请求
            BufferedReader sin = new BufferedReader(new InputStreamReader(
                    System.in));
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            while (type!=3){
                System.out.println("请选择服务:1.充值 2.消费 3.退出");
                type= Integer.parseInt(sin.readLine());
                if(type==1){
                    System.out.println("请输入您的卡号:");
                    id=sin.readLine();
                    System.out.println("请输入重置金额:");
                    money= Double.parseDouble(sin.readLine());
                    Operation operation=new Operation(id,money,type);
                    out.writeObject(operation);
                    out.flush();
                    operation = (Operation) in.readObject();
                    if(operation.getType()==3){
                        System.out.println("充值成功,您的卡号余额为"+operation.getMoney());
                    }
                }
                else if(type==2){
                    System.out.println("请输入您的卡号:");
                    id=sin.readLine();
                    System.out.println("请输入消费金额:");
                    money= Double.parseDouble(sin.readLine());
                    Operation operation=new Operation(id,money,type);
                    out.writeObject(operation);
                    out.flush();
                    operation = (Operation) in.readObject();
                    if(operation.getType()==4){
                        System.out.println("消费成功，账户余额为："+operation.getMoney());
                    }
                    else if(operation.getType()==7){
                        System.out.println("消费失败，卡号不存在！");
                    }
                    else if(operation.getType()==5){
                        System.out.println("消费失败，卡内余额不足，余额为:"+operation.getMoney());
                    }
                }
                else if(type==3){
                    break;
                }
                else {
                    System.out.println("请选择正确操作！！！");
                }
            }
            out.close();
            in.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
