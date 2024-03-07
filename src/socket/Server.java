package socket;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) {
        try {
            ServerSocket server = null;
            try {
                server = new ServerSocket(4444); // 创建一个ServerSocket在端口4444监听客户请求
            } catch (Exception e) {
                System.out.println("Error:" + e);// 屏幕打印出错信息
                System.exit(-1);
            }
            Socket client = null;
            try {
                client = server.accept(); // 使用accept()阻塞等待客户请求，有客户请求
                // 到来则产生一个Socket对象
            } catch (Exception e) {
                System.out.println("接受请求失败!");
                System.exit(-1);
            }
            String inputString;
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            PrintWriter os = new PrintWriter(client.getOutputStream());
            // 由Socket对象得到输出流，并构造PrintWriter对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(
                    System.in));
            // 由系统标准输入设备构造BufferedReader对象
            System.out.println("Client发送的消息为:" + is.readLine()); // 在标准输出上打印从客户端读入的字符串
            inputString = sin.readLine(); // 从标准输入读入一字符串
            while (inputString != null && !inputString.trim().equals("quit")) {// 如果该字符串为"quit",则停止循环
                os.println(inputString); // 向客户端输出该字符串
                os.flush(); // 刷新输出流，使Client马上收到该字符串
                System.out.println("Server发送的消息为:" + inputString); // 在屏幕上显示读入的字符串
                System.out.println("Client发送的消息为:" + is.readLine()); // 从Client读入一字符串，并打印到标准输出上
                inputString = sin.readLine(); // 从系统标准输入读入一字符串
            } // 继续循环
            os.close(); // 关闭Socket输出流
            is.close(); // 关闭Socket输入流
            client.close(); // 关闭Socket
            server.close(); // 关闭ServerSocket
            System.out.println("聊天结束!");
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}// 服务器端程序结束

