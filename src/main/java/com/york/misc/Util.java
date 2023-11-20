package com.york.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("line 1", "line 2", "line 3");

        String newLineJoined = String.join("\n", list);
        System.out.println(newLineJoined);

        List<List<String>> grid = Arrays.asList(
                Arrays.asList("[1]", "[2]", "[3]"),
                Arrays.asList("[4]", "[5]", "[6]"),
                Arrays.asList("[7]", "[8]", "[9]")
        );

        // Stream::map
        String[] finArray = grid
                .stream()  // convert to stream
                .map(subList -> String.join("", subList)) // for each subList (i.e. row), join it with ""
                .toArray(String[]::new); // convert the stream of Strings to an array of Strings.

        String finArrayJoined = String.join("\n", finArray);  // join the array of strings with newline.
        System.out.println(finArrayJoined);

    }
}
