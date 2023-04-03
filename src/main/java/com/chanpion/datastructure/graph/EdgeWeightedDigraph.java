package com.chanpion.datastructure.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 加权无向图
 *
 * @author April.Chen
 * @date 2023/4/3 10:39 上午
 **/
public class EdgeWeightedDigraph {
    // 顶点总数
    private final int V;
    // 边的总数
    private int E;
    // 邻接表
    private Queue<DirectedEdge>[] adj;

    // 创建一个含有V个顶点的空加权有向图
    public EdgeWeightedDigraph(int V) {
        // 初始化顶点数量
        this.V = V;
        // 初始化边的数量
        this.E = 0;
        // 初始化邻接表
        this.adj = new LinkedList[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<DirectedEdge>();
        }
    }

    // 获取图中顶点的数量
    public int V() {
        return V;
    }

    // 获取图中边的数量
    public int E() {
        return E;
    }


    // 向加权有向图中添加一条边e
    public void addEdge(DirectedEdge e) {
        // 边e是有方向的，所以只需要让e出现在起点的邻接表中即可
        int v = e.from();
        adj[v].add(e);
        E++;
    }

    // 获取由顶点v指出的所有的边
    public Queue<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // 获取加权有向图的所有边
    public Queue<DirectedEdge> edges() {
        /*
         遍历图中的每一个顶点，得到该顶点的邻接表，遍历得到每一条边，添加到队列中返回即可
         */
        Queue<DirectedEdge> allEdges = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge edge : adj[v]) {
                allEdges.add(edge);
            }
        }
        return allEdges;
    }

}
