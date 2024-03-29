package com.york.codeforces.Mar29;

import java.util.ArrayList;
import java.util.Scanner;

record CaseD(int k, String strip){}
public class problemD {
    public static void main(String[] args){
        CaseD[] inputs = getInputs();
        for(CaseD c : inputs) System.out.println(solveTestCase(c));
        //System.out.println(diff("ggg","gag"));
    }
    public static CaseD[] getInputs(){
        Scanner sc = new Scanner(System.in);
        final int count = Integer.parseInt(sc.nextLine());
        CaseD[] toReturn = new CaseD[count];
        for(int i = 0; i < count; i++){
            final int k = Integer.parseInt(sc.nextLine().split(" ")[1]);
            String s = sc.nextLine();
            toReturn[i] = new CaseD(k,s);
        }
        return toReturn;
    }
    public static int solveTestCase(CaseD c){
        String sub = "B".repeat(c.k());
        final ArrayList<String> list = new ArrayList<>();
        list.add(c.strip());
        int i = 0;
        while(!list.get(i).contains(sub)){
            final char[] v = list.get(i).toCharArray();
            for(int j = 0; j < v.length; j++){
                if(v[j] == 'W'){
                    final char[] w = v.clone();
                    w[j] = 'B';
                    list.add(String.valueOf(w));
                }
            }


            i++;
        }
        return diff(c.strip(),list.get(i));
    }
    public static int diff(String a, String b){
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();

        int count = 0;
        for(int i = 0; i < ca.length; i++){
            if(ca[i] != cb[i]) count++;
        }
        return count;
    }
}
