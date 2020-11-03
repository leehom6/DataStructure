package recursion;

public class MiGong {
    public static void main(String[] args) {
        //提前构建地图
        int map[][] = new int[8][8];
        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
            map[i][0] = 1;
            map[i][7] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[5][5] = 1;
        map[6][5] = 1;
        map[4][6] = 1;
        //打印地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(setWay(map,1,1,1,2));
        //再次打印地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 迷宫问题：用0表示还没走过，用1表示墙，用2表示走的通的路径，用3表示走不通的死路
     *1
     * @param map 用二维数组来表示二维地图
     * @param i   起点横坐标
     * @param j   起点纵坐标
     * @param x   终点横坐标
     * @param y   终点纵坐标
     * @return 是否能找到迷宫的终点
     */
    public static boolean setWay(int[][] map, int i, int j, int x, int y) {
        if(i>=7||i<=0||j>=7||j<=0){
            System.out.println();
            return false;
        }
        if (map[x][y] == 2) {
            //表示已经走到终点（x,y），找到了迷宫的出口
            return true;
        } else {
            //对当前坐标（i,j）进行递归查找出口路径，还没走到终点
            //如果当前点为0，则按照策略进行下一步移动
            if (map[i][j] == 0) {
                //首先假定可以走通
             map[i][j] = 2;
                if (setWay(map, i + 1, j, x, y)) {
                    //下
                    return true;
                } else if (setWay(map, i, j + 1, x, y)) {
                    //右
                    return true;
                } else if (setWay(map, i - 1, j, x, y)) {
                    //上
                    return true;
                } else if (setWay(map, i, j - 1, x, y)) {
                    //左
                    return true;
                } else {
                    //如果四个方向都走不通,将这个点标记为死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //如果当前点不是0，那么可能是1,2,3,都不能再走这个点(2：迷宫的正确路径不会形成圈，所以不会重复走哪一个点)
                return false;
            }
        }
    }
}
