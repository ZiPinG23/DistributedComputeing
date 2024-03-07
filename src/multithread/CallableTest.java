package multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws Exception {

        MyThread mt = new MyThread();

        FutureTask<Integer> result = new FutureTask<Integer>(mt);

        new Thread(result).start();

        // 获取运算结果是同步过程，即 call 方法执行完成，才能获取结果
        Integer sum = result.get();

        System.out.println(sum);
    }
}
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 1; i <= 100; i++) {
            sum += i;
        }

        return sum;
    }

}

