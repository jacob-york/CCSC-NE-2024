package com.evan;

import java.util.ArrayList;
import java.util.Scanner;

public class FloodFill {
    private char[][] data;
    private static Scanner sc = new Scanner(System.in);
    public FloodFill(int height, int width){
        System.out.println(height + " " + width);
        data = new char[height][width];
        for(int i = 0; i < height; i++){
            String s = sc.nextLine();
            for(int j = 0; j < width; j++){
                data[i][j] = s.charAt(j);
            }
        }
    }
    public FloodFill(char[][] data){
        this.data = data;
    }
    private void fill(int x, int y, char from, char to){
        if(y < 0 || y > data.length) return;
        if(x < 0 || x > data[0].length) return;

        if(data[y][x] == from){
            data[y][x] = to;
            fill(x+1,y,from,to);
            fill(x-1,y,from,to);
            fill(x,y+1,from,to);
            fill(x,y-1,from,to);
        }
    }
    public void fill(int x, int y, char to){
        if(y < 0 || y > data.length) return;
        if(x < 0 || x > data[0].length) return;

        char from = data[y][x];
        data[y][x] = to;
        fill(x+1,y,from,to);
        fill(x-1,y,from,to);
        fill(x,y+1,from,to);
        fill(x,y-1,from,to);
    }
    public void print(){
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        String input = "";

        ArrayList<FloodFill> list = new ArrayList<>();

        while(!input.equals("quit")){
            input = sc.nextLine();
            String[] split = input.split(" ");
            int[] vals = new int[4];
            for(int i = 0; i < 4; i++){
                vals[i] = Integer.parseInt(split[i]);
            }
            char to = split[4].charAt(0);

            FloodFill map = new FloodFill(vals[0],vals[1]);
            map.fill(vals[2],vals[3],to);
            list.add(map);
        }
        for(FloodFill f : list){
            f.print();
        }
    }
}
