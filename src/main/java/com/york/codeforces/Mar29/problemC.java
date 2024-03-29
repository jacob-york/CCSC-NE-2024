package com.york.codeforces.Mar29;

import java.util.Scanner;

public class problemC {
    public static void main(String[] args){
        int[][] inputs = getInputs();
        for(int[] i : inputs) printIntArray(processTestCase(i));
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
    public static int[] processTestCase(int[] input){
        final int[] s = new int[input.length/2];
        final int[] f = new int[input.length/2];
        final int[] d = new int[input.length/2];

        for(int i = 0; i < input.length/2; i++){
            s[i] = input[i];
            f[i] = input[i+input.length/2];
        }

        for(int i = 0; i < input.length/2; i++){
            final int beginTime = i == 0 || s[i] > f[i-1] ? s[i] : f[i-1];
            d[i] = f[i]-beginTime;
        }
        return d;
    }
    public static void printIntArray(int[] input){
        for(int i = 0; i < input.length; i++){
            System.out.print(input[i]);
            if(i != input.length-1) System.out.print(" ");
        }
        System.out.println();
    }
}
