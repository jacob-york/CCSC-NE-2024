package com.york.codeforces.Mar29;

import java.util.Scanner;

record TestCase(int[] cart, int[][] messages){}
public class problemG {
    public static void main(String[] args) {
        TestCase[] inputs = getInputs();
        for(TestCase input : inputs) printIntArray(processTestCase(input));
    }

    public static TestCase[] getInputs() {
        Scanner sc = new Scanner(System.in);
        final int lines = sc.nextInt();
        final TestCase[] toReturn = new TestCase[lines];

        for(int i = 0; i < lines; i++){
            final int carts = sc.nextInt();
            final int messages = sc.nextInt();

            final int[] cart = new int[carts];
            for(int j = 0; j < carts; j++){
                cart[j] = sc.nextInt();
            }

            final int[][] m = new int[messages][];
            for(int j = 0; j < messages; j++){
                final int index = sc.nextInt();
                final int speed = sc.nextInt();
                m[j] = new int[]{index,speed};
            }

            toReturn[i] = new TestCase(cart,m);
        }

        sc.close();
        return toReturn;
    }
    public static int[] processTestCase(TestCase input){
        final int messages = input.messages().length;
        final int[] toReturn = new int[messages];

        for(int i = 0; i < messages; i++){
            alterSpeed(input.cart(),input.messages()[i][0],input.messages()[i][1]);

            final int[] use = input.cart().clone();
            fixSpeed(use);
            toReturn[i] = countTrains(use);
        }

        return toReturn;
    }
    public static void fixSpeed(int[] carts){
        for(int i = 1; i < carts.length; i++){
            if(carts[i] > carts[i-1]) carts[i] = carts[i-1];
        }
    }
    public static void alterSpeed(int[] carts, int index, int amount){
        carts[index-1] -= amount;
    }
    public static int countTrains(int[] carts){
        int count = 1;
        for(int i = 1; i < carts.length; i++){
            if(carts[i] != carts[i-1]) count++;
        }
        return count;
    }
    public static void printIntArray(int[] input){
        for(int i = 0; i < input.length; i++){
            System.out.print(input[i]);
            if(i != input.length-1) System.out.print(" ");
        }
        System.out.println();
    }
}
