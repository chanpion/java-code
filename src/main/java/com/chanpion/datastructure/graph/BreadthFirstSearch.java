package com.chanpion.datastructure.graph;

import java.util.LinkedList;

/**
 * 广度优先搜索
 *
 * @author April.Chen
 * @date 2023/4/3 10:41 上午
 **/
public class BreadthFirstSearch {
    // 索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    // 记录有多少个顶点与s顶点相通
    private int count;
    // 用来存储待搜索邻接表的点
    private LinkedList<Integer> waitSearch;

    // 构造广度优先搜索对象，使用广度优先搜索找出 G 图中 s 顶点的所有相邻顶点
    public BreadthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count = 0;
        this.waitSearch = new LinkedList<Integer>();
        bfs(G, s);
    }

    // 使用广度优先搜索找出G图中v顶点的所有相邻顶点
    private void bfs(Graph G, int v) {
        // 把当前顶点v标识为已搜索
        marked[v] = true;
        // 让顶点 v 的相邻点都进入队列
        waitSearch.addAll(G.adj(v));

        // 通过循环，如果队列不为空，则从队列中弹出一个待搜索的顶点进行搜索
        while (!waitSearch.isEmpty()) {
            // 弹出一个待搜索的顶点
            Integer wait = waitSearch.poll();
            if (!marked[wait]) {
                bfs(G, wait);
            }
        }
        // 让相通的顶点+1；
        count++;
    }

    // 判断w顶点与s顶点是否相通
    public boolean marked(int w) {
        return marked[w];
    }

    // 获取与顶点s相通的所有顶点的总数
    public int count() {
        return count;
    }
}
