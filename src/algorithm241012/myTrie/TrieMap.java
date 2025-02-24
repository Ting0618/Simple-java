package algorithm241012.myTrie;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tingwong
 */
public class TrieMap<V> {
    // 当前存在 Map 中的键值对个数
    private int size = 0;
    // Trie 树的根节点
    private TrieNode<V> root = null;

    public int size() {
        return size;
    }

    /**
     * @Description TODO 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
     * @Date 2025/2/20 09:16
     **/
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        // 从节点 node 开始搜索 key
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            // 向下搜索
            char c = key.charAt(i);
            // c 作为数组索引，等同于 p.children[(int) c]
            p = p.children[c];
        }
        return p;
    }

    /**
     * @Description TODO 搜索 key 对应的值，不存在则返回 null
     * @Date 2025/2/20 09:28
     **/
    public V get(String key) {
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null) {
            return null;
        }
        return x.val;
    }

    /**
     * @Description TODO 判断 key 是否存在在 Map 中
     * @Date 2025/2/20 09:36
     **/
    public boolean containsKey(String key) {
        TrieNode<V> temp = getNode(root, key);
        // temp.val == null means string key is a prefix, not a complete word
        if (temp == null || temp.val == null) {
            return false;
        }
        return true;
    }

    /**
     * @Description TODO 判断是和否存在前缀为 prefix 的键
     * @Date 2025/2/20 09:43
     **/
    public boolean hasKeyWithPrefix(String prefix) {
        TrieNode<V> temp = getNode(root, prefix);
        return temp != null;
    }

    /**
     * @Description TODO 在所有键中寻找 query 的最短前缀
     * @Date 2025/2/20 09:44
     **/
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                return "";
            }
            // when the first time encounter a node that value is not null, means 0-i is a whole word, is a prefix
            if (p.val != null) {
                return query.substring(0, i);
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            // 如果 query 本身就是一个键
            return query;
        }
        return "";
    }

    /**
     * @Description TODO
     * @Date 2025/2/20 10:18
     **/
    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        // 记录前缀的最大长度
        int maxLen = 0;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                break;
            }
            if (p.val != null) {
                // 找到一个键是 query 的前缀，更新前缀的最大长度
                maxLen = i;
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            // 如果 query 本身就是一个键
            return query;
        }
        return query.substring(0, maxLen);
    }

    /**
     * @Description TODO 搜索前缀为 prefix 的所有键
     * @Date 2025/2/20 10:35
     **/
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        // 找到匹配 prefix 在 Trie 树中的那个节点
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) {
            return res;
        }
        // DFS 遍历以 x 为根的这棵 Trie 树
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    /**
     * @Description TODO 遍历以 node 节点为根的 Trie 树，找到所有键
     * @Date 2025/2/20 10:36
     **/
    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {
            return;
        }
        // we are supposed to find the key
        if (node.val != null) {
            res.add(path.toString());
        }
        // 回溯算法遍历框架
        for (char c = 0; c < 256; c++) {
            if (node.children[c] != null) {
                path.append(c);
                traverse(node.children[c], path, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
        // 如果下标用数字的话有点麻烦，因为要将数字转成对应的字符
//        for(int i = 97; i < 97 + 26; i++){
//            char c = 97对应的a；
//            。。。
//        }

    }

    /**
     * @Description TODO 通配符 . 匹配任意字符
     * @Date 2025/2/20 10:56
     **/
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        keysWithPatternTraverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    /**
     * @Description TODO 遍历函数，尝试在「以 node 为根的 Trie 树中」匹配 pattern[i..]
     * @Date 2025/2/20 10:58
     **/
    private void keysWithPatternTraverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        if (node == null) {
            // 树枝不存在，即字符 pattern[i-1] 匹配失败
            return;
        }
        if (i == pattern.length()) {
            // pattern 匹配完成
            if (node.val != null) {
                // 如果这个节点存储着 val，则找到一个匹配的键
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // pattern[i] 是通配符，可以变化成任意字符
            // 多叉树（回溯算法）遍历框架
            for (char j = 0; j < 256; j++) {
                path.append(j);
                keysWithPatternTraverse(node.children[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            // pattern[i] 是普通字符 c
            path.append(c);
            keysWithPatternTraverse(node.children[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * @Description TODO
     * @Date 2025/2/20 11:09
     **/
    // 在 map 中添加或修改键值对
    public void put(String key, V val) {
        if (!containsKey(key)) {
            // 新增键值对
            size++;
        }
        // 需要一个额外的辅助函数，并接收其返回值
        root = put(root, key, val, 0);
    }

    /**
     * @Description TODO 定义：向以 node 为根的 Trie 树中插入 key[i..]，返回插入完成后的根节点
     * @Date 2025/2/20 11:10
     **/
    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            // 如果树枝不存在，新建
            node = new TrieNode<>();
        }
        if (i == key.length()) {
            // key 的路径已插入完成，将值 val 存入节点
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        // 递归插入子节点，并接收返回值
        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;
    }





}
