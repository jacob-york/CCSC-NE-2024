package com.york.useful.treeTraversals.nonRecursive;

import com.york.leetcode.builtins.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderNary {

    public void preOrderDfs(Node root, List<Integer> result) {
        Stack<Node> stack = new Stack<>();
        if (root != null) stack.push(root);

        while (!stack.empty()) {
            Node current = stack.pop();
            result.add(current.val);

            // if you want to reverse the traversal, current.children needs to be reversed
            for (Node child : current.children)
                if (child != null) stack.push(child);
        }
    }

    public List<Integer> traverse(Node root) {
        List<Integer> returnVal = new ArrayList<>();
        preOrderDfs(root, returnVal);
        return returnVal;
    }
}
