package sort;

import java.text.SimpleDateFormat;
import java.util.Date;
//运行时间记录
//2020-10-26 04:43:34
//2020-10-26 04:43:41
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[800000];
        for (int i = 0;i<800000;i++){
            arr[i] = (int)(Math.random()*800000);//得到80000个数据，属于[0,80000)
        }
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(ft.format(date));
        selectsort(arr);
        Date date1 = new Date();
        System.out.println(ft.format(date1));
/*                System.out.println("最终的排序结果为：");
        for(int n:arr){
            System.out.print(n+" ");
        }
        System.out.println();*/
    }


    //选择排序需要进行数组元素个数-1趟，每一趟需要进行（数组元素个数-趟数次）的比较
    public static void selectsort(int[] arr) {
        int max = arr.length;
        int min;//定义为每一趟中的最小值
        int temp;//辅助变量，用来比较
        int index = 0;//记录最小数据的下标
        boolean flag;
        for (int i = 0; i < max - 1; i++) {
            flag = false;
            min = arr[i];
            for (int j = i; j < max - 1; j++) {
                if (min > arr[j + 1]) {
                    flag = true;
                    min = arr[j + 1];
                    index = j + 1;
                }
            }
//            交换最小数据
            if (flag) {
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }
}
