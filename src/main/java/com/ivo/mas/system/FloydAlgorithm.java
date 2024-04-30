package com.ivo.mas.system;

import java.util.*;


public class FloydAlgorithm {
    public static int MaxValue = Integer.MAX_VALUE>>1;
    public static int[][] path;
    public static List<Integer> pathIndex;
    public static void getRouter() {
//        Scanner input = new Scanner(System.in);
//        System.out.println("请输入顶点数和边数:");
        //顶点数
        int vertex = 5;
        //边数
        int edge = 5;

        int[][] matrix = new int[vertex][vertex];
        //初始化邻接矩阵
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                matrix[i][j] = MaxValue;
            }
        }

        //初始化路径数组
        path = new int[matrix.length][matrix.length];

        matrix[0][1] = 10;
        matrix[1][0] = 10;
        matrix[0][3] = 30;
        matrix[3][0] = 30;
        matrix[0][4] = 100;
        matrix[4][0] = 100;
        matrix[1][2] = 50;
        matrix[2][1] = 50;
        matrix[2][3] = 20;
        matrix[3][2] = 20;
        matrix[2][4] = 10;
        matrix[4][2] = 10;
        matrix[3][4] = 60;
        matrix[4][3] = 60;

//        //初始化边权值
//        for (int i = 0; i < edge; i++) {
//            System.out.println("请输入第" + (i + 1) + "条边与其权值:");
//            int source = input.nextInt();
//            int target = input.nextInt();
//            int weight = input.nextInt();
//            matrix[source][target] = weight;
//        }

        //调用算法计算最短路径
        floyd(matrix);
    }

    public static Map floyd(int[][] matrix, int begin, int end) {
        path = new int[matrix.length][matrix.length];
        pathIndex = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                path[i][j] = -1;
            }
        }

        for (int m = 0; m < matrix.length; m++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][m] + matrix[m][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][m] + matrix[m][j];
                        //记录经由哪个点到达
                        path[i][j] = m;
                    }
                }
            }
        }
        Map<String,Object> resMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {

                    if(i==begin&&j==end){
                        if (matrix[i][j] == MaxValue) {
                            System.out.println(i + "到" + j + "不可达");
                        } else {
                            System.out.print(i + "到" + j + "的最短路径长度是：" + matrix[i][j]+",");
                            System.out.print("最短路径为：" + i + "->");
                            resMap.put("res",true);
                            pathIndex.add(i);
                            findPath(i, j);
                            pathIndex.add(j);
                            resMap.put("shortDis",matrix[i][j]);
                            resMap.put("path",pathIndex);
                        }

                    }
                }
            }
        }
        return resMap;
    }

    //非递归实现
    public static void floyd(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                path[i][j] = -1;
            }
        }

        for (int m = 0; m < matrix.length; m++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][m] + matrix[m][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][m] + matrix[m][j];
                        //记录经由哪个点到达
                        path[i][j] = m;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    if (matrix[i][j] == MaxValue) {
                        System.out.println(i + "到" + j + "不可达");
                    } else {

                        System.out.print(i + "到" + j + "的最短路径长度是：" + matrix[i][j]);
                        System.out.print("最短路径为：" + i + "->");
                        findPath(i, j);
                        System.out.println(j);
                    }
                }
            }
        }
    }

    //递归寻找路径
    public static void findPath(int i, int j) {
        int m = path[i][j];
        if (m == -1) {
            return;
        }
        findPath(i, m);
        System.out.print(m + "->");
        pathIndex.add(m);
        findPath(m, j);
    }
}
