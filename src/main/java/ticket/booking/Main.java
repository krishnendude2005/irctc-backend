package ticket.booking;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> l1 = list.stream().filter(isEven()).toList();
        List<Integer> ls = list.stream().map(apply()).toList();
        for(Integer i : l1){
            System.out.println(i);
        }

    }
    public static Predicate <Integer> isEven() {
        return i -> i%2 == 0;
    }
    static Function<Integer, Integer> apply() {
        return i -> i*i;
    }
}