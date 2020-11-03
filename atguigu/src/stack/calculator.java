package stack;
//可以实现多位数的加减乘除运算  中缀表达式的计算
public class calculator {
    public static void main(String[] args) {
        //给出计算的表达式
        String expression = "900+2*6-5";
        //建立数栈和运算符栈
        ArrayStack2 operStack = new ArrayStack2(10);
        ArrayStack2 numStack = new ArrayStack2(10);
        int index = 0;//用于扫描
        int num1 = 0;//操作数1
        int num2 = 0;//操作数2
        int oper = 0;//用来存放操作符
        int res = 0;//用来存放计算结果
        char ch = ' ';//用来存放表达式扫描得到的字符
        //为了解决多位数的扫描转换和计算，需要一个辅助的字符串
        String nums = "";
        //1.扫描表达式进行入栈
        while (true) {
            if (index >= expression.length()) {
                //扫描完字符串表达式
                break;
            }
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是符号还是数字
            //如果是符号，判断运算符栈是否为空，为空直接将运算符入栈
            //不为空的话，比较与栈顶的运算符的优先级比较大小，小于等于栈顶元素优先级的话要先将数栈中的两个数字出栈，
            // 将运算符栈的栈顶运算符出栈进行两数计算，将结果入数栈，然后再把oper压入运算符栈
            //大于栈顶元素优先级的话，直接压入运算符栈
            //如果ch是数字的话，直接压入数栈
            if (operStack.isOper(ch)) {
                //ch是符号
                if (operStack.isempty()) {
//                    为空，为空直接将运算符入栈
                    operStack.push(ch);
                    index++;
                } else {
                    //不为空的话，比较与栈顶的运算符的优先级比较大小，小于等于栈顶元素优先级的话要先将数栈中的两个数字出栈，
                    //将运算符栈的栈顶运算符oper出栈进行两数计算，将结果res入数栈，然后再把ch压入运算符栈
                    if (operStack.priority(ch) <= operStack.peek()) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.calculate(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                        index++;
                    } else {
                        //大于栈顶元素优先级的话，直接压入运算符栈
                        operStack.push(ch);
                        index++;
                    }
                }
            } else {
                //ch是数字
                if(index!=(expression.length()-1)){
                    while (index != (expression.length() - 1) && !operStack.isOper(ch)) {
                        nums += ch;//现将ch拼接到字符串中，在判断下一位是不是数字
                        ch = expression.substring(index + 1, index + 2).charAt(0);
                        index++;
                    }
                    numStack.push(Integer.parseInt(nums));
                    nums="";
                } else {
                    nums += ch;
                    numStack.push(Integer.parseInt(nums));
                    index++;
                    nums="";
                }
            }
        }
        //2.出栈计算
        while (true) {
            if (operStack.isempty()) {
                //已经完成全部计算
                break;
            }
            //进行出栈计算
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.calculate(num2, num1, oper);
            numStack.push(res);
        }
        res = numStack.pop();
        System.out.printf("表达式%s=%d\n", expression, res);
    }
}

//用数组实现栈的结构，需要扩展一部分的功能（给定运算符优先级，判断是运算符，判断是数字，进行两数之间计算）
class ArrayStack2 {
    int top;            //指针
    int[] stack;        //用数组模拟栈
    int maxsize;        //数组最大容量

    public ArrayStack2(int maxsize) {
        top = -1;
        this.maxsize = maxsize;
        stack = new int[maxsize];
    }

    public boolean isfull() {
        return top == maxsize - 1;
    }

    public boolean isempty() {
        return top == -1;
    }

    //元素进栈
    public void push(int n) {
        if (isfull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = n;
    }

    //元素出栈
    public int pop() {
        if (isempty()) {
            System.out.println("栈空");
            throw new RuntimeException();
        }
        int temp = stack[top];
        top--;
        return temp;
    }

    //展示栈中元素
    public void list() {
        if (isempty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }

    }

    //给定运算符的优先级
    public int priority(int oper) {
        if (oper == '+' || oper == '-') {
            return 0;
        } else if (oper == '*' || oper == '/') {
            return 1;
        } else {
            return -1;
        }
    }

    //判断是否为运算符
    public boolean isOper(int oper) {
        return (oper == '+' || oper == '-' || oper == '*' || oper == '/');
    }

    //返回运算符栈的栈顶元素的优先级
    public int peek() {
        return priority(stack[top]);
    }

    //两数计算
    public int calculate(int num1, int num2, int oper) {
        int res = -1;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
        }
        return res;
    }


}