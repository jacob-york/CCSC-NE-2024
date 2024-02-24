package com.york.leetcode.substrings;

import java.util.*;


class Solution {

    /*
     * Sliding Window Solution
     *
     * @author rachit615 on leetcode
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/solutions/3157485/used-hashset-in-java-explained-approach/
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Character> map = new HashMap<>();
        int max = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            if (!map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), s.charAt(right));
                max = Math.max(max, right-left+1);
            }
            else {
                while(s.charAt(left) != s.charAt(right)) {
                    map.remove(s.charAt(left));
                    left++;
                }
                left++;
            }
        }

        return max;
    }

    public static List<String> readInputs() {
        Scanner scanner = new Scanner(System.in);
        List<String> returnVal = new ArrayList<>();

        for (int inputs = Integer.parseInt(scanner.nextLine()); inputs > 0; inputs--) {
            returnVal.add(scanner.nextLine());
        }

        return returnVal;
    }

    public static void main(String[] args) {
        List<String> inputs = readInputs();

        Solution solution = new Solution();
        inputs.stream()
                .map(solution::lengthOfLongestSubstring)
                .forEach(System.out::println);
    }
}

