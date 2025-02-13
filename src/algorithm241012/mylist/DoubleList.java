package algorithm241012.mylist;

/**
 * @author tingwong
 */
public class DoubleList {
    public Node head;
    public Node tail;
    public int key;
    public int value;
    public int size;

    public DoubleList(){
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }
    /**
     * @Description TODO add a node at the tail
     * @Date 2025/1/31 14:49
     **/
    public void addLast(Node node){
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }
    /**
     * @Description TODO remove a node
     * @Date 2025/1/31 14:54
     **/
    public void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
    }
    /**
     * @Description TODO remove the first node
     * @Date 2025/1/31 14:58
     **/
    public Node removeFirst(){
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }
    /**
     * @Description TODO get length
     * @Date 2025/1/31 15:00
     **/
    public int length(){
        return size;
    }
}
