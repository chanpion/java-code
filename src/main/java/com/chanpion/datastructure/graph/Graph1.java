package com.chanpion.datastructure.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author April.Chen
 * @date 2023/4/3 10:22 上午
 **/
public class Graph1 {
    //存放顶点
    protected List<String> vertexes;
    //存放边
    protected int[][] edges;
    //是否被访问
    protected boolean[] isVisited;
    protected int numOfEdges;

    public Graph1(int n) {
        this.vertexes = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.isVisited = new boolean[n];
    }

    //常用方法
    // 1. 获取节点个数
    protected int getNumOfVertex() {
        return vertexes.size();
    }

    // 2. 打印邻接矩阵
    protected void printGraph() {
        System.out.print(" ");
        for (String s : vertexes) {
            System.out.print("     " + s);
        }
        System.out.println();
        for (int r = 0; r < vertexes.size(); r++) {
            System.out.print(vertexes.get(r) + " ");
            for (int c = 0; c < vertexes.size(); c++) {
                System.out.print(String.format("%5d", edges[r][c]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 3. 获取边的数目
    protected int getNumOfEdges() {
        return numOfEdges;
    }

    // 4. 获取某条边的权值
    protected int getWeightOfEdges(int v1, int v2) {
        return edges[v1][v2];
    }

    // 5. 添加节点
    protected void addVertex(String v) {
        vertexes.add(v);
    }

    // 6. 添加边（双向）
    protected void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 7.获取顶点索引对应的值
    protected String getValueByIndex(int i) {
        return vertexes.get(i);
    }

    //*****************************迪杰斯特拉算法*********************************************
    public static void Dijkstra(Graph1 graph, int v) {
        int[] distance = new int[graph.getNumOfVertex()];//顶点v到其他各顶点的距离
        int[] visited = new int[graph.getNumOfVertex()];//存储中间的遍历结果
        // 一、初始化
        init(v, distance, visited, graph);
        // 二、循环更新距离直到所有点都被遍历
        while (!ifEnd(visited)) {
            update(getMinDistanceIndex(distance, visited, v), visited, distance, graph, v);
        }
    }

    // 1.初始化（初始顶点需特殊处理）
    public static void init(int beginIndex, int[] distance, int[] visited, Graph1 graph) {
        for (int i = 0; i < distance.length; i++) {
            if (graph.edges[beginIndex][i] != 0) distance[i] = graph.edges[beginIndex][i];//先获得原始的距离数组
        }
        distance[beginIndex] = 0;//距起始点的距离始终为0，后面更新时要额外注意避免修改
        visited[beginIndex] = 1;//起始点标记已访问
        System.out.println("初始化...................");
        System.out.println("距离：");
        System.out.println(Arrays.toString(distance));
        System.out.println("已访问顶点：");
        System.out.println(Arrays.toString(visited));
        System.out.println();
    }

    // 2.返回distance中距离最短并未被访问的顶点索引
    public static int getMinDistanceIndex(int[] distance, int[] visited, int v) {
        int minDis = 65536;//假定最小值
        int minDisIndex = 0;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < minDis && i != v && visited[i] == 0) {//要找未访问的点，同时不可以是出发点！
                minDis = distance[i];
                minDisIndex = i;
            }
        }
        return minDisIndex;
    }

    // 3.更新操作，每次都更新距离数组和已访问数组(重点难点在于更新距离)
    public static void update(int index, int[] visited, int[] distance, Graph1 graph, int v) {
        Map<String, String> modifiedVertex = new HashMap<>();
        int[] tempDis = new int[distance.length];
        //首先把index对应点与其他点的距离保存在临时变量中
        for (int i = 0; i < graph.getNumOfVertex(); i++) {
            if (graph.edges[index][i] != 0) {
                tempDis[i] = graph.edges[index][i];
            }
        }
        //修改距离，要加上从出发点到index顶点的距离。注意原始出发点不能动！
        for (int k = 0; k < graph.getNumOfVertex(); k++) {
            if (tempDis[k] != 65536 && k != v) {
                tempDis[k] += distance[index];
            }
        }
        //修改后的距离如果比原来的小，就更新distance，同样不能动原始出发点！（感觉这里像是动态规划的思想，需要动态调整到所有点的距离）
        for (int j = 0; j < graph.getNumOfVertex(); j++) {
            if (tempDis[j] < distance[j] && j != v) {
                modifiedVertex.put(graph.getValueByIndex(j), distance[j] + "->" + tempDis[j]);
                distance[j] = tempDis[j];
            }
        }
        //标记这个点已访问
        visited[index] = 1;
        //输出本次的更新结果
        System.out.println();
        System.out.print("距离： ");
        System.out.println(Arrays.toString(distance));
        System.out.print("更新距离： ");
        if (modifiedVertex.isEmpty()) {
            System.out.print("本次未更新");
        } else {
            for (Map.Entry<String, String> entry : modifiedVertex.entrySet()) {
                System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
            }
        }
        System.out.println();
        System.out.print("已访问顶点： ");
        System.out.println(Arrays.toString(visited));
    }

    // 4.判断已访问数组是否满，满了就结束
    public static Boolean ifEnd(int[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testDijkstra() {
        Graph1 graph = new Graph1(10);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 7);
        graph.addEdge(0, 6, 2);
        graph.addEdge(1, 6, 3);
        graph.addEdge(1, 3, 9);
        graph.addEdge(2, 4, 8);
        graph.addEdge(3, 5, 4);
        graph.addEdge(4, 5, 5);
        graph.addEdge(4, 6, 4);
        graph.addEdge(5, 6, 6);
        graph.addEdge(4, 7, 5);
        graph.addEdge(5, 7, 5);
        graph.addEdge(5, 8, 4);
        graph.addEdge(7, 8, 3);
        graph.addEdge(3, 9, 6);
        graph.addEdge(8, 9, 2);
        //权为0的边权都等于65536.
        for (int i = 0; i < graph.getNumOfVertex(); i++) {
            for (int j = 0; j < graph.getNumOfVertex(); j++) {
                if (graph.edges[i][j] == 0) {
                    graph.edges[i][j] = 65536;
                }
            }
        }
        System.out.println("边的数量： " + graph.getNumOfEdges());
        System.out.println("顶点的数量： " + graph.getNumOfVertex());
        System.out.println("邻接矩阵：");
        graph.printGraph();
        Graph1.Dijkstra(graph, 6);
    }


    //*****************************弗洛伊德算法*********************************************
    public static void Floyd(Graph1 graph) {
        int[][] distance = graph.edges;//记录各顶点间的距离
        // mid-中间顶点  begin-起始顶点  end-终点顶点
        for (int mid = 0; mid < graph.getNumOfVertex(); mid++) {
            System.out.println("\n" + graph.getValueByIndex(mid) + "作为中间顶点...");
            for (int begin = 0; begin < graph.getNumOfVertex(); begin++) {
                for (int end = 0; end < graph.getNumOfVertex(); end++) {
                    //对于两个顶点A、B，以及他们中间的顶点C，
                    // 如果A->C->B 的距离比 A->B 的距离短，就更新A、B间的距离
                    int newDis = distance[begin][mid] + distance[mid][end];
                    if (distance[begin][end] > newDis && begin != end) {
                        System.out.println("修改距离：" + graph.getValueByIndex(begin) + "->" + graph.getValueByIndex(end)
                                + " " + distance[begin][end] + ", 修改为 :" + newDis);
                        distance[begin][end] = newDis;
                        distance[end][begin] = newDis;//更新距离,对于无向图 Lij=Lji
                    }
                }
            }
        }
        //结果展示
        System.out.println(" " + "各个顶点间的最终最短距离：");
        for (int i = 0; i < graph.getNumOfVertex(); i++) {
            for (int j = 0; j < graph.getNumOfVertex(); j++) {
                if (distance[i][j] != 65536) {
                    System.out.print(" " + graph.getValueByIndex(i) + "->" + graph.getValueByIndex(j) + " " + distance[i][j] + ",");
                }
            }
            System.out.println("\n");
        }
    }


    @Test
    public void testFloyd() {
        Graph1 graph = new Graph1(10);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 7);
        graph.addEdge(0, 6, 2);
        graph.addEdge(1, 6, 3);
        graph.addEdge(1, 3, 9);
        graph.addEdge(2, 4, 8);
        graph.addEdge(3, 5, 4);
        graph.addEdge(4, 5, 5);
        graph.addEdge(4, 6, 4);
        graph.addEdge(5, 6, 6);
        graph.addEdge(4, 7, 5);
        graph.addEdge(5, 7, 5);
        graph.addEdge(5, 8, 4);
        graph.addEdge(7, 8, 3);
        graph.addEdge(3, 9, 6);
        graph.addEdge(8, 9, 2);
        //权为0的边权都等于65536.
        for (int i = 0; i < graph.getNumOfVertex(); i++) {
            for (int j = 0; j < graph.getNumOfVertex(); j++) {
                if (graph.edges[i][j] == 0) {
                    graph.edges[i][j] = 65536;
                }
            }
        }
        System.out.println("边的数量： " + graph.getNumOfEdges());
        System.out.println("顶点的数量： " + graph.getNumOfVertex());
        System.out.println("邻接矩阵：");
        graph.printGraph();
        Graph1.Floyd(graph);
    }
}

