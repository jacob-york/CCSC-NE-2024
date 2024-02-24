package com.york.competition.birthdays;

import java.util.*;

/*
 * Note that I personally like streams and am trying to use them more in my code,
 * but all of this could be done without them.
 * */
public class Solution {

    /* Java Records are a quick-and-easy way of defining a class that's just attributes.
     * Plus, they come with some boiler-plate methods auto-generated like toStrings().
     * */
    private record ProblemCase(List<Date> birthdays, Date startDate, int yearCount) {}
    private record Date(int day, int month, int year) {
        @Override
        public String toString() {
            // this is where being familiar with C-style string formatting can come in handy.
            return String.format("%02d-%02d-%04d", day, month, year);
        }
    }

    public static void main(String[] args) {
        List<String> unparsedCases = readInput("0");

        unparsedCases.stream()
                .map(Solution::parseCase)
                .map(Solution::solveCase)
                .forEach(System.out::println);
    }

    private static List<String> readInput(String endSymbol) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        String current = scanner.nextLine();
        while (!current.equals(endSymbol)) {
            list.add(current);
            current = scanner.nextLine();
        }

        return list;
    }

    private static Date parseDate(String unparsedDate) {
        String[] sections = unparsedDate.split("-");
        int day = Integer.parseInt(sections[0]);
        int month = Integer.parseInt(sections[1]);
        int year = Integer.parseInt(sections[2]);
        return new Date(day, month, year);
    }

    private static ProblemCase parseCase(String unparsedCase) {
        String[] sections = unparsedCase.split(" ");

        List<Date> birthdays = Arrays.stream(Arrays.copyOfRange(sections, 1, sections.length-2))
                .map(Solution::parseDate)
                .toList();
        Date startDate = parseDate(sections[sections.length - 2]);
        int yearCount = Integer.parseInt(sections[sections.length - 1]);

        return new ProblemCase(birthdays, startDate, yearCount);
    }

    /**
     * gets the age of a person born on [birthday] at the time of [pDate].
     */
    private static int getAge(Date birthday, Date pDate) {
        Date date = (
                pDate.month == 2 && pDate.day == 29
        ) ? new Date(1, 3, pDate.year) : new Date(pDate.day, pDate.month, pDate.year);

        int assumedDate = date.year - birthday.year;
        if (birthday.month > date.month || (birthday.month == date.month && birthday.day > date.day)) {
            assumedDate--;
        }

        return assumedDate;
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static String formatOutput(Date curDate, List<Integer> ages) {
        return curDate.toString() + " " + String.join(" ", ages.stream().map(Objects::toString).toList());
    }

    private static String solveCase(ProblemCase problemCase) {
        for (int i = 0; i < problemCase.yearCount; i++) {
            int curYear = problemCase.startDate.year + i;

            for (Date birthday : problemCase.birthdays) {
                Date curDate = new Date(birthday.day, birthday.month, curYear);
                List<Integer> ages = problemCase.birthdays.stream()
                        .map(bday -> getAge(bday, curDate))
                        .toList();

                if (ages.stream().allMatch(Solution::isPrime)) return formatOutput(curDate, ages);
            }
        }
        return "No date found.";
    }
}
