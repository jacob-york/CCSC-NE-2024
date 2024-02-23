package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem6 {
    public static void main(String[] args){
        ArrayList<String> inputs = getInputs();
        for(String s : inputs){
            System.out.println(processCase(s));
        }
    }
    public static ArrayList<String> getInputs(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();

        int num = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < num; i++){
            inputs.add(sc.nextLine());
        }

        sc.close();
        return inputs;
    }
    public static String processCase(String s){
        String[] strings = s.split(" ");
        int num = Integer.parseInt(strings[0]);
        char post = strings[1].charAt(0);
        char fence = strings[2].charAt(0);

        String n = new String();
        for(int i = 0; i < num; i++){
            n += post;
            if(i+1 < num) n += fence;
        }

        return n;
    }
}
