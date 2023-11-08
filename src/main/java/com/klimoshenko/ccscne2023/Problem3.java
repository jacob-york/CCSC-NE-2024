package com.klimoshenko.ccscne2023;

import java.util.*;

public class Problem3 {
    private enum CellType {
        INLAND,
        COAST,
        OCEAN
    }

    private record Cell(int r, int c, int elevation, CellType type) {
    }

    public static void main(String[] args) {
        List<int[][]> inputs = readTestCases();

        inputs.stream()
                .map(Problem3::processTestCase)
                .forEach(System.out::println);
    }

    private static int processTestCase(int[][] elevation) {
        int n = elevation.length;

        CellType[][] type = getCellTypes(elevation);

        int coastline = 0;

        // coastline: number of borders between ocean cell and neighbors that are coast cells
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (type[r][c] == CellType.OCEAN) {
                    coastline += getNeighbors(elevation, type, r, c).stream()
                            .filter(cell -> cell.type == CellType.COAST)
                            .count();
                }
            }
        }

        return coastline;
    }

    private static CellType[][] getCellTypes(int[][] elevation) {
        int n = elevation.length;

        CellType[][] type = new CellType[n][n];

        /*
        ocean:
            - below sea level, <4 neighbors
            OR
            - below sea level, any neighbor is ocean
         */
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (elevation[r][c] < 0 && getNeighbors(elevation, type, r, c).size() < 4) {
                    spreadOcean(elevation, type, r, c);
                }
            }
        }

        /*
        coast:
            - above sea level, any neighbor is ocean
         */
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (elevation[r][c] > 0
                        && getNeighbors(elevation, type, r, c).stream().anyMatch(cell -> cell.type == CellType.OCEAN)
                ) {
                    type[r][c] = CellType.COAST;
                }
            }
        }

        /*
        inland:
            - everything else
         */
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (type[r][c] == null) {
                    type[r][c] = CellType.INLAND;
                }
            }
        }

        return type;
    }

    private static void spreadOcean(int[][] elevation, CellType[][] type, int r, int c) {
        if (type[r][c] == CellType.OCEAN || !(elevation[r][c] < 0)) {
            return;
        }

        type[r][c] = CellType.OCEAN;

        getNeighbors(elevation, type, r, c).forEach(cell -> spreadOcean(elevation, type, cell.r, cell.c));
    }

    private static Set<Cell> getNeighbors(int[][] elevation, CellType[][] type, int r, int c) {
        int n = elevation.length;

        Set<Cell> result = new HashSet<>();

        if (r - 1 >= 0) result.add(new Cell(r - 1, c, elevation[r - 1][c], type[r - 1][c]));
        if (r + 1 < n) result.add(new Cell(r + 1, c, elevation[r + 1][c], type[r + 1][c]));
        if (c - 1 >= 0) result.add(new Cell(r, c - 1, elevation[r][c - 1], type[r][c - 1]));
        if (c + 1 < n) result.add(new Cell(r, c + 1, elevation[r][c + 1], type[r][c + 1]));

        return result;
    }

    private static final int SENTINEL_STOP_LINES = 0;

    private static List<int[][]> readTestCases() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<int[][]> result = new ArrayList<>();

            int n;
            while ((n = scanner.nextInt()) != SENTINEL_STOP_LINES) {
                int[][] matrix = new int[n][n];

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        matrix[r][c] = scanner.nextInt();
                    }
                }

                result.add(matrix);
            }

            return result;
        }
    }
}
