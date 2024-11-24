package algorithm241012.mystrings;

import javax.xml.stream.events.Characters;
import java.util.*;

/**
 * create time: Oct 13 2024 21:26
 *
 * @author ting
 */
public class MyString {

    /**
     * description: TODO 9. 其实就是每隔几个然后处理其中的几个
     * create time: Oct 13 2024 21:47
     */
    public String reverseStr(String s, int k) {
        char[] ss = s.toCharArray();
        int val = 2;
        for (int i = 0; i < ss.length; i += val * k) {
            if (i + k < ss.length) {
                // reverse
                reverseChar(ss, i, i + k - 1);
                continue;
            }
            // 剩下的，数量小于k个的，直接reverse
            reverseChar(ss, i, ss.length - 1);
        }
        return String.valueOf(ss);
    }

    public void reverseChar(char[] s, int start, int end) {
        char temp;
        while (start < end) {
            temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * description: TODO 10. Given an input string s, reverse the order of the words.
     * create time: Oct 14 2024 21:29
     */
    public String reverseWords(String s) {
        String noSpaceS = this.dropExtraSpace2(s);
        System.out.println("noSpaceS:" + noSpaceS);
        // 去掉多余空格
        char[] ch = noSpaceS.toCharArray();
        int end = noSpaceS.length() - 1;
        int flag = 0;
        char val = ' ';
        // 根据空格将单词反转
        for (int i = 0; i <= end; i++) {
            flag = i;
            while (i <= end && ch[i] != val) {
                i++;
            }
            this.reverseChar(ch, flag, i - 1);
        }
        // 将整个字符再反转就可以得到反转后的正序的单词的字符串
        this.reverseChar(ch, 0, end);
        return String.valueOf(ch);
    }

    /**
     * description: TODO 11. 去掉字符串的多余空格，主要用到StringBuffer，字符不是空并且sb的末尾不是空就把这个字符加到sb上
     * create time: Oct 15 2024 11:15
     */
    public String dropExtraSpace(String s) {
        // delete extra space
        int end = s.length() - 1;
        int start = 0;
        char target = ' ';
        char[] ch = s.toCharArray();
        // 去掉开头的空格
        while (ch[start] == target) {
            start++;
        }
        // 去掉后面的空格
        while (ch[end] == target) {
            end--;
        }
        StringBuilder sb = new StringBuilder();
        // 去掉中间的空格
        char c;
        while (start <= end) {
            c = ch[start];
            if (c != ' ' || s.charAt(s.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb.toString();
    }

    /**
     * description: TODO 12. 去掉字符串的多余空格，
     * create time: Oct 15 2024 11:17
     */
    public String dropExtraSpace2(String s) {
        char[] ch = s.toCharArray();
        int flag = 0;
        char val = ' ';
        // i每隔几个就对前面的字符做某些处理，隔几个用空格来确定
        for (int i = 0; i < ch.length; i++) {
            // 如果i不为空格就把i丢到数组前面去
            if (ch[i] != ' ') {
                // 单词结束的地方
                if (flag != 0) {
                    ch[flag] = ' ';
                    flag++;
                }
                // 单词开始的地方
                // 此时的i是指向遇到的第一个空格，说明这时候一个单词结束了
                while (i < ch.length && ch[i] != val) {
                    ch[flag] = ch[i];
                    i++;
                    flag++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flag; i++) {
            sb.append(ch[i]);
        }
        return sb.toString();
    }

    /**
     * description: TODO 13.把字符串尾部的若干个字符转移到字符串的前面
     * create time: Oct 15 2024 21:25
     */
    public String rightReverse(String s, int k) {
        char[] ch = s.toCharArray();
        int length = ch.length;
        // 把所有字符先reverse
        this.reverseChar(ch, 0, length - 1);
        // 把前k个反转，再把后面剩下的反转，就得到了需要的字符了
        this.reverseChar(ch, 0, k - 1);
        this.reverseChar(ch, k, length - 1);
        return String.valueOf(ch);
    }

    /**
     * description: TODO 26. group the anagrams together. You can return the answer in any order.
     * create time: Oct 27 2024 20:27
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> resMap = new HashMap<>(16);
        List<List<String>> res = new ArrayList<>();
        // get every value of strs, then sort these characters
        for (String s : strs) {
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String tempStr = Arrays.toString(temp);

            // if key doesn't exist
            if (!resMap.containsKey(tempStr)) {
                // let ordered temp as the key of map, the value of map is a list, let the original s as one of the value
                List<String> val = new ArrayList<>();
                val.add(s);
                resMap.put(tempStr, val);
            } else {
                // if key exists, get the val(is a list) and add new str to the list
                List<String> tempList = resMap.get(tempStr);
                tempList.add(s);
                resMap.put(tempStr, tempList);
            }

            // a simper way to write the code, don't need to write if and else,use getOrDefault()
//            List<String> tempList = resMap.getOrDefault(tempStr, new ArrayList<>());
//            tempList.add(s);
//            resMap.put(tempStr,tempList);

        }
        for (Map.Entry<String, List<String>> entry : resMap.entrySet()) {
            res.add((List) entry.getValue());
        }
        return res;
    }

    /**
     * description: TODO 29. find all anagrams in a string
     * create time: Oct 28 2024 20:40
     * note: sliding window
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        // if p is longer
        if(s.length() < p.length()){
            return res;
        }
        int winSize = p.length();
        int left = 0, right = 0;
        char[] chaS = s.toCharArray();
        char[] chaP = p.toCharArray();
        while (right < s.length()) {
            // maintain a fixed window,
            right = left + winSize - 1;
            // check characters that from chaS[left] to chaS[left] if it is p's anagrams
            char[] win = Arrays.copyOfRange(chaS, left, right + 1);
            boolean isAnagrams = isAnagrams(win, chaP);
            if(isAnagrams){
                res.add(left);
            }
            left++;
        }
        return res;
    }

    public boolean isAnagrams(char[] s, char[] p) {
        if (s.length != p.length) {
            return false;
        }
        // add s into a map
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // check if map contains all characters of p
        for (char c : p) {
            if (map.containsKey(c) && map.get(c) != 0) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * description: TODO 29. find all anagrams in a string
     * create time: Oct 28 2024 20:40
     * note: sliding window
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()){
            return res;
        }
        int slen = s.length(), pLen = p.length();
        int[] arrp = new int[26];
        int left = 0, right = left + pLen - 1;;
        // put p's characters in a array, record its letter
        for(char c: p.toCharArray()){
            arrp[c - 'a'] ++;
        }
        // maintain a window and check if these characters that in the window is anagrams of p
        while (right < slen){
            int[] arrs = new int[26];
            // record characters from left to right
            for(int i = left; i <= right; i++){
                arrs[s.charAt(i) - 'a'] ++;
            }
            // check if these two arrays are equal
            if(Arrays.equals(arrs, arrp)){
                res.add(left);
            }
            left++;
            right = left + pLen - 1;
        }
        return res;
    }

}
