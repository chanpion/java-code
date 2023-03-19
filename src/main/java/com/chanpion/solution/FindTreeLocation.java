package com.chanpion.solution;

import java.util.LinkedList;

/**
 * 1
 * 2    4
 * 4  6 null 8
 *
 * @author April Chen
 * @date 2022/12/13 22:08
 */
public class FindTreeLocation {

    public Location findLocation(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int row = 0;
        int currentTotal = 1;
        int currentNum = 1;
        int nextTotal = 0;
        while (queue.peek() != null) {
            TreeNode node = queue.poll();
            currentNum--;
            if (node.val == target) {
                return new Location(row, currentTotal - currentNum);
            }
            if (currentNum == 0) {
                row++;
                currentTotal = nextTotal;
                currentNum = currentTotal;
                nextTotal = 0;
            }
            if (node.left != null) {
                queue.offer(node.left);
                nextTotal++;
            } else if (node.right != null) {
                queue.offer(node.right);
                nextTotal++;
            }

        }
        return null;
    }

    static class Location {
        int row;
        int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
