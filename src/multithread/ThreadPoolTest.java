package multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建 5 个线程
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {

            Future<String> future = service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return Thread.currentThread().getName() + ":hello world";
                }
            });

            list.add(future);
        }
// 打印结果
        for (Future<String> future : list) {
            System.out.println(future.get());
        }
// 关闭线程池
        service.shutdown();

        }
}
