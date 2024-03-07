package homework1;
import java.io.*;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    final static int MaxConnNum = 5;
    static int CurConnNum = 0;
    static String fileName ="./data/data.txt";
    public static void main(String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(MaxConnNum);
        try {
            ServerSocket server = null;
            try {
                server = new ServerSocket(4444); // 创建一个ServerSocket在端口4444监听客户请求
            } catch (Exception e) {
                System.out.println("Error:" + e);// 屏幕打印出错信息
                System.exit(-1);
            }
            while (true) {
                Socket client = null;
                try {
                    client = server.accept(); // 使用accept()阻塞等待客户请求，有客户请求
                    // 到来则产生一个Socket对象
                } catch (Exception e) {
                    System.out.println("接受请求失败!");
                    System.exit(-1);
                }
                final Socket finalClient = client; // 声明为final
                executor.submit(() -> {
                    try {
                        //1表示充值,
                        // 2表示消费,
                        // 3表示充值成功,
                        // 4表示消费成功,
                        // 5表示消费失败余额不足,
                        // 6表示结束操作,
                        // 7表示消费卡号不存在
                        Data data = null;
                        String id;
                        int money;
                        ObjectOutputStream out = new ObjectOutputStream(finalClient.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(finalClient.getInputStream());
                        Operation operation=null;
                        operation = (Operation) in.readObject();
                        while ((operation)!=null && operation.getType()!=6){
                            if(operation.getType()==1){//表示充值操作
                              id=operation.getId();
                              data=FileIO.search(id,fileName);
                              if (data == null) {
                                  data = new Data();
                                  data.setId(id);
                                  data.setMoney(operation.getMoney());
                              } else {
                                  data.setMoney(data.getMoney() + operation.getMoney());
                              }
                              FileIO.record(data,fileName);
                              operation.setMoney(data.getMoney());
                              operation.setType(3);
                            }
                            else if(operation.getType()==2){//表示消费操作
                                id=operation.getId();
                                data=FileIO.search(id,fileName);
                                if(data==null){
                                    operation.setType(7);
                                }
                                else {
                                    if(data.getMoney()>=operation.getMoney()){
                                        data.setMoney(data.getMoney()- operation.getMoney());
                                        FileIO.record(data,fileName);
                                        operation.setType(4);
                                        operation.setMoney(data.getMoney()- operation.getMoney());
                                    }
                                    else {
                                        operation.setType(5);
                                        operation.setMoney(data.getMoney());
                                    }
                                }
                            }
                            out.writeObject(operation);
                            out.flush();
                        }
                        finalClient.close();
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                });
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            executor.shutdown();
        }
    }
}