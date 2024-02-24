package com.february23;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem4 {
    //I forget what this one's called
    public record x(point[] points, float fraction){

    }
    public record point(float x, float y){

    }
    public static void main(String[] args){
        ArrayList<String> inputs = getInputs();
        ArrayList<x> records = getRecords(inputs);
        for(x e : records){
            System.out.println(processTestCase(e));
        }
    }
    public static ArrayList<String> getInputs(){
        ArrayList<String> inputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while(!s.equals("quit")){
            inputs.add(s);
            s = sc.nextLine();
        }
        return inputs;
    }
    public static ArrayList<x> getRecords(ArrayList<String> list){
        ArrayList<x> records = new ArrayList<>();
        for(int i = 0; i < list.size(); i+=2){
            String[] s = list.get(i).split(" ");
            point[] f = new point[s.length/2];
            for(int j = 0; j < f.length; j++){
                float x = Float.parseFloat(s[j*2]);
                float y = Float.parseFloat(s[j*2+1]);
                f[j] = new point(x,y);
            }
            float frac = Float.parseFloat(list.get(i+1));
            records.add(new x(f, frac));
        }
        return records;
    }
    public static float[] getDistances(x e){
        point[] points = e.points;
        float[] distances = new float[points.length-1];
        for(int i = 0; i < distances.length; i++){
            float x1 = points[i].x;
            float y1 = points[i].y;
            float x2 = points[i+1].x;
            float y2 = points[i+1].y;

            float x = x2 - x1;
            float y = y2 - y1;

            float dsq = x*x+y*y;
            float d = (float) Math.sqrt(dsq);
            distances[i] = d;
        }
        return distances;
    }
    public static float getTotalDistance(float[] distances){
        float total = 0.0f;
        for(int i = 0; i < distances.length; i++){
            total += distances[i];
        }
        return total;
    }
    public static String processTestCase(x e){
        if(e.fraction > 1.0f || e.fraction < 0.0f) return "Invalid Fraction";
        else if(e.fraction == 1.0f){
            float x = e.points[e.points.length-1].x;
            float y = e.points[e.points.length-1].y;
            return "x="+x+", y = "+y;
        }
        else if(e.fraction == 0.0f){
            float x = e.points[0].x;
            float y = e.points[0].y;
            return "x="+x+", y = "+y;
        }

        float[] distances = getDistances(e);
        float totalDistance = getTotalDistance(distances);
        float distance = totalDistance*e.fraction;

        int on = -1;
        for(int i = 0; i < distances.length; i++){
            distance -= distances[i];
            if(distance < 0){
                distance += distances[i];
                on = i;
                break;
            }
        }

        float x1 = e.points[on].x;
        float y1 = e.points[on].y;
        float x2 = e.points[on+1].x;
        float y2 = e.points[on+1].x;

        float x = x2-x1;
        float y = y2-y1;

        float f = distance / (float)Math.sqrt(x*x+y*y);

        x = x*f+x1;
        y = y*f+y1;

        return "x="+x+", y="+y;
    }
}
