package com.york.competition.c2021.boredOfTheRings;

import java.util.*;

/**
 * @author david & jacob york
 */
public class Solution {

    public static void main(String[] args) {
        List<Map<Integer, List<Integer>>> adjMatrixs = readUserInput();
        adjMatrixs.stream()
                .map(Solution::solveCase)
                .forEach(System.out::println);
    }

    public static List<Map<Integer, List<Integer>>> readUserInput() {
        List<Map<Integer, List<Integer>>> adjMatrixs = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int iters = scanner.nextInt();
        for (int i = 0; i < iters; i++) {
            Map<Integer, List<Integer>> matrix = new HashMap<>();
            int hallwayCnt = scanner.nextInt();

            for (int j = 0; j < hallwayCnt; j++) {
                int node1 = scanner.nextInt();
                int node2 = scanner.nextInt();
                if (!matrix.containsKey(node1)) matrix.put(node1, new ArrayList<>());
                if (!matrix.containsKey(node2)) matrix.put(node2, new ArrayList<>());

                matrix.get(node1).add(node2);
            }

            adjMatrixs.add(matrix);
        }

        return adjMatrixs;
    }

    public static String solveCase(Map<Integer, List<Integer>> adjMatrix) {
        return graphDFS(adjMatrix, 0, "#0#", true) ? "Yes" : "No";
    }

    public static boolean graphDFS(Map<Integer, List<Integer>> adjMatrix, int root, String visited, boolean gate) {
        for (int child : adjMatrix.get(root)) {
            if (!visited.contains("#" + child + "#")) {
                if (graphDFS(adjMatrix, child, visited + "#" + child + "#", true)) {
                    return true;
                }
            } else if (gate) {
                if (graphDFS(adjMatrix, child, visited, false)) {
                    return true;
                }
            }
        }
        return root == 0 && adjMatrix.size() == visited
                .replace("##", " ")
                .replace("#", "")
                .split(" ")
                .length;
    }
}
