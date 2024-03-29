package com.chanpion.datastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 核心思想分析：排序 + 贪心，
 *
 * 1.我们开一个dis数组，用来表示起始点到每个顶点的距离，最开始时我们赋值为无穷大。
 *
 * 2.加变量loc，初始赋值为起始点。
 *
 * 贪心的策略：在dis数组里面找离初始点最近的那个点
 *
 * 3.通过loc更新dis数组，因为加入一个点后我们就可以更新路径
 *
 * 4.在dis数组里面找离初始点最近的那个点，排除已经选择过的点，将之赋值给loc。
 *
 * 5.重复执行 3 4操作，直到所有点加完。
 *
 * @author April.Chen
 * @date 2023/4/3 10:54 上午
 **/
public class Dijkstra2 {
    public static void main(String[] args) {
        int n, m, x; // n表示点数，m表示边数，x表示我们起点
        Map<Integer, String> routes = new HashMap<>();
        Scanner cin = new Scanner(System.in);

        n = cin.nextInt();
        m = cin.nextInt();
        x = cin.nextInt();

        int value[][] = new int[n + 1][n + 1]; // 表示点到点的邻接矩阵
        int dis[] = new int[n + 1]; // 存最短路径的
        for (int i = 1; i <= n; i++) {
            dis[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                // 初始化我们的地图
                value[i][j] = -1; // 表示没有路的
                if (i == j) {
                    value[i][j] = 0;
                }
            }
            routes.put(i, "");
        }
        for (int i = 0; i < m; i++) { // 表示要输入m个边
            int xx = cin.nextInt();
            int yy = cin.nextInt();
            int v = cin.nextInt(); // xx yy v表示从xx到yy有一条路 长度是v
            value[xx][yy] = v;
            // dis其实在第一个点时候可以在这里计算
            if (xx == x) {
                dis[yy] = v;
            }
        }
        seach(x, dis, value, n, routes);

    }

    public static void seach(int x, int dis[], int value[][], int n, Map<Integer, String> routes) {
        boolean mark[] = new boolean[n + 1];
        mark[x] = true;
        dis[x] = 0;
        int count = 1;
        routes.put(x, String.valueOf(x));

        while (count <= n) {    //O（n^2）
            int loc = 0; // 表示新加的点
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) { // 求dis里面的最小值 优先队列,堆 logn
                if (!mark[i] && dis[i] < min) {
                    min = dis[i];
                    loc = i;
                }
            }
            if (loc == 0) {
                break; // 表示没有可以加的点了
            }
            // 初始化第一个新加进来的节点
            if (routes.get(loc) == "") {
                routes.put(loc, "1->" + loc);
            }
            mark[loc] = true;
            //只需要关注 我们加进来的点 能有哪些路径就可以了，不用扫描所有的dis 最好的情况应该可以达到o(nlogn),最坏的情况才是O(n^2)
            for (int i = 1; i <= n; i++) {
                if (value[loc][i] != -1 && (dis[loc] + value[loc][i] < dis[i])) {
                    dis[i] = dis[loc] + value[loc][i];
                    routes.put(i, routes.get(loc) + "->" + i);
                }
            }
            count++;
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(x + "到 " + i + "的最短路径为 ：" + dis[i]);
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(x + "到 " + i + "的最短路径为 ：" + routes.get(i));
        }

    }
}
