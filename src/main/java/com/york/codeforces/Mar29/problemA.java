package com.york.codeforces.Mar29;
import java.util.Scanner;


public class problemA {
    public static void main(String[] args) {
        int[] inputs = getInputs();
        for(int i :inputs){
            solveCase(i);
        }

    }
    public static int[] getInputs(){
        Scanner scan = new Scanner(System.in);
        int casesN = scan.nextInt();
        int[] cases = new int[casesN];
        for(int i = 0; i<casesN; i++){
            cases[i]= scan.nextInt();
        }
        return cases;
    }
    public static void solveCase(int blocks){
        int h1 = 3;
        int h2 = 2;
        int h3 = 1;
        int n = 1;
        blocks = blocks -6;
        if (blocks ==0){
            System.out.println("2 3 1");
            return;
        }

        while(blocks > 0 ){
            if (n == 1){
                h1++;
                n++;
            }
            else if(n == 2){
                h2++;
                n++;
            }
            else if(n == 3){
                h3++;
                n = 1;
            }
            blocks--;
        }
        System.out.println(h2+" "+h1+" "+h3);

    }


}
