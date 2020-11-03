package stack;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Stack;

//将中缀表达式转换成逆波兰表达式（后缀表达式）计算器的代码实现
//通过一个ArrayList集合配合堆栈来实现逆波兰计算器
public class PolandNotation {
    public static void main(String[] args) {
        //给出需求
/*        String suffixexpression = "32 4 + 5 * 6 -";
        //ArrayList用来存放我们需要计算的逆波兰表达式
        ArrayList<String> list = getListString(suffixexpression);
        System.out.println(list);
        int res = calculate(list);
        System.out.printf("逆波兰表达式%s = %d\n", suffixexpression, res);*/


        //首先给定中缀表达式
        String infixExpression = "15/3*4+(2-5*4)";
        ArrayList<String> list1 = toSuffixExpression(infixExpression);
        System.out.println(list1);
        int result = calculate(list1);
        System.out.printf("中缀表达式%s转换为后缀表达式并计算的结果是：%d\n", infixExpression, result);
    }

    //            将中缀表达式转换成后缀表达式,并存储在ArrayList中
    public static ArrayList<String> toSuffixExpression(String infixExpression) {
        if (infixExpression.isEmpty()) {
            System.out.println("表达式为空···");
            return null;
        }
        //建立两个栈 s1 s2，其中s1用来临时存放运算符和括号，s2用来存放逆波兰表达式
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();
        //扫描中缀表达式
        String str;
        for (int i = 0; i < infixExpression.length(); i++) {
            str = "" + infixExpression.charAt(i);
            if (str.matches("\\d")) {
                //数字
                while( (i < infixExpression.length() - 1)&&("" + infixExpression.charAt(i + 1)).matches("\\d") ) {
                    str = str + infixExpression.charAt(i + 1);//如果是多位数，拼接字符串
                    i++;
                }
                //将数字入栈s2
                s2.add(str);
                str = "";//将str 清空
            } else {
                //不是数字，是括号或者运算符
                //如果栈顶元素是左括号或者s1栈为空，或者str是左括号，直接入栈s1
                if (s1.isEmpty() || s1.peek().equals("(") || str.equals("(")) {
                    s1.push(str);
                    str = "";//将str 清空
                } else if (str.equals(")")) {
                    //如果str为右括号，将s1的运算符不断弹出加入到s2直到遇到左括号，并将这一对括号舍弃
                    do {
                        s2.add(s1.pop());
                    } while (!s1.peek().equals("("));
                    s1.pop();
                    str = "";//将str 清空
                }
                //如果是运算符，而且str比栈顶的运算符的优先级高，直接入栈s1
                else if (operation(str) >operation(s1.peek())) {
                    s1.push(str);
                    str = "";//将str 清空
                } else {
                    do {
                        //如果str是运算符，而且str比栈顶的运算符的优先级低，将栈顶运算符弹出并加入到s2中,
                        // 直到str优先级大于等于栈顶元素的优先级
                        s2.add(s1.pop());
                    } while ((!s1.isEmpty()) && (!s1.peek().equals("(")) && operation(str) <= operation(s1.peek()));
                    s1.push(str);
                    str = "";//将str 清空
                }
            }
        }
        //将s1栈中的运算符全部弹出并添加到s2中
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static int operation(String str) {
        int SUM = 1;
        int DEL = 1;
        int MUL = 2;
        int DIV = 2;
        if (str.equals("+")) {
            return SUM;
        } else if (str.equals("-")) {
            return DEL;
        } else if (str.equals("*")) {
            return MUL;
        } else if (str.equals("/")) {
            return DIV;
        } else {
            throw new RuntimeException();
        }
    }


    //将表达式按照" "一个空格为标记位，分割成多个字符串,并存入到ArrayList集合中
    public static ArrayList<String> getListString(String expression) {
        ArrayList<String> list = new ArrayList<>();
        String[] s = expression.split(" ");
        for (String item : s) {
            list.add(item);
        }
        return list;
    }

    public static int calculate(ArrayList<String> list) {
        Stack<String> stack = new Stack<>();
        //遍历list集合，配合stack来完成逆波兰计算
        for (String item : list) {
            if (item.matches("\\d+")) {//注意正斜杠和反斜杠不要写错，'\'反斜杠用来做转义符号，'/'正斜杠表示目录结构和除法
                //扫描到数字，直接入栈
                stack.push(item);
            } else {
                //扫描到字符，从栈中pop两个数进行运算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        System.out.println("输入的运算符有误···");
                        break;
                }
                //将计算结果入栈
                stack.push("" + res);
            }
        }
        //最终在stack中只有一个数字，就是我们的表达式计算结果
        return Integer.parseInt(stack.pop());
    }
}
