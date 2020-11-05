package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] rags){
/*        int[] arr=new int[]{3,4,6,7,2,7,2,8,0};
        quicksort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i <80000 ; i++) {
            arr[i] = (int)(Math.random()*80000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(date));
        quicksort(arr,0,arr.length-1);
        Date date1 = new Date();
        System.out.println(simpleDateFormat.format(date1));
//        System.out.println(Arrays.toString(arr));

    }

    /**
     *
     * @param arr 待排序数组
     * @param start 排序头
     * @param end 排序尾
     */
    public static void quicksort(int[] arr,int start,int end){
        //递归结束条件，只有一个数，无需排序，结束递归
        if(start<end){
            int low = start;//左指针
            int high = end;//右指针
            int stardand = arr[start];//将待排序部分的第一个元素作为排序的标准数
            while(low < high){
                //两个指针还没有移动到相同位置时，就继续循环
                while(low < high && arr[high] >= stardand){
                    high--;
                }
                arr[low] = arr[high];
                while(low < high && arr[low] <= stardand){
                    low++;
                }
                arr[high] = arr[low];
            }
            //左右指针指向相同位置，退出循环，此时将标准数放入指针位置
            arr[high] = stardand;
            //继续递归处理左边所有小的数字
            quicksort(arr,start,low);
            //继续递归处理右边所有大的数字
            quicksort(arr,high+1,end);
        }
        }


}
