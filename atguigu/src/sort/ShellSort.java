package sort;

import java.text.SimpleDateFormat;
import java.util.Date;
//2020-10-27 07:42:41
//2020-10-27 07:42:41
public class ShellSort {
    public static void main(String[] args) {
/*        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0,789,79,456,1,68,7,578,57};
        shellsort(arr);
        System.out.println("最终的排序结果为：");
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();*/
        int[] arr = new int[800000];
        for (int i = 0;i<800000;i++){
            arr[i] = (int)(Math.random()*800000);//得到80000个数据，属于[0,80000)
        }
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(ft.format(date));
        shellsort(arr);
        Date date1 = new Date();
        System.out.println(ft.format(date1));
/*        System.out.println("最终的排序结果为：");
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();*/
    }






    public static void shellsort(int[] arr) {
        int max = arr.length;
        int temp ;
        int j;
        //gap是每一次分组的步长，分组步长每次缩短一半，当完成步长为1的排序时，就完成了整个数据集的排序
        for (int gap = max / 2; gap > 0; gap /= 2) {//步长:用来分组
            for (int i = gap; i < max; i++) {//分组后的每一组切换,其中i为每一组的最后一个元素                  问题所在，不能从最后边开始进行排序，排序的趟数不完整，待修改
                j = i - gap;//在每一组进行插入排序
                temp = arr[i];//先把最后一个待插入值进行保存
                if (arr[j] > temp) {//待插入值比有序表的最大值小，需要进行插入才执行操作否则直接插入到表后即可
                    while (j >= 0 && arr[j] > temp) {
                        arr[j + gap] = arr[j];//表中大元素后移
                        j = j - gap;
                    }
                    arr[j + gap] = temp;
                }
            }
        }
    }
}
