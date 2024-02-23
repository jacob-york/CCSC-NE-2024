package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem7 {
    public static void main(String[] args){
        ArrayList<String> inputs = getInputs();
        for(String i : inputs){
            processTestCase(i);
        }
    }
    public static ArrayList<String> getInputs(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();

        int num = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < num; i++){
            inputs.add(sc.nextLine());
        }
        return inputs;
    }
    public static void processTestCase(String s){
        int num = Integer.parseInt(s);

        int max = num % 2 == 0 ? num/2 : num/2+1;

        for(int i = max; i >= 1; i--){
            System.out.print(i+" ");
        }
        if(num % 2 == 0) System.out.print("1 ");
        for(int i = 2; i <= max; i++){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
