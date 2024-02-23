package com.evan;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionEval {

    public static void main(String[] args){
        ArrayList<String> inputs = readInputs();
        ArrayList<Integer> outputs = new ArrayList<>();
        for(String input : inputs){
            outputs.add(solveLine(input));
        }
        for(Integer i : outputs){
            System.out.println(i);
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
    private static int solveLine(String line){
        ArrayList<Object> list = new ArrayList<>();
        String[] sl = line.split(" ");
        for(String s : sl){
            try{
                Integer i = Integer.parseInt(s);
                list.add(new Node(i));
            }
            catch(NumberFormatException e){
                list.add(s);
            }
        }
        while(list.size() > 1){
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof String s){
                    if(i+1 < list.size() && i-1 >= 0){
                        if(list.get(i-1) instanceof Node a && list.get(i+1) instanceof Node b && s.length() == 1){
                            Node n = new Node(a,b,s.charAt(0));

                            list.set(i-1,n);
                            for(int j = i+2; j < list.size(); j++){
                                list.set(j-2,list.get(j));
                            }
                            list.remove(list.size()-2);
                            list.remove(list.size()-1);
                        }
                        if(list.get(i-1) instanceof String a && list.get(i+1) instanceof String b && list.get(i) instanceof Node n){
                            if(a.equals("(") && b.equals(")")){
                                list.set(i-1,n);
                                for(int j = i+2; j < list.size(); j++){
                                    list.set(j-2,list.get(j));
                                }
                                list.remove(list.size()-2);
                                list.remove(list.size()-1);
                            }
                        }
                    }
                }
            }
        }
        Object o = list.get(0);
        if(o instanceof Node n){
            return n.getValue();
        }
        else return 0;
    }

    private static class Node{
        private Node a;
        private Node b;
        private char operator;
        private int val;
        public Node(int val){
            this.val = val;
            operator = ' ';
            a = null;
            b = null;
        }
        public Node(Node a, Node b, char operator){
            this.a = a;
            this.b = b;
            this.operator = operator;
            val = 0;
        }
        public int getValue(){
            switch(operator){
                case ' ': return val;
                case '+': return a.getValue() + b.getValue();
                case '-': return a.getValue() - b.getValue();
                case '*': return a.getValue() * b.getValue();
                case '/': return a.getValue() / b.getValue();
            }
            return val;
        }
    }
}
