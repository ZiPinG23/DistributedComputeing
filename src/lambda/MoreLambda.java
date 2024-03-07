package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class MoreLambda {
    public static void main(String[] args) {
        // 循环打印数组
        String[] students = {"Zhangsan", "Lisi"};
        List<String> studentsList = Arrays.asList(students);

// 以前的循环方式
        for (String player : studentsList) {
            System.out.print(player + "; ");
        }
//改进用匿名类
        Consumer<String> consumer=new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s+";");
            }
        };
        studentsList.forEach(consumer);

// 使用 lambda 表达式以及函数操作(functional operation)
        studentsList.forEach((x) -> System.out.print(x + "; "));



// 排序
// 1.使用匿名内部类根据 name 排序 studentsList
                Arrays.sort(students,new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return (s1.compareTo(s2));
                    }
                });

// 2.使用 lambda expression 排序 studentsList
        Comparator<String> sortByName = ( s1,  s2) -> (s1.compareTo(s2));
        Arrays.sort(students, sortByName);

// 3.也可以采用如下形式:
        Arrays.sort(students, ( s1,  s2) -> (s1.compareTo(s2)));
    }
}
