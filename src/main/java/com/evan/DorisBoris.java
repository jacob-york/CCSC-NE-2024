package com.evan;

import java.util.Scanner;

public class DorisBoris {
    private static final Scanner sc = new Scanner(System.in);
    private static int dorisCount = 0;
    private static int borisCount = 0;
    public static void main(String[] args){
        int sets = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < sets; i++){
            String next = sc.nextLine();
            try{
                int num = Integer.parseInt(next);
                dorisData(num);
            }
            catch(NumberFormatException e){
                borisData();
            }
        }

        System.out.println("Doris: " + dorisCount);
        System.out.println("Boris: " + borisCount);
    }

    private static void dorisData(int num){
        for(int i = 0; i < num; i++){
            sc.nextLine();
            dorisCount++;
        }
    }
    private static void borisData(){
        do {
            borisCount++;
        }
        while(!sc.nextLine().equals("0"));
    }
}