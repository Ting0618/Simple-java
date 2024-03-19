package Algorithm.mylink;

/**
 * create by: Ting
 * description: TODO
 * create time: 18/03/2024 13:28
 */
public class MyLink {
    private LinkNode head; //the first node
    private  int size;
    public MyLink(){
        head = new LinkNode();
    }

    /** Given the head of a linked list and an integer val, remove all the nodes of the linked list that has
     * Node.val == val, and return the new head.*/
    public LinkNode removeNodes(LinkNode head, int val){
        LinkNode dummy = new LinkNode(-1, head);
        LinkNode pre = dummy;
        LinkNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                size--;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * create by: Ting
     * description: TODO Get the value of the indexth node in the linked list. If the index is invalid, return -1.
     * create time: 18/03/2024 15:35
     */
    public int getIndexth(LinkNode head,int index){
        if(head == null){
            return -1;
        }
        int count = 0;
        int res = -1;
        while (count < index){
            count++;
            if(head != null){
                res = head.val;
                head = head.next;
            }else{
                return -1;
            }
        }
        return res;
    }
    
    
    /**
     * create by: Ting
     * description: TODO Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     * create time: 18/03/2024 16:49
     */
    public void addAtHead(int val){
        LinkNode temp = new LinkNode(val);
        temp.val = val;
        temp.next = head.next;
        head.next = temp;
        size++;
    }

    /**
     * create by: Ting
     * description: TODO  Append a node of value val as the last element of the linked list.
     * 我纠结的点是，给最后一个current点next赋值后这个链表就改变了？没有返回东西啊。。。
     * 解答：改变的是current的指向，cur改变了链表，
     * create time: 18/03/2024 20:10
     */
    public void addAtTail(int val){
        LinkNode node = new LinkNode(val);
        node.val = val;
        LinkNode current = head;
        while (current.next != null){
            current = current.next;
        }
        current.next = node;
        size++;
    }

    /**
     * create by: Ting
     * description: TODO  Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
     * create time: 18/03/2024 21:43
     */
    public void addAtIndex(int index, int val) {
        if(index <= 0){
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else if (index > size) {

        } else {
            int count = 0;
            LinkNode pre = head;
            LinkNode temp = pre.next;
            while (count < index && temp != null){
                pre = temp;
                temp = temp.next;
                count++;
            }
            LinkNode node = new LinkNode(val);
            node.next = temp;
            pre.next = node;
            size++;
        }

    }

    /**
     * create by: Ting
     * description: TODO  Delete the indexth node in the linked list, if the index is valid.
     * create time: 19/03/2024 17:21
     */
    public void deleteAtIndex(int index){
        if(index <= 0 || index > size){
            return;
        }
        LinkNode pre = head;
        LinkNode temp = pre.next;
        while (index > 0 && temp.next != null){
            pre = temp;
            temp = temp.next;
            index--;
        }
        pre.next = temp.next;
        size--;
    }

    public int getSize(){
        return this.size;
    }
    @Override
    public String toString() {
        return "MyLink{" +
                "linkNode=" + head +
                '}';
    }
}
