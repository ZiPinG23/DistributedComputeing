package lambda;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class TestLambda {
    public static void main(String[] args){
         multiThread();
         System.out.println(Thread.currentThread().getName()); 
        // testSwing();
        // calcMethod(5, n->n++);
    }
    public static void multiThread(){
        //Runnable task =new MyRunnable();
        //  Runnable task = () -> {
        //     System.out.println("一个多线程程序.");
        // };
        Thread thread = new Thread(() -> {
            System.out.println("一个多线程程序.");
        });
        thread.start();
        System.out.println("主线程！");
    }
    public static void testSwing(){
        JFrame frame = new JFrame("Swing Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("click !");
        button.addActionListener(e->{System.out.println("按钮被点击！");});
              
        frame.add(button);
        frame.setVisible(true);
    }    
    public static void calcMethod(int num,Calculator calculator){
       int result=calculator.cal(num);
       System.out.println("计算结果="+result);
   }

}

class ActionEventHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        System.out.println("按钮被点击！");
		
	}

    
}
class MyRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("一个多线程程序.");
	}
}

interface Calculator {
    int cal(int a);
}


   
