package algorithm241012.myTrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tingwong
 */
public class Test {
    private static class TrieNode{
        boolean isEnd = false;
        int val = 0;
        TrieNode[] children = new TrieNode[26];
    }
    private static TrieNode root;
    public Test(){
        root = new TrieNode();
    }
    public void insert(String key, int val) {
        // use p to operate this tree, but keep root original
        TrieNode p = root;
        if(p == null){
            return;
        }
        // insert characters one by one
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            int index = c - 'a';
            // if c is not existed, create a node for c, (as long as c is not null, which means it exists)
            if(p.children[index] == null){
                p.children[index] = new TrieNode();
            }
            // to the next node
            p = p.children[index];
        }
        // update its value
        p.val = val;
    }

    public int sum(String prefix) {
        TrieNode p = getNode(prefix);
        if(p == null){
            return 0;
        }
        int sum = 0;
//        if(p.val != 0){
//            sum = p.val;
//        }
        sum = searchHelper(p, sum);
        return sum;
    }
    public TrieNode getNode(String prefix){
        TrieNode p = root;
        if(p == null){
            return null;
        }
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            int index = c - 'a';
            if(p.children[index] == null){
                return null;
            }
            p = p.children[index];
        }
        return p;
    }
    public int searchHelper(TrieNode node, int sum){
        if(node == null){
            return 0;
        }
        sum += node.val;
        for(TrieNode child: node.children){
            if(child != null){
                sum = searchHelper(child, sum);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Test test = new Test();
        List<String> dictionary = new ArrayList<>();
        dictionary.add("cat");
        dictionary.add("bat");
        dictionary.add("rat");
        String sentence = "the cattle was rattled by the battery";
        test.insert("apple", 3);
        int res = test.sum("apple");
        System.out.println(res);
    }
}
