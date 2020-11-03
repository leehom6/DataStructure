package queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    //测试用主函数
    public static void main(String[] args) {
        //主函数测试
        System.out.println("测试数组队列的数据结构和操作逻辑是否正确");
        //先创建一个测试用数组队列
        ArrayQueue queue = new ArrayQueue(5);
        char key = ' ';
        //测试程序的循环控制变量
        boolean loop = true;
        while(loop){
            System.out.println("输入a添加元素到数组队列");
            System.out.println("输入g取出数组队列的头元素");
            System.out.println("输入s展示数组队列的所有元素");
            System.out.println("输入h查看数组队列的头元素");
            System.out.println("输入e退出测试程序");
            Scanner scanner = new Scanner(System.in);//接受键盘输入
            key = scanner.nextLine().charAt(0);
            switch (key){
                case 'a':
                    System.out.println("输入你想要的添加到队列的元素：");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try {
                        System.out.printf("获取队列的头元素并展示：%d\n", queue.getQueue());
                        System.out.println();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 's':
                    System.out.println("展示所有的队列元素：");
                    queue.showQueue();
                    break;
                case 'h':
                    try{
                    System.out.printf("队列的头元素展示：%d\n",queue.headQueue());
                    System.out.println();}
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                   loop = false;
                   scanner.close();
                    System.out.println("退出测试程序成功!");
                   break;
                default:
                    break;
            }
        }
    }
}

//队列的数组实现
    class ArrayQueue {
    private int maxsize;//队列最大容量
    private int front;//队列头，指向队列的第一个元素的前一个位置
    private int rear;//队列尾，指向队列的最后一个元素的位置
    private int[] array;//用来存放数据，模拟队列

    //      构造方法
    public ArrayQueue(int arrayMaxsize) {
        maxsize = arrayMaxsize;
        front = -1;
        rear = -1;
        array = new int[maxsize];
    }

    //判断是否队满
    public boolean isFull() {
        return rear == maxsize - 1;
    }

    //判断是否队空
    public boolean isEmpty() {
        return rear == front;
    }

    //元素入队列
    public void addQueue(int x) {
        if (isFull()) {
            System.out.println("队列已满，无法将数据存入队列");
        } else {
            rear++;
            array[rear] = x;
        }
    }

    //元素出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new  RuntimeException("队列空，无法从队列中取出元素");
        } else {
            front++;
            return array[front];
        }
    }
    //显示所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，无法进行展示");
        }else{
            for (int i = 0; i < array.length;i++){
                System.out.printf("%d\t",array[i]);
            }
            System.out.println();
        }
    }

    //显示队列头数据，不是取出
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法展示队列头元素");
        } else {
            return array[front + 1];//不能改变队列头的位置
        }
    }

}




