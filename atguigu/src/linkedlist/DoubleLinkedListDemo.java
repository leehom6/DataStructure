package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(9, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(2, "林冲", "豹子头");
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
/*      list.addNode(hero1);
        list.addNode(hero2);
        list.addNode(hero3);
        list.addNode(hero4);*/
        System.out.println("原链表：");
        list.list();
        System.out.println("删除节点后：");
        list.delNode(2);
        list.list();
        System.out.println("查看所修改节点：");
        list.updateNode(new HeroNode2(1,"宋江","老八鼻祖"));
        list.lookup(1);
    }


}


//在SingleLinkedList单链表的基础上改造得到DoubleLinekedList双链表
class DoubleLinkedList {

    //初始化链表，定义一个头结点
    HeroNode2 head = new HeroNode2(0, "", "");

    //获取头结点
    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到单向链表
    //先不考虑顺序问题
    //找到最后一个节点，在后边加入新的节点
    public void addNode(HeroNode2 hero) {
        //通过temp指针遍历链表，找到最后一个节点
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                //找到最后一个节点
                break;
            }
            //如果不是链表最后一个节点，temp后移
            temp = temp.next;
        }
        //在最后一个节点后边加入新的节点,双向连接
        temp.next = hero;
        hero.pre = temp;
    }

    //按照编号顺序添加节点
    public void addByOrder(HeroNode2 hero) {
        //通过指针遍历链表，找到位置进行添加
        HeroNode2 temp = head;
        //标志位：用来标记双链表中是否已经存在与hero相同编号的HeroNode2节点
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
            hero.pre = temp;
            if (hero.next != null) {
                hero.next.pre = hero;
            }
        }
    }

    //删除指定编号的节点
    public void delNode(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;//双链表可以实现对自身的删除，不用借助上一个节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //遍历完，未找到删除的节点
                break;
            }
            if (temp.no == no) {
                //找到
                flag = true;
                break;
            }
            //上述都不能满足，继续遍历
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        } else {
            System.out.printf("未找到编号为%d的节点，无法进行节点删除\n", no);
        }
    }

    //根据HeroNode的编号no修改双链表的节点内容（和单链表一样）
    public void updateNode(HeroNode2 newHeroNode) {
        //首先判断是否为空
        if (head.next == null) {
            System.out.println("链表为空···");
            return;
        }
        //通过一个辅助指针遍历单链表，找到编号相同的节点
        HeroNode2 temp = head.next;
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

    //查看编号为no的节点（和单链表一样）
    public void lookup(int no) {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //通过一个辅助指针遍历双链表，找到编号相同的节点
        HeroNode2 temp = head.next;
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

    //打印双链表的所有节点（和单链表一样）
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //遍历单链表
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    //成员变量
    int no;
    String name;
    String nickname;
    HeroNode2 next;
    HeroNode2 pre;//比单链表多一个前向指针

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //ToString方法

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

