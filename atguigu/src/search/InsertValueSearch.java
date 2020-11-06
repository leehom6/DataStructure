package search;

import java.util.ArrayList;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        ArrayList arrayList = insertValueSearch(arr, 10, 0, arr.length - 1);
        if (arrayList.size() == 0) {
            System.out.println("没有找到");
        } else {
            System.out.println(arrayList);
        }
    }

    /**
     * 插值查找算法：适用于分布均匀的有序表，和二分查找的区别在于mid的计算公式
     * 二分查找的mid是固定折半二分  mid = left + 0.5(right - left)
     * 插值查找的mid是根据findVal在与数组范围的比值  mid = left+ (right - left)*(findVal - arr[left])/(arr[right] - arr[left])
     * 关键字分布均匀时速度快，不均匀时未必有二分查找快
     * @param arr
     * @param findVal
     * @param left
     * @param right
     * @return
     */
    public static ArrayList insertValueSearch(int[] arr, int findVal, int left, int right) {
        System.out.println("调用。。。");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {//这个时候必须加上边界判断，否则我们得到的mid可能会出现数组越界异常！！！
            ArrayList<Integer> list = new ArrayList<>();
            return list;
        } else {
            int mid = left+ (right - left)*(findVal - arr[left])/(arr[right] - arr[left]);
            if (findVal > arr[mid]) {
                return insertValueSearch(arr, findVal, mid + 1, right);
            } else if (findVal < arr[mid]) {
                return insertValueSearch(arr, findVal, left, mid - 1);
            } else {
                //findVal == arr[mid] 找到了，左右邻近值搜索
                ArrayList<Integer> list = new ArrayList<>();
                list.add(mid);
                int index = mid - 1;//邻近值的索引
                while (index >= left && arr[index] == findVal) {
                    list.add(index);
                    index--;
                }
                index = mid + 1;
                while (index <= right && arr[index] == findVal) {
                    list.add(index);
                    index++;
                }
                return list;
            }
        }
    }
}
