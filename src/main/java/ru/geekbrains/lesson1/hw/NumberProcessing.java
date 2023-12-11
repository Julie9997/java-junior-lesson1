package ru.geekbrains.lesson1.hw;

import java.util.Arrays;
import java.util.List;

public class NumberProcessing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 14, 5, 8, 7, 2, 5, 9, 10, 3);

        double average = numbers.stream().filter(number -> number % 2 == 0)
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);

        System.out.println(average);
    }
}
