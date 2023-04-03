package com.chanpion.datastructure.graph;

/**
 * Floyd算法可以求任意两个顶点的最短路径。求任意两个顶点的最短路径同样可以使用Dijkstra算法，只需将所求顶点设成源点即可，但相比于Floyd算法比较复杂。
 *
 * 核心思想
 * 创建n阶方阵d用于保存各顶点之间的最短路径长度，其初始化为邻接矩阵。两点之间的距离是边的权，如果两点之间没有边相连，则权为无穷大。
 * 创建三维布尔数组p，用于记录最短路径的两个顶点所经过的顶点。
 * 对于每一对顶点 v 和 w，看看是否存在一个顶点 u 使得从 v 到 u 再到 w 比已知的路径更短。如果存在，则说明顶点v到顶点w会经过顶点u。则令p[v][w][u] = true，表示顶点v和顶点w的最短路径上有顶点u。同时，更新d[v][w]=<v,u>+<u,w>（<i,j>——表示顶点i和j的权值）。
 *
 * 重复步骤2，直到遍历所有顶点。
 * @author April.Chen
 * @date 2023/4/3 10:51 上午
 **/
public class Floyd {
    private boolean[][][] p; // p[v][w][u] = true表示顶点v和w之间的最短路径上有顶点u
    private int[][] d; // d[v][w]表示顶点v和w之间的最短路径长度

    public void getFloydPath(int[][] m) {
        int vNum = m.length;
        p = new boolean[vNum][vNum][vNum];
        d = m;

        for (int v = 0; v < vNum; v++) {
            for (int w = 0; w < vNum; w++) {
                for (int u = 0; u < vNum; u++) { // 初始化p数组，即默认所有顶点均不相连
                    p[v][w][u] = false;
                }
                if (d[v][w] < Integer.MAX_VALUE) {// 顶点v和w是否直接相连
                    p[v][w][v] = true;
                    p[v][w][w] = true;
                }

            }
        }

        for (int u = 0; u < vNum; u++) {
            for (int v = 0; v < vNum; v++) {
                for (int w = 0; w < vNum; w++) {
                    if (d[v][u] < Integer.MAX_VALUE && d[u][w] < Integer.MAX_VALUE && d[v][u] + d[u][w] < d[v][w]) {
                        d[v][w] = d[v][u] + d[u][w];
                        for (int i = 0; i < vNum; i++) {
                            p[v][w][i] = p[v][u][i] || p[u][w][i];
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取顶点v和w之间的最短路径
     *
     * @param v 起始顶点
     * @param w 终点
     * @return 最短路径
     */
    public int getPath(int v, int w) {
        return d[v][w];
    }

    public static void main(String[] args) {
        int MAX_WEIGHT = Integer.MAX_VALUE;
        int[][] Matrix = new int[9][];    //邻接矩阵

        //邻接矩阵初始化
        Matrix[0] = new int[]{0, 1, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        Matrix[1] = new int[]{1, 0, 3, 7, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        Matrix[2] = new int[]{5, 3, 0, MAX_WEIGHT, 1, 7, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        Matrix[3] = new int[]{MAX_WEIGHT, 7, MAX_WEIGHT, 0, 2, MAX_WEIGHT, 3, MAX_WEIGHT, MAX_WEIGHT};
        Matrix[4] = new int[]{MAX_WEIGHT, 5, 1, 2, 0, 3, 6, 9, MAX_WEIGHT};
        Matrix[5] = new int[]{MAX_WEIGHT, MAX_WEIGHT, 7, MAX_WEIGHT, 3, 0, MAX_WEIGHT, 5, MAX_WEIGHT};
        Matrix[6] = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 3, 6, MAX_WEIGHT, 0, 2, 7};
        Matrix[7] = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 9, 5, 2, 0, 4};
        Matrix[8] = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 7, 4, 0};

        Floyd f = new Floyd();
        f.getFloydPath(Matrix);
        int p = f.getPath(4, 3);
        System.out.println(p);
    }
}
