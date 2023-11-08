package com.klimoshenko.ccscne2023;

import java.util.*;
import java.util.stream.Collectors;

public class Problem2 {

    private record Crater(int x, int y, int radius, String type) {
    }

    private record LandingSite(int x, int y, int radius) {
    }

    private record Input(List<Crater> craters, List<LandingSite> landingSites) {
    }

    public static void main(String[] args) {
        Input input = readTestCases();

        input.landingSites.stream()
                .map(landingSite -> Problem2.processTestCase(landingSite, input.craters))
                .forEach(System.out::println);
    }


    private static long processTestCase(LandingSite landingSite, List<Crater> craters) {
        Set<String> uniqueTypes = craters.stream()
                .map(crater -> crater.type)
                .collect(Collectors.toSet());

        return uniqueTypes.stream()
                .map(type ->
                        craters.stream()
                                .filter(crater -> intersects(landingSite, crater))
                                .filter(crater -> crater.type.equals(type))
                                .count()
                )
                .max(Comparator.naturalOrder())
                .get();
    }

    private static boolean intersects(LandingSite landingSite, Crater crater) {
        return Math.hypot(landingSite.x - crater.x, landingSite.y - crater.y) <= (landingSite.radius + crater.radius);
    }

    private static final int SENTINEL_STOP_LINES = 0;

    private static Input readTestCases() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Crater> craters = readCraters(scanner);
            List<LandingSite> landingSites = readLandingSites(scanner);

            return new Input(craters, landingSites);
        }
    }

    private static List<Crater> readCraters(Scanner scanner) {
        List<Crater> result = new ArrayList<>();

        while (true) {
            int firstNumber = scanner.nextInt();
            if (firstNumber == SENTINEL_STOP_LINES) {
                break;
            }

            int x = firstNumber;
            int y = scanner.nextInt();
            int radius = scanner.nextInt();
            String type = scanner.next();

            Crater crater = new Crater(x, y, radius, type);

            result.add(crater);
        }

        return result;
    }

    private static List<LandingSite> readLandingSites(Scanner scanner) {
        List<LandingSite> result = new ArrayList<>();

        while (true) {
            int firstNumber = scanner.nextInt();
            if (firstNumber == SENTINEL_STOP_LINES) {
                break;
            }

            int x = firstNumber;
            int y = scanner.nextInt();
            int radius = scanner.nextInt();

            LandingSite landingSite = new LandingSite(x, y, radius);

            result.add(landingSite);
        }

        return result;
    }
}
