package hashtable;
//有序链表，存储员工信息
public class EmpLinkedList {
    Emp head = null; // 先创建一个头指针
    /**
     * 链表节点的有序插入
     *
     * @param id
     * @param name
     */
    public void add(int id, String name) {
        Emp emp = new Emp(id, name);
        if (head == null) {
            //链表为空
            head = emp;
            head.next = null;
        } else {
//            链表非空时，先找到应该有序插入到的链表的位置
            if (id <= head.id) {//如果要插入的节点的id小于头结点的id
                Emp temp = head;
                head = emp;
                emp.next = temp;
                return;
            }
            Emp curEmp = head;
            while (curEmp.next!=null&&id > curEmp.next.id) {//找到小于等于id的节点，将新的节点插入到curEmp.next 节点的前面
                curEmp = curEmp.next;
            }
            //找到了插入位置
            emp.next = curEmp.next;
            curEmp.next = emp;
        }
    }

    public void list() {
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf(curEmp.id + ":" + curEmp.name+"\t");
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public boolean findById(int id) {
        Emp curEmp = head;
        while (curEmp != null && curEmp.id != id) {
            curEmp = curEmp.next;
        }
        if (curEmp != null) {
            return true;//找到了
        }
        return false;
    }
}
