package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem10 {
    public static void main(String[] args){
        ArrayList<String> inputs = getInputs();
        for(String i : inputs){
            testCase(i);
        }
    }
    public static ArrayList<String> getInputs(){
        ArrayList<String> strings = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < num; i++){
            strings.add(sc.nextLine());
        }
        sc.close();
        return strings;
    }
    public static void testCase(String s){
        String[] names = s.toLowerCase().split(" ");

        int count = 0;
        for(int i = 0; i < names.length; i++){
            if(names[i].equals("santa")){
                System.out.println("no!");
                return;
            }
            if(names[i].equals("claus")){
                System.out.println("no?");
                return;
            }
            if(offByOne(names[i],"santa") || offByOne(names[i],"claus")) count++;
        }

        if(count == 1) System.out.println("yes.");
        else System.out.println("no.");
    }
    public static boolean offByOne(String name, String compare){
        System.out.println(name + " - " + compare);

        if(name.length() != compare.length()+1) return false;

        char[] nameArray = compare.toCharArray();
        char[] compareArray = compare.toCharArray();

        int charsOffBy = 0;
        for(int i = 0; i < name.length(); i++){
            System.out.println(nameArray[i] + " " + compareArray[i]);
            if(nameArray[i] != compareArray[i-charsOffBy]) charsOffBy++;
        }

        System.out.println(charsOffBy);
        return charsOffBy == 1;
    }
}
