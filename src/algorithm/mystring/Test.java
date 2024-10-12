package algorithm.mystring;

/**
 * create by: Ting
 * description: TODO
 * create time: 30/03/2024 18:09
 */
public class Test {
    public static void main(String[] args) {
        String s = "hello";
        MyString myString = new MyString();
        String res = myString.reverseStr("abcd",4);
        res = myString.replaceNumber("a1b2c3");
        res = myString.reverseWords("  hello   world  ");
        res = myString.specialReverse("abcdefg",2);
//        int kmp = myString.kmp("abcabdanf","abab");
        boolean t = myString.repeatedSubstringPattern2("abab");
        System.out.println(t);
    }
}
