package tree;

public class ThreadBinaryTree {
    public static void main(String[] args) {
        //创建我们需要的二叉树
        HeroNode1 root = new HeroNode1(1, "宋江");
        HeroNode1 hero2 = new HeroNode1(2, "卢俊义");
        HeroNode1 hero3 = new HeroNode1(3, "吴用");
        HeroNode1 hero4 = new HeroNode1(4, "林冲");
        HeroNode1 hero5 = new HeroNode1(5, "关胜");
        HeroNode1 hero6 = new HeroNode1(6, "武松");
        HeroNode1 hero7 = new HeroNode1(7, "李逵");
        root.setLeft(hero2);
        root.setRight(hero3);
        hero2.setLeft(hero4);
        hero2.setRight(hero5);
        hero3.setLeft(hero6);
        hero3.setRight(hero7);
        //进行对二叉树的线索化以及测试
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedBinaryTree();//4251637
        System.out.println(root.getLeft());
        System.out.println(root.getRight());
        System.out.println(hero7.getLeft());
        System.out.println(hero7.getRight());
        //前序遍历二叉树
        System.out.println("前序遍历");
        threadedBinaryTree.preOrder();
    }
}

//构造一个二叉树的类,可以实现线索化的功能
class ThreadedBinaryTree {
    private HeroNode1 root;
    private HeroNode1 pre;//记忆前驱节点

    public ThreadedBinaryTree() {
    }
    public ThreadedBinaryTree(HeroNode1 root) {
        this.root = root;
    }

    public HeroNode1 getRoot() {
        return root;
    }

    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.postOrder();
    }

    //前序查找
    public HeroNode1 preOrderSearch(int no) {
        if (root == null) {
            System.out.println("该根节点的树为空！！");
            return null;
        } else {
            return root.preOrderSearch(no);
        }
    }

    //中序查找
    public HeroNode1 infixOrderSearch(int no) {
        if (root == null) {
            System.out.println("该根节点的树为空！！");
            return null;
        } else {
            return root.infixOrderSearch(no);
        }
    }

    //后序查找
    public HeroNode1 postOrderSearch(int no) {
        if (root == null) {
            System.out.println("该根节点的树为空！！");
            return null;
        } else {
            return root.postOrderSearch(no);
        }
    }

    /**
     *中序线索化遍历,采取非递归的方式进行
     * 其实这个遍历使用两种方式结合进行：
     * 1.用中序遍历的方式找到第一个应该遍历的节点
     * 2.当有线索时，就直接通过线索线性查找，速度快且方便
     * 3.后续线索如果中断就按照中序遍历左中右的遍历顺序从当前节点向左查找的尽头找到下一个应该遍历的节点，直到完成全部遍历
     */
     public void infixThreadList(){
        HeroNode1 node= root;
        while(node!=null){//遍历到节点为null时，说明已经遍历完整个树，结束遍历跳出循环
            while(node.getLeftType()==0){
                //向左节点循环，找到第一个拥有前驱节点的左节点，就是我们需要的线索化中的第一个节点
                //怎么理解：因为我们是中序遍历左中右的顺序，第一个一定是向左查找的尽头的节点，该节点没有左节点，所以一定会有leftType = 1,而且前继节点为pre = null】
                //因为他是第一个节点，前面没有Node为pre赋值，所以pre为默认值null
                node = node.getLeft();
            }
            //把当前节点输出
            System.out.println(node);
            while(node.getRightType() == 1){
                //沿着线索的后继节点一直走，直到后继线索断掉，说明下一个线索是前驱条件
                node = node.getRight();
                System.out.println(node);
            }
            //node已经输出过，按照中序的线索化方式，下一个节点肯定在右边，所以我们将node直接移到右边的下一个节点进行遍历
            node = node.getRight();
        }
     }

    public void delNode(int no) {
        if (root == null) {
            System.out.println("空树，无需删除");
        } else if (root.getNo() == no) {
            root = null;
            System.out.println("删除成功！");
        } else {
            boolean res = root.delNode(no);
            if (res) {
                System.out.println("删除成功！");
            } else {
                System.out.println("树中没有此节点，删除失败···");
            }
        }
    }

    public void delNodePro(int no) {
        //首先对根节点进行特殊处理
        if (root == null) {
            System.out.println("空树，无需删除");
        } else if (root.getNo() == no) {
            System.out.println("删除成功");
            if (root.getLeft() != null) {
                root = root.getLeft();
                return;
            }
            if (root.getRight() != null) {
                root = root.getRight();
                return;
            }
            root = null;
        } else {
            //根节点不是要删除节点的话，可以正常的进行递归删除
            boolean res = root.delNodePro(no);
            if (res) {
                System.out.println("删除成功！");
            } else {
                System.out.println("树中没有此节点，删除失败···");
            }
        }
    }

    public void threadedBinaryTree(){
        threadedBinaryTree(root);
    }
    /**
     * 将二叉树进行线索化，这里使用中序遍历的顺序进行线索化
     * 二叉树在线索化后一定是连贯的，即添加的线索可以把每一个节点相连，连成一个线性结构，线索上的前后两个节点之间都有一根线索（前驱节点线索和后继节点线索都可能）
     * 所以不算第线索中带一个节点的前驱节点，一共有（节点数量-1）根线索线
     * @param node 当前节点
     */
    public void threadedBinaryTree(HeroNode1 node){
        if(root == null){
            System.out.println("空树");
            return;
        }
        //先对左子树进行递归线索化
        if(node.getLeft()!=null){
            threadedBinaryTree(node.getLeft());
        }

        //对当前节点进行线索化遍历
        if(node.getLeft() == null){
            node.setLeftType(1);
            node.setLeft(pre);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRightType(1);
            pre.setRight(node);
        }
        pre = node;

        //对右子数进行递归线索化
        if(node.getRight()!=null){
            threadedBinaryTree(node.getRight());
        }
    }

}


class HeroNode1 {
    private int no;//英雄的编号
    private String name;//英雄的名号
    private HeroNode1 left;//左指针
    private HeroNode1 right;//右指针

    private  int  leftType;//左指针状态
    private int rightType;//右指针状态

    public void setLeftType(int b){
        this.leftType = b;
    }
    public int getLeftType(){
        return this.leftType;
    }
    public void setRightType(int b ){
        this.rightType = b;
    }
    public int getRightType(){
        return this.rightType;
    }

    //空参构造
    public HeroNode1() {
    }

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode1 getLeft() {
        return this.left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return this.right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null && this.leftType==0) {
            this.left.preOrder();
        }
        if (this.right != null && this.rightType==0) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找

    /**
     * 前序查找：如果找到节点到就返回节点，否则判断左节点是否为空，空的话返回非空左子树继续前序遍历；判断右节点是否为空，是否为空都要返回一个结果
     *
     * @param no 要查找的英雄的序号
     * @return 返回找到的节点，没有找到的话返回null
     */
    public HeroNode1 preOrderSearch(int no) {
        System.out.println("进行前序查找");
        if (this.no == no) {
            //找到本节点就是要查找的序号对应的节点，直接返回
            return this;
        }
        HeroNode1 resNode = null;
        //对左递归查找
        if (this.left != null) {
            //如果左节点非空，则对左节点继续递归调用前序查找
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //对右递归查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //必须返回，无论是否找到都必须给调用一个返回值结果
        return resNode;
    }

    /**
     * 中序遍历：先左递归查找，然后对该节点进行判断，最后对右递归查找
     *
     * @param no 要查找的英雄的序号
     * @return 返回找到的节点，没有找到的话返回null
     */
    public HeroNode1 infixOrderSearch(int no) {
        HeroNode1 resNode = null;
        //非空左递归
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进行中序查找");
        //对该节点进行判断
        if (this.no == no) {
            return this;
        }
        //非空右递归
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序查找：先左递归，再右递归，最后判断该节点
     *
     * @param no
     * @return
     */
    public HeroNode1 postOrderSearch(int no) {
        HeroNode1 resNode = null;
        //非空左递归
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //非空右递归
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进行后序查找");
        //对该节点进行判断
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 删除节点：将指定节点为根结点的子树直接删除
     *
     * @param no
     */
    public boolean delNode(int no) {
        if (this.left != null && this.left.no == no) {
            //如果左节点是要删除的节点，直接删除
            this.left = null;
            return true;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return true;
        }
        boolean res = false;
        if (this.left != null) {
            res = this.left.delNode(no);
            if (res) {
                return true;
            }
        }
        if (this.right != null) {
            res = this.right.delNode(no);
        }
        return res;
    }

    /**
     * 删除节点：将指定节点为根结点的子树直接删除
     *
     * @param no
     */
    public boolean delNodePro(int no) {
        if (this.left != null && this.left.no == no) {
            //如果左节点是要删除的节点
            if (this.left.left != null) {
                this.left = this.left.left;
                return true;
            }
            if (this.left.right != null) {
                this.left = this.left.right;
                return true;
            }
            this.left = null;
            return true;
        }
        if (this.right != null && this.right.no == no) {
            //如果右节点是要删除的节点的话
            if (this.right.left != null) {
                this.right = this.right.left;
                return true;
            }
            if (this.right.right != null) {
                this.right = this.right.right;
                return true;
            }
            this.right = null;
            return true;
        }
        boolean res = false;
        if (this.left != null) {
            res = this.left.delNodePro(no);
            if (res) {
                return res;
            }
        }
        if (this.right != null) {
            res = this.right.delNodePro(no);
        }
        return res;
    }
}