package com.klimoshenko.ccscne2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        List<List<Integer>> inputs = readTestCases();

        inputs.stream()
                .map(Problem1::processTestCase)
                .forEach(System.out::println);
    }

    private static final int MINIMUM_HEATWAVE_TEMP = 80;

    private static int processTestCase(List<Integer> testCase) {
        int longest = 0;

        int current = 0;

        for (int n : testCase) {
            if (n >= MINIMUM_HEATWAVE_TEMP) {
                current++;
            } else {
                current = 0;
            }

            if (current >= longest) {
                longest = current;
            }
        }

        return longest;
    }

    private static final int SENTINEL_STOP_LINES = 0;
    private static final int SENTINEL_STOP_LINE = -1;

    private static List<List<Integer>> readTestCases() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<List<Integer>> result = new ArrayList<>();

            while (true) {
                int firstNumber = scanner.nextInt();
                if (firstNumber == SENTINEL_STOP_LINES) {
                    break;
                }

                List<Integer> line = new ArrayList<>();
                line.add(firstNumber);

                int n;
                while ((n = scanner.nextInt()) != SENTINEL_STOP_LINE) {
                    line.add(n);
                }

                result.add(line);
            }

            return result;
        }
    }
}
