package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
/*        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[8];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));*/
        int N = 80000;//定义常量N
        int[] arr = new int[N];
        int[] temp = new int[N];
        for (int i = 0; i <N; i++) {
            arr[i] = (int)(Math.random()*N);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        mergeSort(arr,0,arr.length-1,temp);
        Date date1 = new Date();
        String format1 = simpleDateFormat.format(date1);
        System.out.println(format1);
//        System.out.println(Arrays.toString(arr));
    }
    /**
     * 分
     * 通过递归把待排序数组不断地对分，直到分到一个数字不可再分后，进行归并排序（治），递归---触底反弹
     *
     * @param arr   待排序的数组
     * @param left  分治部分的左边界
     * @param right 分治部分的右边界
     * @param temp  辅助矩阵，用来进行存放排序，将来拷贝回原数组（空间换时间）
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {//只有两个数字
            int mid = (left + right) / 2;
            mergeSort(arr,left,mid,temp);//不执行
            mergeSort(arr,mid+1,right,temp);//不执行
            merge(arr,left,mid,right,temp);
        }
    }
    /**
     * 治：
     * 归并排序算法时机是一种分治思想的算法，先将数组分到最小，然后有序合并到temp数组，再拷贝回原数组，递归实现。时间复杂度为线性对数阶。
     *
     * @param arr   待排序的数组
     * @param left  分治部分的左边界
     * @param mid   分治部分的中间值
     * @param right 分治部分的右边界
     * @param temp  辅助矩阵，用来进行存放排序，将来拷贝回原数组（空间换时间）
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //left到mid为左边部分，mid+1到right为右边部分
        int i = left;//左边部分的初始索引
        int j = mid + 1;//右边部分的初始索引
        int t = 0;//temp数组的初始索引

        //左右部分进行比较，从小到大的顺序存入temp数组中
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //有一边的数已经全部存入temp中，将剩余的那一边全部存入temp
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }
        //至此，已经将待排序数组的元素全部存入temp中
        //最后第三步，将temp数组中经过排序的内容再copy回数组中原来的位置
        for (i = left, t = 0; i <= right; i++, t++) {
            arr[i] = temp[t];
        }
    }
}
