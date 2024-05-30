import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class _02StreamInMethod {
    public static void main(String[] args) {
        //获取方法
        //单列集合  default Stream<E> stream  Collection中的默认方法
        //双列集合  无keySet（），entrySet                        无法直接使用stream流
        //数组      psvm                     Arrays工具类中的静态方法
        //零散数据     of                     stream中的静态方法

        //单列集合
        ArrayList<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("立储峰");
        list.add("ABC");
        list.stream().filter(name -> name.length() ==3).forEach(System.out::println);

        //双列集合
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("aaa",111);
        hashMap.put("bbb",222);
        hashMap.put("ccc",333);
        hashMap.keySet().stream().filter(name->name.length()==3).forEach(System.out::println);

        //数组
        int[] arr1  = {1,2,2,3,3,434,3};
        Arrays.stream(arr1).forEach(System.out::println);
        String[] arr2 = {"fsa","asd","vsd"};
        Arrays.stream(arr2).forEach(System.out::println);

        //零散数据需要同种数据类型
        Stream.of(1,42,453,12,56,67,3).forEach(System.out::println);
        Stream.of("a","b","v","asd","asd","ffs").forEach(System.out::println);
        //方法形参是一个可变参数，可以传递数组，只能传递引用数据类型，不能是基本数据，否则会把数组方程一个元素
        Stream.of(arr2).forEach(System.out::println);

        Stream.of(arr1).forEach(System.out::println);

    }
}
