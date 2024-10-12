package algorithm.mystring;

/**
 * create by: Ting
 * description: TODO
 * create time: 30/03/2024 17:56
 */
public class MyString {

    /**
     * create by: Ting
     * description: TODO Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
     * https://leetcode.cn/problems/repeated-substring-pattern/description/
     * create time: 2024/4/4 10:14
     */
    public boolean repeatedSubstringPattern(String s){
        int[] next = getNext(s.toCharArray());
        char[] c = s.toCharArray();
        for(int i: next){
            System.out.print(i + " ");
        }

        if(s.length() ==1) return false;
        if (s.length() < 3){
            return c[0] == c[1];
        }
        // 最小重复子串
        int max = s.length() -next[s.length() - 1];
        return s.length() % max == 0 && next[s.length() - 1] > 0;
    }

    public boolean repeatedSubstringPattern2(String s) {
        if (s.equals("")) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }
        for(int j:next){
            System.out.print(j+",");
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }

    /**
     * create by: Ting
     * description: TODO
     * create time: 2024/4/1 16:15
     */
    public int kmp(String str, String s2) {
        char[] s = str.toCharArray();
        char[] t = s2.toCharArray();
        char[] temp = s2.toCharArray();
        int[] next = getNext(temp);
        int i = 0, j = 0;
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else if (j > 0) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == s2.length() ? i - j : -1;
    }

    private int[] getNext(char[] s) {
        int len = s.length;
        int[] next = new int[len];
        if (len == 1) {
            next[0] = -1;
            return next;
        }
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = next[i - 1];
        while (i < len) {
            if (s[i-1] == s[cn]) {
                next[i] = cn + 1;
                cn++;
                i++;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
    }



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
