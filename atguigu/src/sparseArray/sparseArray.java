package sparseArray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class sparseArray {
    public  static void main(String[] args) {
        //1.创建一个二维数组作为棋盘，其中0：没有棋子  1：黑棋 2：白棋
/*      0	0	0	0	0	0	0	0	0	0	0
        0	0	1	0	0	0	0	0	0	0	0
        0	0	0	2	0	0	0	0	0	0	0
        0	0	0	0	2	0	0	0	0	0	0
        0	0	0	0	0	1	0	0	0	0	0
        0	0	0	0	0	0	0	0	0	0	0
        0	0	0	0	0	0	0	0	0	0	0
        0	0	0	0	0	0	0	0	0	0	0
        0	0	0	0	0	0	0	0	0	0	0
        0	0	0	0	0	0	0	0	0	0	0
        0	0	0	0	0	0	0	0	0	0	0   */
        int chessArray1[][] =  new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[3][4] = 2;
        chessArray1[4][5] = 1;
        //输出展示二维数组 原棋盘
        System.out.println("输出展示二维数组 原棋盘:");
        for (int[] row : chessArray1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //遍历二维数组找到非零数据的个数
        int sum = 0;
        for (int[] row : chessArray1) {
            for (int data : row) {
                if(data != 0)
                    sum++;
            }
        }

        //将原数组转化为稀疏矩阵中进行压缩存储
        //首先创建第一行中的稀疏矩阵的信息
        int chessArray2[][] = new int[sum+1][3];//行数：存储元素个数+第一行信息行 列数为三列
        chessArray2[0][0] = 11;
        chessArray2[0][1] = 11;
        chessArray2[0][2] = sum;
        //对二维数组原棋盘进行遍历，依次将棋子数据信息存入稀疏矩阵
        int count = 0;
        for (int i = 0; i < chessArray1.length; i++) {
            for (int j = 0; j <chessArray1[i].length ; j++) {
                if(chessArray1[i][j]!=0){
                    count++;
                    chessArray2[count][0] = i;//每一行的头两列存储的是位置坐标
                    chessArray2[count][1] = j;
                    chessArray2[count][2] = chessArray1[i][j];//第三列存储的是值
                }
            }
        }
        //已经存入稀疏矩阵，进行展示和存盘
        System.out.println("转化为稀疏矩阵如下所示");
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //使用序列化对象输入输出流对稀疏矩阵进行存盘和读取
        int chessArray3[][] = null;
        try {
        FileOutputStream fos = new FileOutputStream("d://map.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(chessArray2);
        FileInputStream fis = new FileInputStream("d://map.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        chessArray3 = (int[][]) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将稀疏矩阵还原为原二维数组棋盘
        int num = chessArray3[0][2];
        int chessArray4[][] = new  int[ chessArray3[0][0]][chessArray3[0][1]];
        for (int i = 1; i <= num;i++){
            int row = chessArray3[i][0];
            int col = chessArray3[i][1];
            chessArray4[row][col] = chessArray3[i][2];
        }
        //输出还原后的二维数组棋盘
        System.out.println("输出还原后的二维数组棋盘:");
        for (int[] row : chessArray4) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
