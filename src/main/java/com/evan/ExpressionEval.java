package com.evan;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionEval {

    public static void main(String[] args){
        ArrayList<String> inputs = readInputs();
        for(String input : inputs){
            solveLine(input);
        }
    }

    private static ArrayList<String> readInputs(){

        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
        String line;
        do{
            line = sc.nextLine();
            if(!line.equals("quit")) inputs.add(line);
        }while(!line.equals("quit"));
        return inputs;
    }
    private static String solveLine(String line){

        if(!line.contains("(") && !line.contains("+") && !line.contains("-") && !line.contains("/") && !line.contains("*")){
            return line;
        }

        int to = line.indexOf(')');
        if(to != -1) {
            int from = line.substring(0, to).lastIndexOf('(');
            return solveLine(line.substring(0,from) + atomicParse(line.substring(from,to+1)) + line.substring(to+1));
        }
        else return atomicParse(line);
    }
    private static String atomicParse(String line){
        for(int i = 0; i < line.length(); i++){
            switch(line.charAt(i)){
                case '*':
                    int firstIndex = i-2;
                    while(line.charAt(firstIndex) != ' ') firstIndex--;
                    int lastIndex = i+2;
                    while(line.charAt(lastIndex) != ' ') lastIndex++;

                    String val1 = line.substring(firstIndex,i-1);
                    String val2 = line.substring(i+2,lastIndex+1);
                    int len = val1.length()+val2.length()+3;

                    line = line.substring(0,firstIndex)+(Integer.parseInt(val1) * Integer.parseInt(val2))+line.substring(lastIndex+1);
                    i -= len;

                    break;
                case '/':
                    firstIndex = i-2;
                    while(line.charAt(firstIndex) != ' ') firstIndex--;
                    lastIndex = i+2;
                    while(line.charAt(lastIndex) != ' ') lastIndex++;

                    val1 = line.substring(firstIndex,lastIndex+1);
                    val2 = line.substring(firstIndex,lastIndex+1);
                    len = val1.length()+val2.length()+3;

                    line = line.substring(0,firstIndex)+(Integer.parseInt(val1) * Integer.parseInt(val2))+line.substring(lastIndex+1);
                    i -= len;

                    break;
            }
        }

        for(int i = 0; i < line.length(); i++){
            switch(line.charAt(i)){
                case '+':
                    int firstIndex = i-2;
                    while(line.charAt(firstIndex) != ' ') firstIndex--;
                    int lastIndex = i+2;
                    while(line.charAt(lastIndex) != ' ') lastIndex++;

                    String val1 = line.substring(firstIndex,i-1);
                    String val2 = line.substring(i+2,lastIndex+1);
                    int len = val1.length()+val2.length()+3;

                    line = line.substring(0,firstIndex)+(Integer.parseInt(val1) + Integer.parseInt(val2))+line.substring(lastIndex+1);
                    i -= len;

                    break;
                case '-':
                    firstIndex = i-2;
                    while(line.charAt(firstIndex) != ' ') firstIndex--;
                    lastIndex = i+2;
                    while(line.charAt(lastIndex) != ' ') lastIndex++;
                    val1 = line.substring(firstIndex,lastIndex+1);
                    val2 = line.substring(firstIndex,lastIndex+1);
                    len = val1.length()+val2.length()+3;

                    line = line.substring(0,firstIndex)+(Integer.parseInt(val1) - Integer.parseInt(val2))+line.substring(lastIndex+1);
                    i -= len;

                    break;
            }
        }

        return line;
    }
}
