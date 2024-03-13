package com.york.competition.c2018.boogle;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public record Node(int row, int col) {}
    public static List<String> words = new ArrayList<>();
    public static void main(String[] args) {
        List<char[][]> cases = readUserInput();
        cases.stream().map(Solution::solveCase)
                .forEach(System.out::println);
    }

    public static List<char[][]> readUserInput() {
        List<char[][]> returnVal = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String current = scanner.nextLine();
        while (!current.equals("END")) {
            words.add(current);
            current = scanner.nextLine();
        }

        int row = scanner.nextInt();
        int col = -1;
        while (row != -1) {
            col = scanner.nextInt();
            char[][] case_ = new char[row][col];
            for (int i = 0; i < row; i++) {
                String curRow = scanner.next();
                for (int j = 0; j < col; j++) {
                    case_[i][j] = curRow.charAt(j);
                }
            }
            returnVal.add(case_);
            row = scanner.nextInt();
        }
        assert col != -1;

        return returnVal;
    }

    public static String solveCase(char[][] matrix) {
        List<List<Node>> paths = new ArrayList<>();
        for (String word : words) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    tryDFS(matrix, word, paths, new Node(i, j), new HashSet<>(), new ArrayList<>(), 0);
                }
            }
        }
        return formatPaths(paths);
    }

    public static void tryDFS(char[][] matrix, String word, List<List<Node>> paths,
                              Node root, Set<Node> visited, List<Node> curPath, int curWordInd) {

        if (root.row < 0 || root.row >= matrix.length || root.col < 0 || root.col >= matrix[0].length ||
                visited.contains(root) || matrix[root.row][root.col] != word.charAt(curWordInd))  return;

        visited.add(root);
        curPath.add(root);

        if (curWordInd == word.length()-1) {
            paths.add(new ArrayList<>(curPath));
        } else {
            tryDFS(matrix, word, paths, new Node(root.row+1, root.col+1), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row+1, root.col), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row+1, root.col-1), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row, root.col+1), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row, root.col-1), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row-1, root.col+1), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row-1, root.col), visited, curPath, curWordInd+1);
            tryDFS(matrix, word, paths, new Node(root.row-1, root.col-1), visited, curPath, curWordInd+1);
        }

        visited.remove(root);
        curPath.remove(root);
    }

    public static String formatPaths(List<List<Node>> paths) {
        return "Solutions:\n" + paths.stream()
                .sorted((l1, l2) -> {
                    int shortest = Math.min(l1.size(), l2.size());
                    for (int i = 0; i < shortest; i++) {
                        Node n1 = l1.get(i);
                        Node n2 = l2.get(i);
                        int compareReturn = n1.row - n2.row;
                        if (compareReturn != 0) return compareReturn;
                        else {
                            compareReturn = n1.col - n2.col;
                            if (compareReturn != 0) return compareReturn;
                        }
                    }
                    return l1.size() - l2.size();
                })
                .map(path -> path.stream()
                        .map(node -> ("" + (char) ('A' + node.row)) + node.col)
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
    }
}
