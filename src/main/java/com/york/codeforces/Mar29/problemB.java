package com.york.codeforces.Mar29;

import java.util.Scanner;

public class problemB {
    public static void main(String[] args){
        int[][] inputs = getInputs();
        for(int[] i : inputs) System.out.println(processTestCase(i));
    }
    public static int[][] getInputs(){
        Scanner sc = new Scanner(System.in);
        final int lines = sc.nextInt();
        final int[][] toReturn = new int[lines][];
        for(int i = 0; i < lines; i++){
            final int length = sc.nextInt();
            toReturn[i] = new int[length*2];
            for(int j = 0; j < length*2; j++){
                toReturn[i][j] = sc.nextInt();
            }
        }
        sc.close();
        return toReturn;
    }
    public static String processTestCase(final int[] input){
        final int[] a = new int[input.length/2];
        final int[] b = new int[input.length/2];
        for(int i = 0; i < input.length/2; i++){
            a[i] = input[i];
            b[i] = input[i+input.length/2];
        }

        int minVal = Integer.MAX_VALUE;//Initialize to highest possible value
        int minIndex = 0;
        for(int i = 0; i < input.length/2; i++){
            if(b[i] < minVal && b[i] != 0){
                minVal = b[i];
                minIndex = i;
            }
        }

        final int difference = a[minIndex]-b[minIndex];
        if(difference < 0) return "NO";

        for(int i = 0; i < input.length/2; i++){
            if(b[i] != 0 && a[i]-b[i] != difference) return "NO";
        }
        return "YES";
    }
}
