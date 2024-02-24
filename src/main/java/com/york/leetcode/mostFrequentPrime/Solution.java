package com.york.leetcode.mostFrequentPrime;

import java.util.*;
import java.util.stream.IntStream;

class Solution {

    public static final int NORTH = -1;
    public static final int SOUTH = 1;
    public static final int EAST = 1;
    public static final int WEST = -1;
    public static final int NONE = 0;

    public static void main(String[] args) {
        List<int[][]> cases = readUserInput();
        cases.stream()
                .map(Solution::mostFrequentPrime)
                .forEach(System.out::println);
    }

    public static int mostFrequentPrime(int[][] mat) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int[][] directions = {{NORTH, NONE}, {NORTH, EAST}, {NONE, EAST},
                {SOUTH, EAST}, {SOUTH, NONE}, {SOUTH, WEST}, {NONE, WEST}, {NORTH, WEST}};

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                for (int[] direction : directions) {
                    getValidNumsInVector(mat, i, j, direction, frequency);
                }
            }
        }

        return frequency.isEmpty() ? -1 : getModes(frequency).stream()
                .max(Comparator.naturalOrder())
                .get();
    }

    public static boolean isOutOfBounds(int r, int c, int[][] mat) {
        return r < 0 || c < 0 || r >= mat.length || c >= mat[0].length;
    }

    public static List<Integer> getModes(Map<Integer, Integer> map) {
        List<Integer> modes = new ArrayList<>();

        int maxReps = map.values().stream()
                .max(Comparator.naturalOrder())
                .get();

        for (int key : map.keySet()) {
            if (map.get(key) == maxReps) {
                modes.add(key);
            }
        }
        return modes;
    }

    public static void getValidNumsInVector(int[][] mat, int r, int c, int[] direction, Map<Integer, Integer> frequency) {
        int current = 0;
        int ci = c;
        int ri = r;

        final int VERT = 0;
        final int HORIZ = 1;

        while (!isOutOfBounds(ri, ci, mat)) {
            current *= 10;
            current += mat[ri][ci];
            if (current > 10 && isPrime(current)) {
                frequency.put(current, frequency.getOrDefault(current, 0) + 1);
            }
            ci += direction[HORIZ];
            ri += direction[VERT];
        }
    }

    public static boolean isPrime(int num) {
        return IntStream.range(2, num).noneMatch(i -> num % i == 0);
    }

    private static List<int[][]> readUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<int[][]> cases = new ArrayList<>();

        int m;
        while ((m = scanner.nextInt()) > 0) {
            int n = scanner.nextInt();
            int[][] current = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    current[i][j] = scanner.nextInt();
                }
            }
            cases.add(current);
        }
        return cases;
    }
}