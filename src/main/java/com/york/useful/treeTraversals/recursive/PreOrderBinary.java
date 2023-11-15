package com.york.useful.treeTraversals.recursive;

import com.york.leetcode.builtins.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * recursive, pre-order binary tree traversal.
 */
class PreOrderBinary {

    public void preOrderDfs(TreeNode root, List<Integer> result) {
        if (root == null) return;

        result.add(root.val);
        preOrderDfs(root.left, result);
        preOrderDfs(root.right, result);
    }

    public List<Integer> traverse(TreeNode root) {
        List<Integer> returnVal = new ArrayList<>();
        preOrderDfs(root, returnVal);
        return returnVal;
    }
}