package com.klimoshenko.ccscne2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem4 {
    public static void main(String[] args) {
        List<List<String>> inputs = readTestCases();

        inputs.stream()
                .map(Problem4::processTestCase)
                .map(result -> result.stream()
                        .map(Object::toString)
                        .toList()
                )
                .map(result -> String.join("-", result))
                .forEach(System.out::println);
        System.out.println("done");
    }

    private static List<Integer> processTestCase(List<String> poem) {
        return poem.stream()
                .map(Problem4::countSyllables)
                .toList();
    }

    private static final Character[] vowels = new Character[]{'a', 'e', 'i', 'o', 'u'};

    private static int countSyllables(String line) {
        return Arrays.stream(line.split(" "))
                .map(word -> {
                    for (char vowel : vowels) {
                        word = word.replace(vowel, ' ');
                    }
                    return (int) Math.max(
                            Arrays.stream(word.split("\\s+"))
                                    .filter(g -> !g.isEmpty())
                                    .count()
                            , 1
                    );
                })
                .reduce(Integer::sum)
                .get();
    }

    private static final String SENTINEL_STOP_LINES = "done";

    private static List<List<String>> readTestCases() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<List<String>> result = new ArrayList<>();

            String line;
            while (!(line = scanner.nextLine()).equals(SENTINEL_STOP_LINES)) {
                List<String> lines = Arrays.stream(line.split("/"))
                        .map(String::trim)
                        .toList();

                result.add(lines);
            }

            return result;
        }
    }
}
