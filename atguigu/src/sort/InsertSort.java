package sort;
//2020-10-27 07:42:26
//2020-10-27 07:42:27
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);//得到80000个数据，属于[0,80000)
        }
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(ft.format(date));
        insertsort(arr);
        Date date1 = new Date();
        System.out.println(ft.format(date1));

/*        System.out.println("最终的排序结果为：");
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();*/
    }

    /**
     * 插入排序：通过构造有序表，将后边的元素找到正确位置依次有序插入有序表中
     *
     * @param arr
     */
    public static void insertsort(int[] arr) {
        //从第2个元素开始进行插入排序，默认第一个元素就是一张有序表
        int insertval;
        int index;
        for (int i = 1; i < arr.length; i++) {
            insertval = arr[i];
            index = i - 1;
       /*     for(int  j = i - 1; j >=0;j-- ){
                //如果有序表中的元素大于插入值，则将该元素后移
                if(arr[j]>insertval){
                    arr[j+1] = arr[j];
                }else{
                    //如果有序表中的元素小于等于插入值，则将值插入到该元素的后面
                    arr[j+1] = insertval;
                }
            }*/
            while ( index >= 0&&arr[index] > insertval) {
                arr[index+1] = arr[index];//如果有序表中的元素大于插入值，则将该元素后移
                index--;//继续对前面的元素进行校验
            }
            arr[index+1] = insertval;
        }
    }
}
