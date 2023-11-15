package com.york.useful.treeTraversals.recursive;

import com.york.leetcode.builtins.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * recursive, pre-order n-ary tree traversal.
 */
public class PreOrderNary {

    public void preOrderDfs(Node root, List<Integer> result) {
        if (root == null) return;

        result.add(root.val);
        for (Node child : root.children)
            preOrderDfs(child, result);
    }

    public List<Integer> traverse(Node root) {
        List<Integer> returnVal = new ArrayList<>();
        preOrderDfs(root, returnVal);
        return returnVal;
    }
}
