package com.york.useful;

import java.util.*;

/**
 * Produces permutations in a specified order (specifiable via the comparator parameter).
 * Time Complexity: O(n!)
 * Space Complexity: O(n)
 * @param <T> Type of each element in the sequence.
 *
 * @author jacob york
 */
public class PermutationAlgorithm<T extends Comparable<T>> {

    public List<List<T>> apply(T[] sequence, Comparator<T> comparator) {

        Arrays.sort(sequence, comparator);

        List<List<T>> permutations = new ArrayList<>();
        List<T> current = new ArrayList<>();

        dfs(permutations, sequence, current);
        return permutations;
    }

    public void dfs(List<List<T>> permutations, T[] curAvail, List<T> current) {
        for (int i = 0; i < curAvail.length; i++) {
            if (curAvail[i] == null) continue;

            current.add(curAvail[i]);
            curAvail[i] = null;

            dfs(permutations, curAvail, current);

            curAvail[i] = current.get(current.size() - 1);
            current.remove(current.size() - 1);
        }

        if (current.size() == curAvail.length) permutations.add(List.copyOf(current));
    }

    public static void main(String[] args) {
        System.out.print("enter a list of string elements (separated by spaces)>");
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split(" ");

        PermutationAlgorithm<String> algorithm = new PermutationAlgorithm<>();
        List<List<String>> permutations = algorithm.apply(inputs, Comparator.naturalOrder());

        String[] joinedPerms = permutations.stream()
                .map(permutation -> permutation.toArray(String[]::new))
                .map(permutation -> String.join(" ", permutation))
                .toArray(String[]::new);

        System.out.println(String.join("\n", joinedPerms));
    }
}
