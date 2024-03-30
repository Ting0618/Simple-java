package Algorithm.mystring;

/**
 * create by: Ting
 * description: TODO
 * create time: 30/03/2024 18:09
 */
public class Test {
    public static void main(String[] args) {
        String s = "hello";
        MyString myString = new MyString();
        String res = myString.reverseStr("abcdefg",3);
        System.out.println(res);
    }
}
