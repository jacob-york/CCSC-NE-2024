package com.york.useful.treeTraversals.nonRecursive;

import com.york.leetcode.builtins.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class levelOrderNary {

    public void levelOrderDfs(Node root, List<Integer> result) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (queue.size() > 0) {
            Node current = queue.poll();
            result.add(current.val);

            // if you want to reverse the traversal, current.children needs to be reversed
            for (Node child : current.children)
                if (child != null) queue.add(child);
        }
    }

    public List<Integer> traverse(Node root) {
        List<Integer> returnVal = new ArrayList<>();
        levelOrderDfs(root, returnVal);
        return returnVal;
    }
}
