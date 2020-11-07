package hashtable;

//hashTab（哈希表，也称为散列表），通过哈希映射来管理多条链表，可以提速，可以用作缓存（在轻量级时，代替redis，memcache等缓存服务）
//其结构是数组+链表的形式，用链表数组来管理多条链表，其中通过哈希映射来将数据归类管理到各条对应的链表
public class HashTab {
    public EmpLinkedList[] empLinkedListArray;
    //构造函数
    public HashTab() {
        empLinkedListArray = new EmpLinkedList[10];
        for (int i = 0; i < 10; i++) {
            empLinkedListArray[i] = new EmpLinkedList();//对数组中的每个链表都进行初始化
        }
    }

    //先定义映射函数hashfun()
    public int hashFun(int id){
        return id % 10;//将哈希表定义为序号为0-9的十个链表
    }

    public void add(int id,String name){
        int linkedListNo = hashFun(id);
        empLinkedListArray[linkedListNo].add(id,name);
    }

    public void list(){
        for (int i = 0; i <10 ; i++) {
            if(empLinkedListArray[i].head == null){
                System.out.printf("第"+i+"条链表中存入的员工信息：\t 无\n");
            }else{
                System.out.printf("第"+i+"条链表中存入的员工信息：\t");
                empLinkedListArray[i].list();
            }
        }
    }

    public void findById(int id){
        int no = hashFun(id);
        boolean res = empLinkedListArray[no].findById(id);
        if(res == true){
            System.out.println("在第"+no+"条链表找到了员工信息\n");
        }else{
            System.out.println("在哈希表中没有找到该员工的信息\n");
        }
    }

}
