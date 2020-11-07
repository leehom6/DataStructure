package hashtable;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab();
        int id = 0;
        String name = "";
        while(true){
            System.out.println("输入add 添加员工信息");
            System.out.println("输入find 寻找员工信息");
            System.out.println("输入list 遍历哈希表中的员工信息");
            System.out.println("exit 退出该程序");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            switch (input){
                case "add":
                    System.out.println("请输入要添加的员工的id：");
                     id = scanner.nextInt();
                    System.out.println("请输入要添加的员工的姓名：");
                     name = scanner.next();
                    hashTab.add(id,name);
                    break;
                case"find" :
                    System.out.println("请输入要查找的员工的id：");
                     id = scanner.nextInt();
                     hashTab.findById(id);
                     break;
                case "list":
                    hashTab.list();
                    break;
                case"exit":
                    return;
                default:
                    System.out.println("信息输入错误，请重新输入：");
                    break;
            }
        }
    }

}
