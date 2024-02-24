package com.york.competition.c2015.question4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        List<ArrayList<String>> inputs = readUserInput();
        inputs.stream()
                .map(Solution::evaluateExpression)
                .forEach(System.out::println);
    }

    private static String evaluateExpression(ArrayList<String> tokens) {
        tokens = collapseParens(tokens);
        tokens = collapseMultDiv(tokens);
        tokens = collapsePlusMin(tokens);
        return tokens.get(0);
    }

    private static ArrayList<String> collapseParens(ArrayList<String> tokens) {
        int openPar = -1;
        int parenDepth = 0;

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("(")) {
                if (openPar == -1) {
                    openPar = i;
                }
                parenDepth++;
            } else if (tokens.get(i).equals(")")) {
                parenDepth--;
                if (parenDepth == 0) {
                    collapseRange(tokens, openPar, i);
                    i = openPar;
                    openPar = -1;
                }
            }
        }
        return tokens;
    }

    private static void collapseRange(ArrayList<String> tokens, int start, int end) {
        String result = evaluateExpression(new ArrayList<>(tokens.subList(start + 1, end)));
        int numRes = Integer.parseInt(result);
        for (int j = 0; j <= end - start; j++) tokens.remove(start);
        tokens.add(start, String.valueOf(numRes));
    }

    private static ArrayList<String> collapsePlusMin(ArrayList<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("+") || tokens.get(i).equals("-")) {
                int token1 = Integer.parseInt(tokens.get(i - 1));
                int token2 = Integer.parseInt(tokens.get(i + 1));
                int result = tokens.get(i).equals("+") ? token1 + token2 : token1 - token2;
                i--;
                for (int j = 0; j < 3; j++) tokens.remove(i);
                tokens.add(i, String.valueOf(result));
            }
        }
        return tokens;
    }

    private static ArrayList<String> collapseMultDiv(ArrayList<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                int token1 = Integer.parseInt(tokens.get(i - 1));
                int token2 = Integer.parseInt(tokens.get(i + 1));
                int result = tokens.get(i).equals("*") ? token1 * token2 : token1 / token2;
                i--;
                for (int j = 0; j < 3; j++) tokens.remove(i);
                tokens.add(i, String.valueOf(result));
            }
        }
        return tokens;
    }

    private static List<ArrayList<String>> readUserInput() {
        List<ArrayList<String>> inputs = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String current = scanner.nextLine();
        while (!current.equals("quit")) {
            inputs.add(new ArrayList<>(Arrays.stream(current.split(" ")).toList()));
            current = scanner.nextLine();
        }
        return inputs;
    }
}
