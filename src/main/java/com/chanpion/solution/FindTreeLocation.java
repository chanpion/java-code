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

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node42 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node4;
        node2.left = node42;
        node2.right = node6;
        node4.right = node8;


        TreeNodeWithLocation treeNodeWithLocation = findLocation(node1, 4);
        if (treeNodeWithLocation == null) {
            System.out.println("not found");
            return;
        }
        System.out.println("location, row : " + treeNodeWithLocation.row + " , col: " + treeNodeWithLocation.col);
    }

    public static TreeNodeWithLocation findLocation(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNodeWithLocation> queue = new LinkedList<>();

        TreeNodeWithLocation treeNodeWithLocation = new TreeNodeWithLocation(root, 1, 0);
        queue.offer(treeNodeWithLocation);
        while (!queue.isEmpty()) {
            TreeNodeWithLocation p = queue.poll();
            TreeNode node = p.node;
            if (node.val == target) {
                return p;
            }
            int level = p.row;

            if (p.node.left != null) {
                queue.offer(new TreeNodeWithLocation(p.node.left, level + 1, p.col * 2));
            }

            if (p.node.right != null) {
                queue.offer(new TreeNodeWithLocation(p.node.right, level + 1, p.col * 2 + 1));
            }
        }
        return null;
    }

    static class TreeNodeWithLocation {
        TreeNode node;
        int row;
        int col;

        public TreeNodeWithLocation(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }

        public TreeNodeWithLocation(int row, int col) {
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
