package homework1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileIO {
        private static final Lock lock = new ReentrantLock();

    static Data search(String id, String filename) {
        lock.lock();
        try {
            var in = Files.newInputStream(Path.of("./src/homework1/" + filename));
            var scanner = new Scanner(in);

            while (scanner.hasNext()) {
                var file_id = scanner.next();
                var file_money = scanner.nextDouble();
                if (file_id.equals(id)) {
                    lock.unlock();
                    return new Data(id, file_money);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return null;
    }

        static void record(Data data,String filename){
        lock.lock();
        var path = Path.of("./src/homework1/" + filename);
        try {
            List<Data> all_data = new ArrayList<>();

            var in = Files.newInputStream(path);
            var scanner = new Scanner(in);

            var is_found = false;
            while (scanner.hasNext()) {
                var file_id = scanner.next();
                var file_money = scanner.nextDouble();

                if (file_id.equals(data.getId())) {
                    all_data.add(data);
                    is_found = true;
                } else {
                    all_data.add(new Data(file_id, file_money));
                }
            }

            if (!is_found) {
                all_data.add(data);
            }

            var out = Files.newOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
            // 创建 BufferedWriter 对象，用于缓冲字符写入
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);

            for (var i : all_data) {
                writer.write(i.getId() + " " + i.getMoney());
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lock.unlock();
    }
//    public static void record(Data data, String filename) {
//        var path = Path.of("./src/homework1/" + filename);
//        try {
//
//                List<Data> all_data = new ArrayList<>();
//
//                try (var in = Files.newInputStream(path);
//                     var scanner = new Scanner(in)) {
//                    var is_found = false;
//                    while (scanner.hasNext()) {
//                        var file_id = scanner.next();
//                        var file_money = scanner.nextDouble();
//
//                        if (file_id.equals(data.getId())) {
//                            all_data.add(data);
//                            is_found = true;
//                        } else {
//                            all_data.add(new Data(file_id, file_money));
//                        }
//                    }
//
//                    if (!is_found) {
//                        all_data.add(data);
//                    }
//                }
//            synchronized (lock) {
//                try (var out = Files.newOutputStream(path);
//                     OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
//                     BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
//                    Thread.sleep(3000);
//
//                    for (var i : all_data) {
//                        writer.write(i.getId() + " " + i.getMoney());
//                        writer.write("\n");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
