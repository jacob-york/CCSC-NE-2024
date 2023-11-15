package com.york.useful.treeTraversals.recursive;

import com.york.leetcode.builtins.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * recursive, post-order n-ary tree traversal.
 */
public class PostOrderNary {

    public void postOrder(Node root, List<Integer> result) {
        if (root == null) return;

        for (Node child : root.children)
            postOrder(child, result);
        result.add(root.val);
    }

    public List<Integer> traverse(Node root) {
        List<Integer> returnVal = new ArrayList<>();
        postOrder(root, returnVal);
        return returnVal;
    }
}
