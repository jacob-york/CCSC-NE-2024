package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class RomanNumeral {
    public static void main(String[] args){
        ArrayList<String> inputs = getInputs();

        for(String i : inputs){
            System.out.println(""+getValue(i));
        }
    }
    public static ArrayList<String> getInputs(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
        String i = sc.nextLine();
        while(!i.equals("quit")){
            inputs.add(i);
            i = sc.nextLine();
        }
        return inputs;
    }
    public static int getValue(String roman){
        int total = 0;
        for(int i = 0; i < roman.length(); i++){
            int value = getValue(roman.charAt(i));
            if(i+1 < roman.length() && value < getValue(roman.charAt(i+1))) total -= value;
            else total += value;
        }
        return total;
    }
    public static int getValue(char roman){
        final char[] numerals = new char[]{'M','D','C','L','X','V','I'};
        final int[] values = new int[]{1000,500,100,50,10,5,1};
        for(int i = 0; i < numerals.length; i++){
            if(roman == numerals[i]) return values[i];
        }
        return 0;
    }
}
