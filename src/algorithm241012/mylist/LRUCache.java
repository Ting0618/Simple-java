package algorithm241012.mylist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tingwong
 */
public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private DoubleList doubleList;

    /**
     * @Description TODO initialize
     * @Date 2025/1/31 15:03
     **/
    public LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.doubleList = new DoubleList();
    }
    /**
     * @Description TODO Return the value of the key if the key exists, otherwise return -1.
     * @Date 2025/1/31 15:04
     **/
    public int get(int key) {
        if(map.containsKey(key)){
            // 1. get key and then return value
            Node node = map.get(key);
            // 2. make key the latest(remove originally, add a new node at the tail)
            doubleList.remove(node);
            doubleList.addLast(node);
            return node.value;
        } else {
            return -1;
        }
    }
    /**
     * @Description TODO Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
     * @Description TODO If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * @Date 2025/1/31 15:07
     **/
    public void put(int key, int value) {
        if(map.containsKey(key)){
            // 1. get the original node of key
            Node node = map.get(key);
            // 2. update node(key, val) in the doubleList(remove originally then add to the tail)
            doubleList.remove(node);
            node.value = value;
            doubleList.addLast(node);
            map.put(key, node);
            return;
        }
        // key does not exist in the map, add a new node to the map and doubleList
        if(capacity == doubleList.length()){
            Node first = doubleList.removeFirst();
            map.remove(first.key);
        }
        // 1. add to the doubleList
        Node node = new Node(key, value);
        doubleList.addLast(node);
        // 2. add to the map
        map.put(key, node);
    }

}
