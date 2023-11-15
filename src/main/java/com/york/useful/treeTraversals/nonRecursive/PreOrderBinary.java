package com.york.useful.treeTraversals.nonRecursive;

import com.york.leetcode.builtins.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderBinary {

    public void preOrderDfs(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);

        while (!stack.empty()) {
            TreeNode current = stack.pop();
            result.add(current.val);

            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }
    }

    public List<Integer> traverse(TreeNode root) {
        List<Integer> returnVal = new ArrayList<>();
        preOrderDfs(root, returnVal);
        return returnVal;
    }
}
