package com.york.competition;

/**
 * @author Jacob York
 */
public class HeatWave {

    public static int longestHeatWave(int[] temperatures) {
        boolean inHeatWave = false;
        int currLongest = 0;
        int currCnt = 0;

        for (int temp : temperatures) {
            if (temp >= 90) {
                if (!inHeatWave) inHeatWave = true;
                currCnt++;
            }
            else if (inHeatWave) {
                inHeatWave = false;
                currLongest = Math.max(currCnt, currLongest);
                currCnt = 0;
            }
        }

        return currLongest;
    }
    public static void main(String[] args) {
        System.out.println(longestHeatWave(new int[] {90, 90, 90, 3, 90, 5, 6, 7, 8, 9}));
    }
}