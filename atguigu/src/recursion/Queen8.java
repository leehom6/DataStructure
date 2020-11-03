package recursion;

public class Queen8 {
    static int max = 8;//8皇后
    static int[] arr = new int[max];//使用一维数组来表示八皇后一次在每一行中的位置，其中数组的下标表示行位置，数组中的值表示列位置
    static int count = 0;
    static int judgecount = 0;

    public static void main(String[] args) {
        check(0);
        System.out.printf("一共有%d种解法\n",count);
        System.out.printf("一共进行了%d次的判断\n",judgecount);
    }

    /**
     * 冲突判断：八个皇后不能处于同一行、同一列、同一斜线
     *
     * @param n 用来表示对第几个皇后的当前摆放位置进行冲突判断
     * @return false:冲突     true:不冲突
     */
    public static boolean judge(int n) {
        //由于我们在每一行只放置一个皇后，所以不用判断同一行的冲突
        //同一列的冲突,同斜线的冲突
        judgecount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(i - n) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * check 表示测试第n个皇后的摆放的所有可能
     *
     * @param n 表示正在防止第n个皇后，n从0开始
     */
    public static void check(int n) {
        if (n == 8) {
            //说明已经放置好八皇后位置，进行输出打印
            for (int i = 0; i < 8; i++) {
                System.out.printf("arr[%d] = %d" + " ", i, arr[i]);
            }
            System.out.println();
            count++;
            return;
        }
        //对该行的每一个位置进行皇后的摆放尝试
        for (int i = 0; i < 8; i++) {    //i++  更新检验所有的位置
            arr[n] = i;
            if (judge(n)) {
                //该位置不冲突，则将皇后摆放在该位置，并找下一个皇后的所有可能位置
                check(n + 1);
            }
        }
    }
}
