package com.klimoshenko.ccscne2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem6 {
    private enum ShapeType {
        SQUARE, DIAMOND
    }

    private record TestCase(List<Shape> shapes, int row, int column, int numRows, int numColumns) {
    }

    private record Shape(ShapeType type, char character, int size, int tileSize) {
    }

    public static void main(String[] args) {
        List<TestCase> inputs = readTestCases();

        inputs.stream()
                .map(Problem6::processTestCase)
                .forEach(matrix -> {
                    for (int r = 0; r < matrix.length; r++) {
                        for (int c = 0; c < matrix[0].length; c++) {
                            System.out.print(matrix[r][c]);
                        }
                        System.out.println();
                    }
                });
        System.out.println("done");
    }

    private static char[][] processTestCase(TestCase testCase) {
        char[][] matrix = new char[testCase.numRows][testCase.numColumns];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                matrix[r][c] = '.';
            }
        }

        int offsetX = -(testCase.column % testCase.numColumns);
        int offsetY = -(testCase.row % testCase.numRows);

        testCase.shapes.forEach(shape -> {
            int numTilesY = testCase.numRows / shape.tileSize + 1;
            int numTilesX = testCase.numColumns / shape.tileSize + 1;

            int extraTiles = Math.max(testCase.numRows, testCase.numColumns);

            for (int tileY = -extraTiles; tileY < numTilesY + extraTiles; tileY++) {
                for (int tileX = -extraTiles; tileX < numTilesX + extraTiles; tileX++) {

                    switch (shape.type) {
                        case SQUARE -> {
                            for (int r = 0; r < shape.size; r++) {
                                for (int c = 0; c < shape.size; c++) {
                                    setCell(matrix, offsetY + tileY * shape.tileSize + r, offsetX + tileX * shape.tileSize + c, shape.character);
                                }
                            }
                        }
                        case DIAMOND -> {
                            int middle = shape.size;
                            int squareSize = (shape.size * 2) - 1;
                            for (int r = 0; r < squareSize; r++) {
                                int width = r >= middle ? ((2 * (squareSize - 1 - r) + 1)) : (2 * r + 1);
                                for (int c = 0; c < width; c++) {
                                    setCell(matrix, offsetY + tileY * shape.tileSize + r, offsetX + tileX * shape.tileSize + middle - width / 2 - 1 + c, shape.character);
                                }
                            }
                        }
                    }

                }
            }
        });

        return matrix;
    }

    private static void setCell(char[][] matrix, int r, int c, char ch) {
        if (!(0 <= r && r < matrix.length && 0 <= c && c < matrix[0].length)) {
            return;
        }
        matrix[r][c] = ch;
    }

    private static final int SENTINEL_STOP_LINES = 0;

    private static List<TestCase> readTestCases() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<TestCase> result = new ArrayList<>();

            while (true) {
                int firstNumber = Integer.parseInt(scanner.nextLine());
                if (firstNumber == SENTINEL_STOP_LINES) {
                    break;
                }

                List<Shape> shapes = new ArrayList<>();

                int numShapes = firstNumber;
                for (int i = 0; i < numShapes; i++) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");

                    ShapeType type = switch (parts[0]) {
                        case "square" -> ShapeType.SQUARE;
                        case "diamond" -> ShapeType.DIAMOND;
                        default -> throw new IllegalStateException("Unexpected value: " + parts[0]);
                    };
                    char character = parts[1].charAt(0);
                    int size = Integer.parseInt(parts[2]);
                    int tileSize = Integer.parseInt(parts[3]);

                    Shape shape = new Shape(type, character, size, tileSize);
                    shapes.add(shape);
                }

                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                int row = Integer.parseInt(parts[0]);
                int column = Integer.parseInt(parts[1]);
                int numRows = Integer.parseInt(parts[2]);
                int numColumns = Integer.parseInt(parts[3]);

                result.add(new TestCase(shapes, row, column, numRows, numColumns));
            }

            return result;
        }
    }
}
