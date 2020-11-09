package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "卢俊义");
        HeroNode hero3 = new HeroNode(3, "吴用");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero5 = new HeroNode(5, "关胜");
        root.setLeft(hero2);
        root.setRight(hero3);
        hero3.setRight(hero4);
        hero3.setLeft(hero5);

        BinaryTree binaryTree = new BinaryTree(root);
//        //前序遍历
//        System.out.println("前序遍历");//12354
//        binaryTree.preOrder();
//        //中序遍历
//        System.out.println("中序遍历");//21534
//        binaryTree.infixOrder();
//        //后序遍历
//        System.out.println("后序遍历");//25431
//        binaryTree.postOrder();
//
//        int searchNo = 1;
//        HeroNode resNode = null;
//        System.out.println("前序查找测试");
//        resNode = binaryTree.getRoot().preOrderSearch(searchNo);
//        if (resNode != null) {
//            System.out.printf("查找序号为%d的节点成功，节点的信息为" + resNode + "\n", searchNo);
//        } else {
//            System.out.println("没有找到");
//        }
//
//        System.out.println("中序查找测试");
//        resNode = binaryTree.getRoot().infixOrderSearch(searchNo);
//        if (resNode != null) {
//            System.out.printf("查找序号为%d的节点成功，节点的信息为" + resNode + "\n", searchNo);
//        } else {
//            System.out.println("没有找到");
//        }
//
//        System.out.println("后序查找测试");
//        resNode = binaryTree.getRoot().postOrderSearch(searchNo);
//        if (resNode != null) {
//            System.out.printf("查找序号为%d的节点成功，节点的信息为" + resNode + "\n", searchNo);
//        } else {
//            System.out.println("没有找到");
//        }
        binaryTree.preOrder();
        binaryTree.delNodePro(5);
        binaryTree.preOrder();
    }
}

//构造一个二叉树的类
class BinaryTree {
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
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
    public HeroNode preOrderSearch(int no) {
        if (root == null) {
            System.out.println("该根节点的树为空！！");
            return null;
        } else {
            return root.preOrderSearch(no);
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root == null) {
            System.out.println("该根节点的树为空！！");
            return null;
        } else {
            return root.infixOrderSearch(no);
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root == null) {
            System.out.println("该根节点的树为空！！");
            return null;
        } else {
            return root.postOrderSearch(no);
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
}


class HeroNode {
    private int no;//英雄的编号
    private String name;//英雄的名号
    private HeroNode left;//左指针
    private HeroNode right;//右指针

    //空参构造
    public HeroNode() {
    }

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return this.left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return this.right;
    }

    public void setRight(HeroNode right) {
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
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
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
    public HeroNode preOrderSearch(int no) {
        System.out.println("进行前序查找");
        if (this.no == no) {
            //找到本节点就是要查找的序号对应的节点，直接返回
            return this;
        }
        HeroNode resNode = null;
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
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
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
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
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
