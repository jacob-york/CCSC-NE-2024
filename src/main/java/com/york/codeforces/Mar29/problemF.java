package com.york.codeforces.Mar29;

import java.util.Scanner;

record Case(char[] string, int[] permutation){}
public class problemF {
    public static void main(String[] args){
        final Case[] inputs = getInputs();
        for(Case c : inputs) System.out.println(processTestCase(c));
    }
    public static Case[] getInputs(){
        Scanner sc = new Scanner(System.in);
        final int cases = Integer.parseInt(sc.nextLine());
        final Case[] toReturn = new Case[cases];
        for(int i = 0; i < cases; i++){
            final int length = Integer.parseInt(sc.nextLine());
            final char[] string;
            final int[] permutation = new int[length];
            string = sc.nextLine().toCharArray();
            String[] p = sc.nextLine().split(" ");
            for(int j = 0; j < length; j++){
                permutation[j] = Integer.parseInt(p[j]);
            }
            toReturn[i] = new Case(string,permutation);
        }
        sc.close();
        return toReturn;
    }
    public static int processTestCase(Case testCase){
        int count = 1;
        char[] use = permutate(testCase.string().clone(), testCase.permutation());
        while(!String.copyValueOf(use).equals(String.copyValueOf(testCase.string()))){
            use = permutate(use,testCase.permutation());
            count++;
        }
        return count;
    }
    public static char[] permutate(char[] string, int[] permutation){
        char[] toReturn = new char[string.length];
        for(int i = 0; i < string.length; i++){
            toReturn[i] = string[permutation[i]-1];
        }
        return toReturn;
    }
}

