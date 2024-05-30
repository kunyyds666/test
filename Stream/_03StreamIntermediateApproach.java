import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

public class _03StreamIntermediateApproach {
    public static void main(String[] args) {
        //filter    过滤
        //limit     获取前几个元素
        //skip      跳过前几个元素
        //distinct  元素去重，依赖（hashcode和equals方法）
        //concat    合并a和b两个流为一个liu
        //map       转换流中的数据类型

        ArrayList<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("张无忌");
        list.add("立储峰");
        list.add("ABC");

        //stream流只能使用一次
        //filter
        list.stream()
                .filter(name -> name.length()==3)
                .forEach(System.out::println);

        //limit
        list.stream()
                .limit(2)
                .forEach(System.out::println);

        //skip
        list.stream()
                .skip(2)
                .forEach(System.out::println);

        //distinct
        list.stream()
                .distinct()
                .forEach(System.out::println);

        //concat
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("123");

        Stream.concat(list.stream(),list1.stream())
                .forEach(System.out::println);

        //map
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("张无忌-21");
        list2.add("张无忌-22");
        list2.add("立储峰-23");
        list2.add("ABC-32");

        //流中原本数据类型，要转换的数据类型
//        list2.stream().map(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                String[] arr = s.split("-");
//                String ageString = arr[1];
//                return Integer.parseInt(ageString);
//            }
//        }).forEach(System.out::println);

        list2.stream()
                .map(s -> Integer.parseInt(s.split("-")[1]))
                .forEach(System.out::println);



    }
}
