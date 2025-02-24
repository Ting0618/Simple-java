package algorithm241012.myTrie;

/**
 * @author tingwong
 */
public class TrieNode<V> {
    // ASCII 码个数
    private static final int R = 256;
    V val = null;
    // children 都是节点，根据节点所在的索引确定这个 child 树枝上代表的值
    TrieNode<V>[] children = new TrieNode[R];
}
