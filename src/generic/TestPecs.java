package generic;

import java.util.ArrayList;
import java.util.List;

public class TestPecs {
    static void testProducer(List<? extends Number> list){
        Number number=list.get(0);
        System.out.println(number);
          Number d=0;
       //   list.add(d);
    }
    static void testConsumer(List<? super Double> list){
         // Number number=list.get(0);
        //  System.out.println(number);
        double d=10;
        list.add(d);
    }
    public static void testNolimited(List<?> list){
        //list.add(new Object());
        for (Object object : list) {
            System.out.println(object);
        }
    }
    public static void main(String[] args){
        List<Integer> listInt=new ArrayList<>() ;
        listInt.add(1);
        listInt.add(2);

        testProducer(listInt);

        List<Number> listNum=new ArrayList<>();
        listNum.add(3);
        listNum.add(4);
        testConsumer(listNum);
        
        testNolimited(listNum);
        testNolimited(listInt);

    }

}
