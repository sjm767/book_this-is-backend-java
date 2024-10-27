package practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LambdaTest {

    @Test
    @DisplayName("익명 함수를 함수형 인터페이스로 대체")
    void anonymousFunction() {
        List<String> list = new ArrayList<>();

        list.add("public");
        list.add("static");
        list.add("void");

        list.sort(String::compareTo);
    }

    @Test
    @DisplayName("for문 스트림API로 사용")
    void forEach() {
        List<String> list = new ArrayList<>();

        list.add("public");
        list.add("static");
        list.add("void");

        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("1~10까지의 수 중 2의 배수만으로 이루어진 리스트를 뽑기")
    void listFilter() {
        Integer[] intArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        List<Integer> collect = Arrays.stream(intArray)
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    @Test
    @DisplayName("distinct 예제")
    void distinct() {
        Integer[] intArray = new Integer[]{1,1,1,1,2,2,2,3,3,4};
        List<Integer> list = Arrays.asList(intArray);
        List<Integer> distinctList = list.stream().distinct().toList();

        distinctList.forEach(System.out::println);
    }

    @Test
    @DisplayName("map을 이용하여 모든 소문자를 대문자로 만들기")
    void toUpperCase() {
        String[] lowerCaseArray = {"public", "static", "void"};

        List<String> upperCases = Arrays.stream(lowerCaseArray)
                .map(String::toUpperCase)
                .toList();

        upperCases.forEach(System.out::println);
    }

}
