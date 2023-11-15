package com.york.useful.treeTraversals.nonRecursive;

import com.york.leetcode.builtins.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class levelOrderBinary {

    public void levelOrderDfs(TreeNode root, List<Integer> result) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (queue.size() > 0) {
            TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public List<Integer> traverse(TreeNode root) {
        List<Integer> returnVal = new ArrayList<>();
        levelOrderDfs(root, returnVal);
        return returnVal;
    }
}
