package com.chanpion.datastructure.graph;

/**

 * @author April.Chen
 * @date 2023/4/3 10:36 上午
 **/
public class Dijkstra {
    static int[][] graph;
    static int[] dist;
    static int[] path = new int[7];
    static boolean[] isUsed = new boolean[7];

    static {
        graph = new int[][]{
                {0, 1, 4, 3, 0, 0, 0},
                {1, 0, 3, 0, 0, 0, 0},
                {4, 3, 0, 2, 1, 5, 0},
                {3, 0, 2, 0, 2, 0, 0},
                {0, 0, 1, 2, 0, 0, 0},
                {0, 0, 5, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 2, 0}
        };
        dist = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    }

    public static void dijkstra() {
        while (true) {
            //判断节点是否已经全部纳入
            if (isOver()) {
                break;
            }
            //寻找未纳入的dist最小节点
            int i = findMin();
            //设置为已遍历状态
            isUsed[i] = true;
            //遍历该节点邻接节点
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0 && isUsed[j] == false) {
                    //更新dist、path
                    flashDistAndPath(i, j);
                }
            }
        }
    }

    public static int findMin() {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < dist.length; i++) {
            if (min > dist[i] && isUsed[i] == false) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    //之前的dist值一定是之前该节点到根节点的最短路径开销
    public static void flashDistAndPath(int i, int j) {
        if (isUsed[j] == false) {
            if (graph[i][j] + dist[i] < dist[j]) {
                dist[j] = graph[i][j] + dist[i];
                path[j] = j;
            }
        }
    }

    public static boolean isOver() {
        int trues = 0;
        for (boolean isused : isUsed) {
            if (isused == true) {
                trues++;
            }
        }
        if (trues == dist.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        isUsed[0] = true;
        dist[1] = 1;
        dist[2] = 4;
        dist[3] = 3;

        path[1] = 0;
        path[2] = 0;
        path[3] = 0;
        dijkstra();
        for (int i = 0; i < dist.length; i++) {
            System.out.println(dist[i]);
        }
    }
}
