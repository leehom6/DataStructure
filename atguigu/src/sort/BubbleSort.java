package sort;

import java.text.SimpleDateFormat;
import java.util.Date;
//运行时间记录
//2020-10-26 04:08:26
//2020-10-26 04:08:37
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);//得到80000个数据，属于[0,80000)
        }
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(ft.format(date));
        bubblesort(arr);
        Date date1 = new Date();
        System.out.println(ft.format(date1));
/*        System.out.println("最终的排序结果为：");
        for(int n:arr){
            System.out.print(n+" ");
        }
        System.out.println();*/
    }

    public static void bubblesort(int[] arr) {
        int max = arr.length;//数组的元素个数
        int temp;
        boolean turn ;
        //冒泡排序法需要进行元素个数-1趟
        for (int i = 0; i < max - 1; i++) {
            turn = true;//假定该趟不发生元素之间的位置交换
            //每一趟确定一个元素
            for (int j = 0; j < max - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    turn = false;//对位置交换进行标记
                    //如果相邻元素的大小顺序相反，进行调换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
   /*         System.out.println("第"+(i+1)+"趟的的排序结果为：");
            for(int n:arr){
                System.out.print(n+" ");
            }
            System.out.println();*/
            if(turn){
                //该趟确实没有发生元素之间的位置交换，那么已经完成排序，提前结束程序
                return;
            }
        }
    }
}
