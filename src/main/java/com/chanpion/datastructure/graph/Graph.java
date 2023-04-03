package com.chanpion.datastructure.graph;

import java.util.LinkedList;

/**
 * 无向图
 * @author April.Chen
 * @date 2023/4/3 10:42 上午
 **/
public class Graph {
    // 顶点数目
    private final int V;
    // 边的数目
    private int E;
    // 邻接表
    private LinkedList<Integer>[] adj;

    public Graph(int V) {
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

    // 向图中添加一条边 v-w
    public void addEdge(int v, int w) {
        // 在无向图中，边是没有方向的，所以该边既可以说是从v到w的边，又可以说是从w到v的边，
        // 因此，需要让w出现在v的邻接表中，并且还要让v出现在w的邻接表中
        adj[v].add(w);
        adj[w].add(v);
        // 边的数量+1
        E++;
    }

    // 获取和顶点v相邻的所有顶点
    public LinkedList<Integer> adj(int v) {
        return adj[v];
    }
}
