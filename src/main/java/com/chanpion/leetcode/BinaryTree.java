package com.chanpion.leetcode;

import com.chanpion.solution.FindTreeLocation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author April Chen
 * @date 2022/11/28 21:20
 */
public class BinaryTree {

    /**
     * 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }


    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public static List<Integer> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left == null && node.right == null) {
                size++;
            }
        }

        return list;
    }

    /**
     * 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null || p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 将有序数组转换为二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * 最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        int minDepth = Math.min(leftDepth, rightDepth);
        return minDepth + 1;
    }

    /**
     * 112. 路径总和
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 带层数的层序遍历
     */
    public static void levelorderWithLevel2(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();

        nodeQueue.offer(root);
        levelQueue.offer(1);

        while (!nodeQueue.isEmpty()) {
            TreeNode p = nodeQueue.poll();
            int level = levelQueue.poll();
            System.out.println(p + " + " + level);

            if (p.left != null) {
                nodeQueue.offer(p.left);
                levelQueue.offer(level + 1);
            }
            if (p.right != null) {
                nodeQueue.offer(p.right);
                levelQueue.offer(level + 1);
            }
        }
    }

    /**
     * 获取第 k 层结点的个数
     *
     * @param root
     * @param k
     * @return
     */
    public static int getKLevelSize(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNodeWithLevel> queue = new LinkedList<>();
        queue.offer(new TreeNodeWithLevel(root, 1));

        //定义变量，记录 k 层结点的个数
        int size = 0;
        while (!queue.isEmpty()) {
            TreeNodeWithLevel node = queue.poll();
            TreeNode p = node.node;
            int level = node.level;

            if (level == k) {
                //说明是第 k 层了
                size++;
            }
            if (p.left != null) {
                queue.offer(new TreeNodeWithLevel(p.left, level + 1));
            }
            if (p.right != null) {
                queue.offer(new TreeNodeWithLevel(p.right, level + 1));
            }
        }
        return size;
    }

    /**
     * 获取叶子结点的个数
     */
    public static int leafSizeOf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.left == null && p.right == null) {
                size++;
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
        return size;
    }


    /**
     * 树的高度
     *
     * @param root
     * @return
     */
    public static int heightOf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNodeWithLevel> queue = new LinkedList<>();
        queue.offer(new TreeNodeWithLevel(root, 1));

        //定义一个变量记录 height
        //height 的值可以根据 level 确定
        int height = -1;
        while (!queue.isEmpty()) {
            TreeNodeWithLevel node = queue.poll();
            TreeNode p = node.node;
            int level = node.level;

            height = level;

            if (p.left != null) {
                queue.offer(new TreeNodeWithLevel(p.left, level + 1));
            }
            if (p.right != null) {
                queue.offer(new TreeNodeWithLevel(p.right, level + 1));
            }

        }
        //此时 height 就为最后一个 level ，即最大的 level
        return height;
    }

    /**
     * 判断一棵树是否是完全二叉树
     * 思路：将所有元素依次加入队列，当取出的元素为 null 时停止元素添加。因为二叉树碰到 null 有两种情况：
     * <p>
     * null 之后所有元素也都为 null，则说明是完全二叉树
     * null 之后还有元素,则说明不是完全二叉树
     */
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //循环终止的条件，从队列中取出的元素是 null
        while (true) {
            TreeNode p = queue.poll();
            if (p == null) {
                break;
            }
            //因为这里需要根据本身的树进行判断，所以不用再判断左右孩子是否为 null
            queue.offer(p.left);
            queue.offer(p.right);
        }

        //判断取出元素为 null 后，其后面是否还有元素，即判断后面元素是否 == null
        //当后面元素存在 != null 时，说明不是完全二叉树，返回 false
        //当后面元素都 == null 时，说明是完全二叉树，返回 true
        while (!queue.isEmpty()) {
            TreeNode q = queue.poll();
            if (q != null) {
                return false;
            }
        }
        return true;
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


    static class TreeNodeWithLevel {
        public TreeNode node;
        public int level;

        public TreeNodeWithLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }

        @Override
        public String toString() {
            return String.format("%s-%d", node.toString(), level);
        }
    }

}
