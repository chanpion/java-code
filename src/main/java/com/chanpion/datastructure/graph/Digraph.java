package com.chanpion.datastructure.graph;

import java.util.LinkedList;

/**
 * 有向图
 *
 * @author April.Chen
 * @date 2023/4/3 10:37 上午
 **/
public class Digraph {
    // 顶点数目
    private final int V;
    // 边的数目
    private int E;
    // 邻接表
    private LinkedList<Integer>[] adj;

    public Digraph(int V) {
        // 初始化顶点数量
        this.V = V;
        // 初始化边的数量
        this.E = 0;
        // 初始化邻接表
        this.adj = new LinkedList[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }


    // 获取顶点数目
    public int V() {
        return V;
    }

    // 获取边的数目
    public int E() {
        return E;
    }

    // 向有向图中添加一条边 v->w
    public void addEdge(int v, int w) {
        /*
        只需要让顶点w出现在顶点v的邻接表中，因为边是有方向的，
        最终，顶点v的邻接表中存储的相邻顶点的含义是：  v -> 其他顶点
        */
        adj[v].add(w);
        E++;
    }

    // 获取由v指出的边所连接的所有顶点
    public LinkedList<Integer> adj(int v) {
        return adj[v];
    }

    // 该图的反向图
    private Digraph reverse() {
        // 创建有向图对象
        Digraph r = new Digraph(V);

        for (int v = 0; v < V; v++) {
            // 获取由该顶点v指出的所有边
            // 原图中表示的是由顶点v->w的边
            for (Integer w : adj[v]) {
                // 转换指向为 w->v
                r.addEdge(w, v);
            }
        }
        return r;
    }
}
