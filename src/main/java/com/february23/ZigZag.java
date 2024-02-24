package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class ZigZag {
    public static void main(String[] args){
        ArrayList<String[]> input = getInput();
        for(String[] i : input){
            System.out.println(testCase(i));
        }
    }
    public static ArrayList<String[]> getInput(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> raw = new ArrayList<>();
        String s = sc.nextLine();
        while(!s.equals("quit")){
            raw.add(s);
            s = sc.nextLine();
        }
        ArrayList<String[]> out = new ArrayList<>();
        for(String l : raw){
            out.add(l.split(" "));
        }
        sc.close();
        return out;
    }
    public static String testCase(String[] line){
        int[] nums = new int[line.length];
        for(int i = 0; i < line.length; i++){
            nums[i] = Integer.parseInt(line[i]);
        }

        int prev = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) return "Zero value";
            if(sameSigns(prev,nums[i])) return "No";

            prev = nums[i];
        }
        return "Yes";
    }
    public static boolean sameSigns(int a, int b){
        if(a < 0 && b >= 0) return false;
        if(a > 0 && b <= 0) return false;
        if(a == 0 && b != 0) return false;
        return true;
    }
}
