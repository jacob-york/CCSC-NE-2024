package com.evan;

import java.util.ArrayList;
import java.util.Scanner;

public class BIrthdays {
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
            if(!line.equals("0")) inputs.add(line);
        }while(!line.equals("0"));
        return inputs;
    }
    private static void solveLine(String line){
        String[] splitLine = line.split(" ");
        int numBirthdays = Integer.parseInt(splitLine[0]);
        Date[] birthdays = new Date[numBirthdays];
        for(int i = 0; i < numBirthdays; i++){
            birthdays[i] = new Date(splitLine[i+1]);
        }
        Date startDate = new Date(splitLine[numBirthdays+1]);
        int[] ages = new int[numBirthdays];
        for(int i = 0; i < numBirthdays; i++){
            ages[i] = Date.getAge(birthdays[i],startDate);
        }
        int numberOfYears = Integer.parseInt(splitLine[numBirthdays+2]);

        for(int i = 0; i < numBirthdays; i++){
            for(int j = i+1; j < numBirthdays; j++){
                if(Date.compare(birthdays[i],birthdays[j]) == -1){
                    Date k = birthdays[i];
                    int age = ages[i];
                    birthdays[i] = birthdays[j];
                    ages[i] = ages[j];
                    birthdays[j] = k;
                    ages[j] = age;
                }
            }
        }

        int nextBirthdayIndex = 0;
        while(Date.compare(birthdays[nextBirthdayIndex],startDate) == -1){
            nextBirthdayIndex++;
        }

        boolean found = false;
        Date date = null;

        for(int i = 0; i < numberOfYears; i++){
            for(int j = 0; i < numBirthdays; i++){
                int index = (j+nextBirthdayIndex)%numBirthdays;

                found = true;
                for(Integer age : ages){
                    if(!PrimeFinder.isPrime(age)){
                        found = false;
                        break;
                    }
                }

                if(found){
                    date = birthdays[index];
                    break;
                }

                ages[index]++;
            }
            if(found) break;
        }

        if(found) System.out.println(date.toString());
        else System.out.println("None found!");

    }
    private static class PrimeFinder{
        private static final ArrayList<Integer> primes = new ArrayList<>();

        public static boolean isPrime(int i){
            if(primes.isEmpty()){
                primes.add(2);
            }
            if(primes.get(primes.size()-1) < i){
                int x = primes.get(primes.size()-1);
                for(int j = x; j < i+2; j++){
                    boolean isPrime = true;
                    for(Integer k : primes){
                        if(j % k != 0){
                            isPrime = false;
                            break;
                        }
                    }
                    if(isPrime) primes.add(j);
                }
            }

            return primes.contains(i);
        }
    }
    private static class Date{
        int day;
        int month;
        int year;

        public String toString(){
            return day + "-" + month + "-" + year;
        }

        Date(String date){
            String[] s = date.split("-");
            day = Integer.parseInt(s[0]);
            month = Integer.parseInt(s[1]);
            year = Integer.parseInt(s[2]);
        }

        public static int getAge(Date birthday, Date today){
            int age = today.year-birthday.year;
            if(today.month < birthday.month){
                age -= 1;
            }
            else if(today.month == birthday.month && today.day < birthday.day){
                age -= 1;
            }
            return age;
        }
        public static int compare(Date date1, Date date2){
            if(date1.month < date2.month){
                return -1;
            }
            else if(date1.month > date2.month){
                return 1;
            }
            else return Integer.compare(date1.day, date2.day);
        }
    }
}
