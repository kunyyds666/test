import java.util.ArrayList;

public class _01StreamDemo {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("张无忌");
        list.add("立储峰");
        list.add("ABC");

        list.stream().filter(name->name.startsWith("张")).filter(name -> name.length() == 3).forEach(System.out::println);

    }
}
