package Algorithm.mystring;

/**
 * create by: Ting
 * description: TODO
 * create time: 30/03/2024 17:56
 */
public class MyString {
    /**
     * create by: Ting
     * description: TODO Write a function that reverses a string. The input string is given as an array of characters s
     * https://leetcode.cn/problems/reverse-string/description/
     * create time: 30/03/2024 18:03
     */
    public String reverseString(char[] s) {
        int mid = s.length / 2;
        for (int i = 0; i < mid; i++) {
            int right = s.length - 1 - i;
            char temp = s[i];
            s[i] = s[right];
            s[right] = temp;
        }
        return String.valueOf(s);
    }

    /**
     * create by: Ting
     * description: TODO Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string
     * https://leetcode.cn/problems/reverse-string-ii/description/
     * create time: 30/03/2024 21:03
     */
    public String reverseStr(String s, int k) {
        char[] charStr = s.toCharArray();
        int sLength = charStr.length;
        for (int i = 0; i < sLength; ) {
            int interval = sLength - 1 - i;
            int right;
            if (interval < k) {
                // reverse all,from charStr[i] to [length-1]
                right = sLength - 1;
            } else {
                // reverse [i] to [i+k-1]
                right = i + k - 1;
            }
            for (int j = i; j < right; j++) {
                char temp = charStr[j];
                charStr[j] = charStr[right];
                charStr[right] = temp;
                right--;
            }
            i = i + 2 * k;
        }
        return String.valueOf(charStr);
    }

    /**
     * create by: Ting
     * description: TODO 给定一个字符串 s，它包含小写字母和数字字符，请编写一个函数，将字符串中的字母字符保持不变，而将每个数字字符替换为number
     * create time: 31/03/2024 13:11
     */
    public String replaceNumber(String str) {
        StringBuffer res = new StringBuffer();
        char[] charStr = str.toCharArray();
        for (int i = 0; i < charStr.length; i++) {
            int a = Integer.valueOf(charStr[i]);
            if (a < 97 || a > 123) {
                res.append("number");
            } else {
                res.append(charStr[i]);
            }
        }
        return res.toString();
    }

    /**
     * create by: Ting
     * description: TODO Given an input string s, reverse the order of the words.
     * create time: 31/03/2024 15:09
     */
    public String reverseWords(String s) {
        StringBuffer res = new StringBuffer();
        // removing excess " "
        char[] str = s.toCharArray();
        str = removeExtraSpace(str);
        return res.toString();
    }

    private char[] removeExtraSpace(char[] str) {
        int slow = 0;
        for (int fast = 0; fast < str.length; fast++) {
            // if str[fast] == ' ',fast++
            if (str[fast] != ' ') {
                str[slow] = str[fast];
                slow++;
            }
        }
        return str;
    }


    /**
     * create by: Ting
     * description: TODO 给定一个字符串 s 和一个正整数 k，请编写一个函数，将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作
     * create time: 31/03/2024 17:51
     */
    public String specialReverse(String str, int k) {
        char[] tempStr = str.toCharArray();
        reverseString(tempStr);
        int flag = k - 1;
        int flag2 = tempStr.length - 1;
        for (int i = 0; i < tempStr.length; i++) {
            while (i < flag) {
                char temp = tempStr[i];
                tempStr[i] = tempStr[flag];
                tempStr[flag] = temp;
                i++;
                flag--;
            }
            while (i > k - 1 && i < tempStr.length && i < flag2) {
                char temp = tempStr[i];
                tempStr[i] = tempStr[flag2];
                tempStr[flag2] = temp;
                i++;
                flag2--;
            }
        }
        return String.valueOf(tempStr);
    }
}
