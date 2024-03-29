package com.york.codeforces.Mar29;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class problemE {

    public record TestCase(List<Integer> weights, int k) {}
    public record Pair(int n1, int n2) {}

    public static void main(String[] args) {
        List<TestCase> cases = readTestCases();
        cases.stream()
                .map(problemE::solveCase)
                .forEach(System.out::println);
    }

    public static int solveCase(TestCase testCase) {
        return combinations(testCase.weights).stream()
                .map(comb -> divs(comb, testCase.k))
                .max(Comparator.naturalOrder())
                .get();
    }

    public static List<List<Pair>> combinations(List<Integer> weights) {
        List<List<Pair>> allCombinations = new ArrayList<>();

            for (int i = 1; i < weights.size(); i++) {
                List<Integer> weightsCpy = new ArrayList<>(weights);
                weightsCpy.remove(i);
                weightsCpy.remove(0);
                List<Pair> curPairs = new ArrayList<>();
                curPairs.add(new Pair(weights.get(0), weights.get(i)));
                dfsHelper(curPairs, weightsCpy, allCombinations);
            }

        return allCombinations;
    }

    public static int divs(List<Pair> pairList, int k) {
        int sum = 0;
        for (Pair pair : pairList) {
            sum += (pair.n1 + pair.n2) / k;
        }
        return sum;
    }

    public static List<TestCase> readTestCases() {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        List<TestCase> returnVal = new ArrayList<>(numCases);

        for (int i = 0; i < numCases; i++) {
            int weightListSize = scanner.nextInt();
            List<Integer> weightList = new ArrayList<>(weightListSize);
            int k = scanner.nextInt();
            for (int j = 0; j < weightListSize; j++) {
                weightList.add(scanner.nextInt());
            }
            returnVal.add(new TestCase(weightList, k));
        }

        return returnVal;
    }

    public static void dfsHelper(List<Pair> curPairList, List<Integer> curWeights, List<List<Pair>> combinations) {
        if (curWeights.size() < 2) {
            combinations.add(curPairList);
            return;
        }

        for (int i = 1; i < curWeights.size(); i++) {
            List<Integer> weightsCpy = new ArrayList<>(curWeights);
            weightsCpy.remove(i);
            weightsCpy.remove(0);
            List<Pair> curPairList1 = new ArrayList<>(curPairList);
            curPairList1.add(new Pair(curWeights.get(0), curWeights.get(i)));
            dfsHelper(curPairList1, weightsCpy, combinations);
        }

    }
}
