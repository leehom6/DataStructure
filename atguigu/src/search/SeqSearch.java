package search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {8, 16, 52, 48, 97, 134, 5674, 61};
        int res = sequenceSearch(arr, 52);
        if (res == -1){
            System.out.println("没有找到");
        }else{
            System.out.println("找到了，对应的数组元素的下标为："+res);
        }
    }

    /**
     * 线性查找：按顺序遍历查找某个值，找到时返回下标即可
     *
     * @param arr
     * @param value
     * @return
     */
    public static int sequenceSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
