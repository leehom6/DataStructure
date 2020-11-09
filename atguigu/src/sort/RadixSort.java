package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
/*        int[] arr = {53, 3, 542, 748, 14, 214,46,897,152};
        radixSort(arr);*/
        int N = 8000000;
        int[] arr = new int[N];
        for (int i = 0; i <arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        radixSort(arr);
        Date date1 = new Date();
        System.out.println(simpleDateFormat.format(date1));
    }

    /**
     * 基数排序，是桶排序的扩展，是一种用空间换时间的典型算法。速度比快速排序和归并排序快，但容易出现内存溢出错误
     *
     * @param arr 传入的待排序数组
     */
    public static void radixSort(int[] arr) {
        //先定义一个二维数组，表示10个桶来装对应位基数的数据,每个桶的最大容量就是数组中的元素个数，以防万一
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中装入的数的个数，我们使用一个一维数组来记录,数组长度为10，对应十个桶
        int[] bucketElementsCount = new int[10];

        //首先找到待排序数组中的最大值元素
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int length = (max + "").length();//表示数组中最大元素的位数，也决定了我们需要进行基数排序的轮数
        System.out.println(length);


        //开启对应轮数的循环基数排序
        int n = 1;//用来辅助求基数值
        for (int j = 0; j < length; j++) {
            //对数组中的每一个元素求对应位数的值，然后放入对应的桶中
            for (int i = 0; i < arr.length; i++) {
                int radix = arr[i] / n % 10;//对数组中的每一个元素求对应位数的值
                bucket[radix][bucketElementsCount[radix]] = arr[i];//放入基数值对应桶的对应位置
                bucketElementsCount[radix]++;//对应桶的计数值自增1；
            }
            //已经全部放入对应的桶中，需要从10个桶中再按顺序拷贝回数组中
            int index = 0;//待排序数组的当前索引，用来回拷
            for (int i = 0; i < 10; i++) {
                if (bucketElementsCount[i] != 0) {
                    //如果第i个桶中的计数值不为0，进行遍历取出，放回数组中
                    for (int k = 0; k < bucketElementsCount[i]; k++) {
                        arr[index] = bucket[i][k];
                        index++;
                    }
                    bucketElementsCount[i] = 0;//将该桶的计数值清零，不影响下一轮基数排序的计数
                }
            }

            n = n * 10;//用来下一轮的基数值求取
            /*System.out.println("第" + j + "轮的基数排序的结果是" + Arrays.toString(arr));*/
        }
    }
}
