package com.york.competition.aMazeIng;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Jacob York
 */
public class Solution {

    /* TODO
     * Important:
     * this should leave a trail of '%' chars on maze to the shortest path.
     */
    public static char shortestNumMoves(char[][] maze) {
        int y = 0;
        int x = 0;
        boolean found = false;
        for (y = 0; y < maze.length; y++) {
            for (x = 0; x < maze[0].length; x++) {
                if (maze[y][x] == '>') {
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        Queue<String> stack = new LinkedList<>();
        stack.add(x + " " + y);
        int numMoves = 0;

        return maze[y][x];
    }

    public static String mazeToString(char[][] maze) {
        return String.join("\n", Arrays.stream(maze)
                .map(String::new)
                .toArray(String[]::new)
        );
    }

    public static List<char[][]> parseInput() {
        List<char[][]> mazes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        List<String> currentMaze = new ArrayList<>();
        int height = 0;
        String curInput = scanner.nextLine();

        while (!curInput.equals("-1")) {
            if (height == 0) {
                height = Integer.parseInt(curInput.split(" ")[0]);
            }
            else {
                currentMaze.add(curInput);
                height--;
                if (height == 0) {
                    mazes.add(currentMaze.stream()
                            .map(String::toCharArray)
                            .toArray(char[][]::new)
                    );
                    currentMaze.clear();
                }
            }
            curInput = scanner.nextLine();
        }

        return mazes;
    }

    public static void main(String[] args) {
        List<char[][]> mazes = parseInput();

        for (char[][] maze : mazes) {
            System.out.println(shortestNumMoves(maze));
            /*Integer shortestNumMoves = shortestNumMoves(maze);
            if (shortestNumMoves == null) {
                System.out.println("No escape");
            }
            else {
                System.out.println(shortestNumMoves);
                System.out.println(mazeToString(maze));
            }*/
        }
    }
}
