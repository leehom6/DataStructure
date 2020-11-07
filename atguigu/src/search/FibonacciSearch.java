package search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxsize = 20;//定义fibonacci数列的长度
    public static void main(String[] args){
//        int arr[] = {1,8,10,56,77,89,154,254,1234,1546};
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        System.out.println(fibnacciSearch(arr,56));
    }

    /** 定义fibonacci数组
     * 先定义一个fibonacci的工具数组，用来管理待查找数组的索引,其实就是一把尺子，帮助我们对数组进行黄金分割点的查找
     * @return
     */
    public static int[] fib(){
        int[] f = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxsize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    /**
     * 用非递归的方式来实现fibonacci查找算法
     * 原理：找到一个mid   ---->   f[k] - 1 = f[k-1] - 1 + 1(mid位置) +  f[k-2]-1，对数组进行分割
     * @param arr 待查找数组
     * @param findVal 查找值
     * @return
     */
    public static int fibnacciSearch(int[] arr, int findVal){
        int[] fib = fib();  //得到fibonacci数列工具
        int k = 0; //用来记录当前所用的fibonacci数列的下标
        int low = 0;//用来表示数组的左边界
        int high = arr.length-1;//用来表示数组的右边界
        //找到一个适合数组大小的fibonacci数列的值，fibonacci的
        while(high > fib[k]-2){//数组长度改造后应该<=f[k] - 1
            k++;
        }
        //由于high < = fib[k]， 所以我们需要对arr拷贝一份出去并填充到fib[k]的长度，方便后边的查找
        int[] temp = Arrays.copyOf(arr,fib[k]-1);//  f[k] - 1 = f[k-1] - 1 + 1(mid位置) +  f[k-2]-1,这里将数组长度改造为f[k]-1，扩充部分默认为0
        for(int i = high+1;i <temp.length;i++){
            temp[i] = arr[high];//使用arr数组的最后一个数对扩充部分进行填充
        }
        //开始进行查找，查找过程low不断右移，high 不断左移，mid不断按照 f[k] - 1 = f[k-1] - 1 + 1(mid位置) +  f[k-2]-1 寻找“黄金分割点”
        int mid;
        while(low <= high){
            mid = low + (fib[k-1] - 1);// f[k] - 1 = f[k-1] - 1 + 1(mid位置) +  f[k-2]-1  找到mid 的位置，将查找部分分为f[k-1] - 1 和 f[k-2]-1两部分
            if(findVal > temp[mid]){//向左边查找，左边我们设置为f[k-1]-1个元素
                low = mid+1;
                k=k-2;
            }else if(findVal < temp[mid]){//向右边查找，右边我们设置为f[k-2]-1 个元素
                high = mid -1;
                k=k-1;
            }else{
                    //首先要理解两点   1.由于temp是由arr数组扩充到fib[k]长度的，所以mid有可能出现超出arr的原始右边界的情况，一旦超出得到的全都是扩充的arr[high]值
                    //               2.high在我们的查找过程中只有向左移的操作，因此不会出现越出arr原始右边界的情况
                if(mid <= high){
                    return mid;//没有越界，直接返回
                }else{
                    return high;//越界，找到的mid 在扩充部分，其实就是arr[high]的值
                }
            }
        }
        //出现low > high,全部查找过，没有找到
        System.out.println("没有找到");
        return -1;
    }
}
