package com.york.useful.treeTraversals.recursive;

import com.york.leetcode.builtins.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * recursive, in-order binary tree traversal.
 */
class InOrderBinary {

    public void inOrderDfs(TreeNode root, List<Integer> result) {
        if (root == null) return;

        inOrderDfs(root.left, result);
        result.add(root.val);
        inOrderDfs(root.right, result);
    }

    public List<Integer> traverse(TreeNode root) {
        List<Integer> returnVal = new ArrayList<>();
        inOrderDfs(root, returnVal);
        return returnVal;
    }
}