package com.york.useful.treeTraversals.recursive;

import com.york.leetcode.builtins.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * recursive, post-order binary tree traversal.
 */
class PostOrderBinary {

    public void postOrderDfs(TreeNode root, List<Integer> result) {
        if (root == null) return;

        postOrderDfs(root.left, result);
        postOrderDfs(root.right, result);
        result.add(root.val);
    }

    public List<Integer> traverse(TreeNode root) {
        List<Integer> returnVal = new ArrayList<>();
        postOrderDfs(root, returnVal);
        return returnVal;
    }
}