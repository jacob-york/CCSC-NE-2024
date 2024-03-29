package com.york.codeforces.mstWithMatching;

import java.util.Scanner;

public class Solution {
    public record Edge(int v1, int v2, int w) {}
    private static int c = 0;
    public static void main(String[] args) {
        String[] inputs = getInputs();
        int[][] matrix = getMatrix(inputs);
    }
    public static String[] getInputs(){
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        int n = Integer.parseInt(first.split(" ")[0]);
        c = Integer.parseInt(first.split(" ")[1]);

        String[] inputs = new String[n];

        for(int i = 0; i < n; i++){
            inputs[i] = sc.nextLine();
        }

        sc.close();

        return inputs;
    }
    public static int[][] getMatrix(String[] input){
        int[][] matrix = new int[input.length][input.length];
        for(int i = 0; i < input.length; i++){
            String[] vals = input[i].split( " ");
            int[] nums = new int[vals.length];
            for(int j = 0; j < vals.length; j++){
                nums[j] = Integer.parseInt(vals[j]);
            }
            matrix[i] = nums;
        }
        return matrix;
    }
}
