package tree;

public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder(0);
        System.out.println("中序遍历");
        arrBinaryTree.infixOrder(0);
        System.out.println("后序遍历");
        arrBinaryTree.postOrder(0);
    }

    int[] arr;
    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    /**
     * 前序遍历的重载形式！！
     * 可以省去每次写零的麻烦，而且可以在递归中正常改变传入的形参
     */
    public void preOrder(){
        preOrder(0);
    }
    public void preOrder(int n){
        if(arr == null||arr.length<=0){
            System.out.println("空树，无法遍历");
            return;
        }
        System.out.println(arr[n]);
        if((2*n+1)<arr.length){
            preOrder(2*n+1);
        }
        if((2*n+2)<arr.length){
            preOrder(2*n+2);
        }
    }

    public void infixOrder(){
        infixOrder(0);
    }
    public void infixOrder(int n){
        if(arr == null||arr.length<=0){
            System.out.println("空树，无法遍历");
            return;
        }
        if(2*n+1<arr.length){
            infixOrder(2*n+1);
        }
        System.out.println(arr[n]);
        if(2*n+2 < arr.length){
            infixOrder(2*n+2);
        }
    }

    public void postOrder(){
        postOrder(0);
    }
    public void postOrder(int n){
        if(arr == null ||arr.length <= 0){
            System.out.println("数空，无需遍历");
            return;
        }
        if(2*n+1 < arr.length){
            postOrder(2*n+1);
        }
        if(2*n+2<arr.length){
            postOrder(2*n+2);
        }
        System.out.println(arr[n]);
    }




}

