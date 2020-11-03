package linkedlist;

public class josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(125);
        list.countBoy(10,20,125);
    }
}

//定义环形单链表
class CircleSingleLinkedList {
    private Boy first = null;//头指针，不能动

    //构建环形单链表
    public void add(int num) {
        if (num < 1) {
            System.out.println("数字不符合逻辑");
            return;
        }
        Boy curBoy = null;//辅助指针
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                curBoy = boy;
                boy.setNext(first);
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历打印环形单链表
    public void list() {
        if (first == null) {
            System.out.println("队列中没有一个孩子~~~");
            return;
        }
        Boy curBoy = first;//辅助指针
        while (true) {
            //打印
            System.out.println("第" + curBoy.getNo() + "个孩子");
            if (curBoy.getNext() == first) {
                //遍历完环形链表
                break;
            }
            //后移辅助指针实现遍历
            curBoy = curBoy.getNext();
        }
    }
    //一共total个小孩，从第startNo个小孩开始报数，每次报到count出圈
    public void countBoy(int startNo, int count, int total) {
        //首先进行优化校验
        if (first == null || startNo < 1 || count > total){
            System.out.println("输入参数有误，请重新输入···");
            return;
        }
        //首先遍历得到helper辅助指针，helper指针是保持紧跟在first指针后边的
        Boy helper = first;
        while(true){
            if(helper.getNext()==first){
                //跟在first后面，完成遍历
                break;
            }
            //后移实现遍历
            helper = helper.getNext();
        }

        //将first 移到第k个孩子处开始报数
        for(int i = 0; i < startNo-1 ;i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数到count的孩子出圈
        while(true){
            if(first==helper){
                //只剩下一个孩子，退出循环结束报数
                break;
            }
            //循环完成一次孩子报数出圈
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //打印出圈孩子的信息
            System.out.printf("出圈的孩子编号为：%d\n",first.getNo());
            //将first和helper进行移动
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最终留在圈中的幸运星的编号是：%d\n",helper.getNo());
    }
}


//定义节点类型Boy
class Boy {
    private int no;//编号
    private Boy next;//后向指针

    //构造器
    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
