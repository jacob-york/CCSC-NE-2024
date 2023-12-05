package com.evan;

import java.util.ArrayList;
import java.util.Scanner;

public class OdometerFactory {
    public static void main(String[] args){
        ArrayList<String> inputs = readInputs();
        for(String line : inputs){
            solveCase(line);
        }
    }
    private static ArrayList<String> readInputs(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
        String line;
        do{
            line = sc.nextLine();
            if(!line.equals("-1")) inputs.add(line);
        }while(!line.equals("-1"));
        return inputs;
    }
    private static void solveCase(String line){
        String[] splitLine = line.split(" ");
        int base = Integer.parseInt(splitLine[0]);
        int length = Integer.parseInt(splitLine[1]);
        int num = Integer.parseInt(splitLine[2],base);
        int miles = Integer.parseInt(splitLine[3]);

        int pow = pow(base,length);

        for(int i = 0; i < miles; i++){
            num++;
            if(num >= pow){
                num -= pow;
            }
            String s = Integer.toString(num,base);
            for(int j = s.length(); j < length; j++){
                s = "0"+s;
            }
            System.out.println(s);
        }
    }
    private static int pow(int base, int exp){
        if(exp == 0) return 1;
        if(exp == 1) return base;
        else return base*pow(base,exp-1);
    }
}
