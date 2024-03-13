package com.york.competition.c2016.amazeing;

import java.util.*;
import java.util.stream.Stream;

public class FunctionalSolution {

    public static int ROW = 0;
    public static int COL = 1;
    public record ValidPath(char[][] maze, int min) {}

    public static List<char[][]> readInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<List<char[]>> userInput = new ArrayList<>();

            List<char[]> currentCase = null;
            String line = scanner.nextLine();

            while (!line.equals("-1")) {
                try {
                    Integer.parseInt(line.split(" ")[0]);
                    currentCase = new ArrayList<>();
                    userInput.add(currentCase);
                }
                catch (NumberFormatException e) {
                    assert currentCase != null;
                    currentCase.add(line.toCharArray());
                }

                line = scanner.nextLine();
            }

            return userInput.stream()
                    .map(list -> list.toArray(char[][]::new))
                    .toList();
        }
    }

    public static int[] findNode(char[][] maze, char ch) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == ch) return new int[] {row, col};
            }
        }
        return null;
    }

    public static ValidPath backTrack(char[][] maze, int row, int col, int moveCnt) {
        if (row < 0 || col < 0) return null;
        if (row >= maze.length || col >= maze[0].length) return null;
        if (maze[row][col] == '$') return new ValidPath(maze.clone(), moveCnt);
        if (maze[row][col] != '.') return null;

        maze[row][col] = '%';
        ValidPath[] results = new ValidPath[] {
                backTrack(maze, row-1, col, moveCnt+1),
                backTrack(maze, row+1, col, moveCnt+1),
                backTrack(maze, row, col-1, moveCnt+1),
                backTrack(maze, row, col+1, moveCnt+1)
        };
        maze[row][col] = '.';

        Stream<ValidPath> filtered = Arrays.stream(results).filter(Objects::nonNull);
        Optional<ValidPath> optional = filtered.min(Comparator.comparingInt(record -> record.min));
        return optional.orElse(null);
    }

    public static String processCase(char[][] maze) {
        int[] start = findNode(maze, '>');
        assert start != null;

        ValidPath result = null;
        if (start[ROW] == 0) result = backTrack(maze, start[ROW]+1, start[COL], 1);
        else if (start[ROW] == maze.length-1) result = backTrack(maze, start[ROW]-1, start[COL], 1);
        else if (start[COL] == 0) result = backTrack(maze, start[ROW], start[COL]+1, 1);
        else if (start[COL] == maze.length-1) result = backTrack(maze, start[ROW], start[COL]-1, 1);

        if (result == null) return "No excape";

        return result.min + "\n" + String
                .join("\n", Arrays
                        .stream(result.maze)
                        .map(String::new)
                        .toArray(String[]::new));
    }

    public static void main(String[] args) {
        List<char[][]> cases = readInput();

        cases.stream()
                .map(FunctionalSolution::processCase)
                .forEach(System.out::println);
    }
}
