package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem8 {
    public static void main(String[] args){
        ArrayList<String> inputs = getInputs();
        for(String i : inputs){
            processTestCase(i);
        }
    }
    public static ArrayList<String> getInputs(){
        ArrayList<String> inputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < num; i++){
            inputs.add(sc.nextLine());
        }

        sc.close();
        return inputs;
    }
    public static void processTestCase(String s){
        int num = Integer.parseInt(s.split(" ")[0]);
        String text = s.split(" ")[1];

        String first = new String();
        String last = new String();

        for(int i = 0; i < text.length(); i++){
            if(i % num == 0){
                last = text.charAt(i)+last;
            }
            else{
                first = first+text.charAt(i);
            }
        }

        System.out.println(first+last);
    }
}
