package stack;

import java.util.Scanner;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        //测试用单链表来实现的堆栈功能
        LinkedListStack stack = new LinkedListStack();
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

//借助单链表来实现堆栈
class LinkedListStack {
    int maxsize = 3;
    SingleLinkedList list = new SingleLinkedList();
    Node top = list.head;//top指针指向栈顶元素，也就是链表头结点后第一个节点

    //push
    public void push(int num) {
        if (isfull()) {
            System.out.println("栈满··");
            return;
        }
        list.add(new Node(num));
    }

    //pop
    public int pop() {
        return list.del();
    }



    //list
    public void list(){
        list.list();
    }

    //isfull
    public boolean isfull() {
        return list.num() == maxsize;
    }

    //isempty
    public boolean isempty(){
        return list.isempty();
    }
}

//通过单链表来模拟堆栈后进先出的逆序存储方式，可以通过头插法
//首先定义节点
class Node {
    int no;
    Node next;

    //构造器
    public Node(int no) {
        this.no = no;
    }
}

//定义单链表，通过头插法来实现逆序存储和打印
class SingleLinkedList {
    //初始化链表，创建一个头结点
    Node head = new Node(0);

    //判断链表是否为空
    public boolean isempty() {
        return head.next == null;
    }


    //头插法添加节点
    public void add(Node node) {
        node.next = head.next;
        head.next = node;
    }

    //从头部取出节点数据
    public int del() {
        if(isempty()){
            throw new RuntimeException();
        }
        int pop = head.next.no;
        head.next = head.next.next;
        return pop;
    }

    //从头部开始逆序遍历展示链表
    public void list() {
        if (isempty()) {
            System.out.println("栈空···");
            return;
        }
        Node node = head.next;
        while (true) {
            if (node != null) {
                System.out.println(node.no);
                node = node.next;
            } else {
                break;
            }
        }
    }

    //遍历链表得到链表有效节点的个数，为了辅助判断堆栈是否栈满
    public int num() {
        if (isempty()) {
            return 0;
        }
        int num = 0;
        Node node = head.next;
        while (true) {
            if (node != null) {
                num++;
                node = node.next;
            } else {
                break;
            }
        }
        return num;
    }
}