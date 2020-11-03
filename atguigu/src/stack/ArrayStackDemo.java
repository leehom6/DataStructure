package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试用单链表来实现的堆栈功能
        ArrayStack stack = new ArrayStack(5);
        Scanner scanner = new Scanner(System.in);
        int num;
        while(true){
            System.out.println("请输入你想要执行的栈的操作");
            System.out.println("u:push");
            System.out.println("o:pop");
            System.out.println("l:list");
            System.out.println("f:isfull");
            System.out.println("e:empty");
            char ch = scanner.next().charAt(0);
            switch (ch){
                case 'u':
                    System.out.println("请输入你要压入栈的数字");
                    num = scanner.nextInt();
                    stack.push(num);
                    break;
                case 'o':
                    try {
                        System.out.println(stack.pop());
                    }catch (Exception e){
                        System.out.println("栈空");
                    }
                    break;
                case 'l':
                    stack.list();
                    break;
                case 'f':
                    System.out.println(stack.isfull());
                    break;
                case 'e':
                    System.out.println(stack.isempty());
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack {
    int top;//指针
    int[] stack;//用数组模拟栈
    int maxsize;//数组最大容量
    public ArrayStack(int maxsize){
        top = -1;
        this.maxsize = maxsize;
        stack  = new int[maxsize];
    }

    public boolean isfull() {
        return top == maxsize - 1;
    }

    public boolean isempty() {
        return top == -1;
    }
    //元素进栈
    public void push(int n){
        if(isfull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = n;
    }
    //元素出栈
    public int pop(){
        if(isempty()){
            throw new RuntimeException();
        }
        int temp = stack[top];
        top--;
        return temp;
    }
    //展示栈中元素
    public void list(){
        if(isempty()){
            System.out.println("栈空");
            return;
        }

            for (int i = top; i >= 0 ; i--) {
                System.out.println(stack[i]);
            }

    }

}