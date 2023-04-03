package com.chanpion.datastructure.graph;

/**
 * 加权无向图边
 *
 * @author April.Chen
 * @date 2023/4/3 10:43 上午
 **/
public class Edge implements Comparable<Edge> {
    // 顶点一
    private final int v;
    // 顶点二
    private final int w;
    // 当前边的权重
    private final double weight;

    // 通过顶点v和w，以及权重weight值构造一个边对象
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 获取边的权重值
    public double weight() {
        return weight;
    }

    // 获取边上的一个点
    public int either() {
        return v;
    }

    // 获取边上除了顶点vertex外的另外一个顶点
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else {
            return v;
        }
    }

    @Override
    public int compareTo(Edge that) {
        /*
        如果当前边的权重值大 return 1
        如果当前边的权重值小 return -1
        如果权重值一样大    return 0
         */
        return Double.compare(this.weight, that.weight);
    }
}