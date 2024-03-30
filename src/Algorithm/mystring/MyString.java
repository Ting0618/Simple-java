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
        String res = "";
        char[] charStr = s.toCharArray();
        int sLength = charStr.length;
        for (int i = 0; i < sLength; ) {
            int interval = sLength -1 - i;
            if(interval < k){
                // reverse all,from charStr[i] to [length]
                int mid = (sLength+i) / 2;
                for (int j=i; j < mid; j++) {
                    int right = sLength - 1 - i;
                    char temp = charStr[j];
                    charStr[j] = charStr[right];
                    charStr[right] = temp;
                }
            }
            else {
                // reverse [i] to [i+k]
                int mid = (i+i+k-1) / 2;
                for (int j=i; j < mid; j++) {
                    int right = i+k-1;
                    char temp = charStr[j];
                    charStr[j] = charStr[right];
                    charStr[right] = temp;
                }
            }
            i = i + 2 * k;
        }

        return String.valueOf(charStr);
    }
}
