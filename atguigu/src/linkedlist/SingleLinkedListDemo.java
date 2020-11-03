package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(5, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(9, "林冲", "豹子头");
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero1);
        list.addByOrder(hero4);
        HeroNode hero5 = new HeroNode(7, "宋江", "及时雨");
        HeroNode hero6 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero7 = new HeroNode(6, "吴用", "智多星");
        HeroNode hero8 = new HeroNode(8, "林冲", "豹子头");
        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByOrder(hero5);
        list2.addByOrder(hero6);
        list2.addByOrder(hero7);
        list2.addByOrder(hero8);
        SingleLinkedList newlist = mergeByOrder(list, list2);
        newlist.list();

/*        System.out.println("原链表：");
        list.list();
        System.out.println("逆序打印单链表");
        reversePrint(list.getHead());
        System.out.println("翻转后的链表");
        reverseSingleList(list.getHead());
        list.list();*/
      /*  System.out.println("===========================");
        list.lookup(3);
        System.out.println("===========================");

        list.updateNode(new HeroNode(2, "卢昊天", "呼噜娃"));
        list.list();
        System.out.println("===========================");

        list.delNode(1);
        list.list();
        System.out.println("===========================");

        list.delNode(4);
        list.list();
        System.out.println("单链表的节点的个数=" + getLength(list.getHead()));
        System.out.printf("倒数第2个节点为："+findNodeByInverse(list.getHead(),2)+"\n");
        System.out.println();
        list.delNode(2);
        list.delNode(3);
        list.list();
        System.out.println("===========================");
        list.lookup(1);*/
    }

    //新浪面试题1：获取到单链表中有效节点的个数（如果是有头结点的链表，不统计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        //通过辅助指针遍历链表来统计节点的个数
        HeroNode temp = head.next;
        int count = 0;
        while (true) {
            if (temp == null) {
                //遍历完
                break;
            } else {
                count++;
                temp = temp.next;
            }
        }
        return count;
    }

    //新浪面试题2：从单链表中查倒数第k个节点
    //思路：1.获取单链表的总节点的个数
    // 2.计算出倒数第k个节点的正序编号
    // 3.循环遍历得到该节点
    public static HeroNode findNodeByInverse(HeroNode head, int index) {
        //链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到单链表节点个数
        int length = getLength(head);
        //进行数据的校验，超出单链表范围的可直接返回null
        if (index < 0 || index > length) {
            return null;
        }
        //当前节点
        HeroNode cur = head.next;
        //计算正序编号:例如总个数为5，倒数第2个即为整数第4个，4 = 5 + 1 - 2；
        int no = length + 1 - index;
        for (int i = 1; i < no; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //腾讯面试题：单链表的反转
//    思路：1.遍历原链表，将得到的节点逐个的通过头插法得到新的反转链表
//    2.将head.next = reversehead.next   将原链表的头节点的next指向翻转头的next域，得到新的反转单链表
    public static void reverseSingleList(HeroNode head) {
        //首先进行一次校验,没有有效节点或者只有一个有效节点，无需反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        //创建辅助的反转头
        HeroNode reversehead = new HeroNode(0, "", "");
        //cur节点用来记录当前遍历节点的位置
        HeroNode cur = head.next;
        //next用来保存cur节点的下一个节点的位置
        HeroNode next = null;
        while (true) {
            if (cur == null) {
                //遍历完单链表
                break;
            }
            //next用来保存cur节点的下一个节点的位置
            //需要注意的是，这里先检验cur非空再next = cur.next，避免了报空指针异常
            next = cur.next;
            //头插法完成反转
            cur.next = reversehead.next;
            reversehead.next = cur;
            //后移到保存过的下一个位置，进行遍历
            cur = next;
        }
        //移花接木，得到反转的单链表
        head.next = reversehead.next;
    }

    //百度面试题：逆序打印单链表，不改变原链表的结构
    //思路:做法1.利用栈,遍历压入栈中,再从栈中弹出逆序打印
    //做法2：和上一题反转单链表基本一样，但最后不执行head.next = reversehead.next 的操作也就是重新生成了一个新的链表来进行打印
    public static void reversePrint(HeroNode head) {
        //首先进行优化校验,没有有效节点,无需逆序打印
        if (head.next == null) {
            return;
        }
        //创建一个栈用来存放节点，实现逆序打印
        Stack<HeroNode> stack = new Stack<>();
        //定义一个辅助指针
        HeroNode cur = head.next;
        while (true) {
            if (cur == null) {
                //遍历完链表
                break;
            }
            stack.push(cur);//循环进栈
            //后移实现遍历
            cur = cur.next;
        }
        //弹出并打印
        while(!stack.empty()){
            System.out.println(stack.pop());
        }
/*        for (int i = 0;i < stack.size();i++){
            System.out.println(stack.pop());
            //输出的节点数量不够？？？：因为终止条件stack.size()是随着stack.pop()不断变小的，要注意！！！
            逆序打印单链表
            //HeroNode{no=4, name='林冲', nickname='豹子头'}
            //HeroNode{no=3, name='吴用', nickname='智多星'}
        }*/
    }

    //课后练习题：将两个有序单链表合并，合并后仍为有序
    public static SingleLinkedList mergeByOrder(SingleLinkedList list1,SingleLinkedList list2){
        //进行优化校验，其中有一个链表为空直接返回另一个链表即为合并后的单链表
        HeroNode head1 = list1.getHead();
        HeroNode head2 = list2.getHead();
        if(head1==null){
            return list1;
        }
        if(head2 ==null){
            return list2;
        }
        //创建合并后的单链表头
        SingleLinkedList newlist = new SingleLinkedList();
        HeroNode newhead = newlist.getHead();
        //创建三个辅助指针，分别对应合并链表，原链表1，原链表2的当前节点
        HeroNode newcur = newhead;
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        while(true){
            if(cur1==null&&cur2==null){
                //两个链表都遍历完，结束循环
                break;
            }
            if(cur1==null || cur1.no>cur2.no){
                //cur1遍历完，或者cur1大于cur2的数据，添加cur2节点
                newcur.next = cur2;
                newcur = newcur.next;
                cur2 = cur2.next;
            }
            if (cur2 == null || cur2.no > cur1.no) {
                //cur2遍历完，或者cur2大于cur1的数据，添加cur1节点
                newcur.next = cur1;
               newcur = newcur.next;
                cur1 = cur1.next;
            }
        }
        return newlist;
    }
}


//定义SingleLinkedList单链表，管理我们的英雄
class SingleLinkedList {
    //先初始化单链表，创建一个头结点
    HeroNode head = new HeroNode(0, "", "");

    //获取头结点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //先不考虑顺序问题
    //找到最后一个节点，在后边加入新的节点
    public void addNode(HeroNode hero) {
        //通过temp指针遍历链表，找到最后一个节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                //找到最后一个节点
                break;
            }
            //如果不是链表最后一个节点，temp后移
            temp = temp.next;
        }
        //在最后一个节点后边加入新的节点
        temp.next = hero;
    }

    //按照编号顺序添加节点
    public void addByOrder(HeroNode hero) {
        //通过指针遍历链表，找到位置进行添加
        HeroNode temp = head;
        //标志位：用来标记单链表中是否已经存在与hero相同编号的HeroNode节点
        boolean flag = false;
        //通过遍历找到插入位置的上一个节点
        while (true) {
            //遍历到最后一个节点仍未找到插入的位置（没有节点的编号比hero的编号大），插入到链表最后，终止循环
            if (temp.next == null) {
                break;
            }
            //如果找到一个节点的next节点的编号比添加的节点的编号大，插入到该节点的后边
            if (temp.next.no > hero.no) {
                break;
            }
            if (temp.next.no == hero.no) {
                flag = true;
                break;
            }
            //如果都不满足，继续遍历
            temp = temp.next;
        }
        if (flag == true) {
            System.out.println("链表中已经存在该编号的英雄，无法重复添加");
        } else {
            hero.next = temp.next;
            temp.next = hero;
        }

    }

    //删除指定编号的节点
    public void delNode(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //遍历完，未找到删除的节点
                break;
            }
            if (temp.next.no == no) {
                //找到
                flag = true;
                break;
            }
            //上述都不能满足，继续遍历
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到编号为%d的节点，无法进行节点删除\n", no);
        }
    }

    //根据HeroNode的编号no修改单链表的节点内容
    public void updateNode(HeroNode newHeroNode) {
        //首先判断是否为空
        if (head.next == null) {
            System.out.println("链表为空···");
            return;
        }
        //通过一个辅助指针遍历单链表，找到编号相同的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //遍历完，未找到指定编号的节点
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            //不满足上述情况，继续遍历
            temp = temp.next;
        }
        //有两种结束循环的方式：1.遍历完未找到 2.找到相应的节点
        if (flag) {
            //找到节点
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            //遍历完，未找到相同节点
            System.out.printf("未找到编号为%d的节点，无法进行修改\n", newHeroNode.no);
        }
    }

    //查看编号为no的节点
    public void lookup(int no) {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //通过一个辅助指针遍历单链表，找到编号相同的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //遍历完，未找到指定编号的节点
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            //不满足上述情况，继续遍历
            temp = temp.next;
        }
        if (flag) {
            System.out.println(temp);
        } else {
            System.out.printf("未找到编号为%d的节点\n", no);
        }
    }

    //打印单链表的所有节点
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //遍历单链表
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

//定义HeroNode对象,每个HeroNode对象就是一个节点
class HeroNode {
    public int no; //英雄的编号
    public String name;//英雄名字
    public String nickname;//英雄的称号
    HeroNode next;//指向下一个节点的指针域

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                "}";
    }
}

