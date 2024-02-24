package com.york.competition.reorderings;

import java.util.*;

/**
 * Reorderings solution, from CCSCE 2016. I prioritized finishing quickly, so some of it could be written better.
 * Finished in around 50 minutes.
 *
 * @author Jacob York
 */
public class Solution {

    public static List<String> readUserInput(String end) {
        Scanner scanner = new Scanner(System.in);
        List<String> userLines = new ArrayList<>();

        String curLine = scanner.nextLine();
        while (!curLine.equals(end)) {
            userLines.add(curLine);
            curLine = scanner.nextLine();
        }

        return userLines;
    }

    private static List<List<String[]>> separate(List<String> userLines) {
        List<List<String[]>> cases = new ArrayList<>();

        List<String[]> curCase = new ArrayList<>();
        for (String line : userLines) {

            try {
                Integer.parseInt(line);
                curCase = new ArrayList<>();
                cases.add(curCase);
            }
            catch (NumberFormatException nfe) {
                assert curCase != null;
                curCase.add(line.split(" "));
            }
        }

        return cases;
    }

    public static int[] execute(List<String[]> permutation) {
        Map<String, Integer> registers = new HashMap<>();
        registers.put("A", 1);
        registers.put("X", 1);
        registers.put("Y", 2);

        for (String[] command : permutation) {
            String instr = command[0];
            String var1 = command[1];
            String var2 = command[2];

            switch (instr) {
                case "MOV":
                    int value = registers.containsKey(var2) ? registers.get(var2) : Integer.parseInt(var2);
                    registers.put(var1, value);
                    break;
                case "ADD":
                    int num1 = registers.containsKey(var1) ? registers.get(var1) : Integer.parseInt(var1);
                    int num2 = registers.containsKey(var2) ? registers.get(var2) : Integer.parseInt(var2);
                    registers.put("A", num1 + num2);
                    break;
                case "SUB":
                    num1 = registers.containsKey(var1) ? registers.get(var1) : Integer.parseInt(var1);
                    num2 = registers.containsKey(var2) ? registers.get(var2) : Integer.parseInt(var2);
                    registers.put("A", num1 - num2);
                    break;
                case "MUL":
                    num1 = registers.containsKey(var1) ? registers.get(var1) : Integer.parseInt(var1);
                    num2 = registers.containsKey(var2) ? registers.get(var2) : Integer.parseInt(var2);
                    registers.put("A", num1 * num2);
                    break;
                case "DIV":
                    num1 = registers.containsKey(var1) ? registers.get(var1) : Integer.parseInt(var1);
                    num2 = registers.containsKey(var2) ? registers.get(var2) : Integer.parseInt(var2);
                    registers.put("A", num1 / num2);
                    break;
            }
        }

        return new int[] {registers.get("A"), registers.get("X"), registers.get("Y")};
    }

    public static List<List<String[]>> getPermutations(List<String[]> _case) {
        List<List<String[]>> permutations = new ArrayList<>();
        List<String[]> cur = new ArrayList<>();
        dfs(permutations, _case.toArray(String[][]::new), cur);
        return permutations;
    }

    public static void dfs(List<List<String[]>> permutations, String[][] curAvail, List<String[]> cur) {
        if (cur.size() == curAvail.length) {
            permutations.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < curAvail.length; i++) {
            String[] line = curAvail[i];
            if (line != null) {
                cur.add(line);
                curAvail[i] = null;
                dfs(permutations, curAvail, cur);
                curAvail[i] = line;
                cur.remove(line);
            }
        }
    }

    public static String solveCase(List<String[]> _case) {
        int AV = 0;
        int XV = 1;
        int YV = 2;
        int[] baseValues = execute(_case);

        int matches = 0;
        List<List<String[]>> permutations = getPermutations(_case);
        for (List<String[]> permutation : permutations) {
            int[] result = execute(permutation);
            if (result[AV] == baseValues[AV] && result[XV] == baseValues[XV] && result[YV] == baseValues[YV]) {
                matches++;
            }
        }

        return String.format("A=%d X=%d Y=%d\n%d", baseValues[AV], baseValues[XV], baseValues[YV], matches);
    }

    public static void main(String[] args) {
        List<String> userLines = readUserInput("-1");
        List<List<String[]>> cases = separate(userLines);
        for (List<String[]> _case : cases) {
            System.out.println(solveCase(_case));
        }
    }
}
