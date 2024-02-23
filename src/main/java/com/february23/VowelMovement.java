package com.february23;

import java.util.ArrayList;
import java.util.Scanner;


public class VowelMovement {

    public static ArrayList<String> getInput(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> raw = new ArrayList<>();
        String s = sc.nextLine();
        while(!s.equals("quit")){
            raw.add(s);
            s = sc.nextLine();
        }
        sc.close();
        return raw;
    }
    public static String solveCase(String i){
        char [] cArray = i.toCharArray();
        ArrayList<Character> vList = new ArrayList<>();
        String vowels = "aeiouAEIOU";
        String vowelsUpper = "AEIOU";
        for(int x = 0; x<cArray.length; x++){
            if (vowels.contains(cArray[x]+"")){
                vList.add(Character.toLowerCase(cArray[x]));
            }
        }
        vList.sort(Character::compareTo);
        for(int x = 0; x<cArray.length; x++){
            if (vowels.contains(cArray[x]+"")){
                if (vowelsUpper.contains(cArray[x] + "")){
                    cArray[x] = (char) ((char) vList.remove(0)+32);
                }
                else{
                    cArray[x] = vList.remove(0);
                }
            }
        }
        return cArray.toString();

    }
    public static void main(String[] args){
        ArrayList<String> input = getInput();
        for(String i : input){
            System.out.println(solveCase(i));
        }
    }
}
