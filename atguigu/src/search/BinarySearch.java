package search;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
//        int[] arr = {8, 8, 10, 89, 1000, 1000, 1000, 1024};
        ArrayList<Integer> list = binarySearchPro(arr, 10, 0, arr.length - 1);
        if (list.size() == 0) {
            System.out.println("没有找到");
        } else {
            System.out.println(list);
        }
    }

    /**
     * 二分查找,递归查找的思想
     * 使用二分查找的前提，必须是有序数组
     *
     * @param arr   查找所属的有序数组
     * @param value 查找值
     * @param left  查找范围的左边界
     * @param right 查找范围的右边界
     * @return
     */
    public static int binarySearch(int[] arr, int value, int left, int right) {
        System.out.println("调用。。。");
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;//说明已经全部查找过了，并且没有查找到元素
        } else {
            int mid = (left + right) / 2; //查找范围的中间值
            if (value == arr[mid]) {
                //恰好等于mid对应元素的值，返回结束递归
                return mid;
            } else if (value > arr[mid]) {
                return binarySearch(arr, value, mid + 1, right);
            } else {
                return binarySearch(arr, value, left, mid - 1);
            }
        }
    }

    /**
     * 二分查找，课后思考题，将要求升级为找到有序数组中的所有值为value 的元素
     * 其实在原有的算法中做一点改动就可以实现，改动在返回Mid处的元素值的步骤
     * 使用二分查找的前提，必须是有序数组
     *
     * @param arr   查找所属的有序数组
     * @param value 查找值
     * @param left  查找范围的左边界
     * @param right 查找范围的右边界
     * @return
     */
    public static ArrayList binarySearchPro(int[] arr, int value, int left, int right) {
        System.out.println("调用。。。");
        if (left > right) {
            ArrayList<Integer> list = new ArrayList<>();
            return list;//说明已经全部查找过了，并且没有查找到元素
        } else {
            int mid = (left + right) / 2; //查找范围的中间值
            if (value == arr[mid]) {
                //恰好等于mid对应元素的值，返回结束递归
                /* return mid;*/
                //在这里进行改造，在找到mid处的值与value相同后，分别向左向右查找邻近的值是否也等于mid，并将所有找到的下标加入数组find中进行返回（因为是有序表）
                ArrayList<Integer> list = new ArrayList<>();
                int i = 0;
                int index = mid;
                //从mid向左进行查找
                while (index >= left && arr[index] == value) {
                    list.add(index);
                    index--;
                }
                index = mid + 1;
                while (index <= right && arr[index] == value) {
                    list.add(index);
                    index++;
                }
                return list;
                //
            } else if (value > arr[mid]) {
                return binarySearchPro(arr, value, mid + 1, right);
            } else {
                return binarySearchPro(arr, value, left, mid - 1);
            }
        }
    }
}