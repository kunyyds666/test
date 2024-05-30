import java.util.*;
import java.util.stream.Collectors;

public class _04StreamTerminationMethod {
    public static void main(String[] args) {

        //foreach   遍历
        //count     计数
        //toArray   收集流放到数组中
        //collect   收集流放到集合中

        ArrayList<String> list = new ArrayList<>();

        list.add("张无忌");
        list.add("立储峰");
        list.add("ABC");

        //foreach   遍历
        list.stream().forEach(System.out::println);

        //count     计数
        long count = list.stream().count();
        System.out.println(count);

        //toArray   收集流放到数组中
        Object[] array = list.stream().toArray();
        System.out.println(Arrays.toString(array));
        //String[] array1 = list.stream().toArray(value -> new String[value]);
        String[] array1 = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(array1));

        //collect
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("张无忌-男-21");
        list1.add("立储峰-女-18");
        list1.add("ABC-男-20");

        //需求收集男性到list集合
        List<String> arr1 = list1.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .toList();//.collect(Collectors.toList());
        System.out.println(arr1);
        //收集男性到set集合
        Set<String> arr2 = list1.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toSet());
        System.out.println(arr2);


        //收集男性到map集合，key 姓名 value 年龄 toMap中两个参数键的规则，值的规则
        Map<String, Integer> arr3 = list1.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2])));
        System.out.println(arr3);


    }
}
