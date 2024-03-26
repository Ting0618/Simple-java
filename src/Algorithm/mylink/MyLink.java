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

    public MyLink(LinkNode head, int size) {
        this.head = head;
        this.size = size;
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
    
    /**
     * create by: Ting
     * description: TODO reverse the list
     * create time: 19/03/2024 19:57
     */
    public void reverse(){
        LinkNode pre = null;
        LinkNode cur = head;
        LinkNode temp;
        while (cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head = pre;
    }


    /**
     * create by: Ting
     * description: TODO Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     * create time: 19/03/2024 20:56
     */
    public void swapPairs(){


    }


    /**
     * create by: Ting
     * description: TODO remove the nth node from the end of the list and return its head.
     * create time: 25/03/2024 14:30
     */
    public void removeNthFromEnd(int n){
        LinkNode fast = head;
        LinkNode slow = head;
        if (n <= 0 ){
            return;
        }
        //find fast
        for (int i = 0; i < n; i++) {
            if(fast.next != null){
                fast = fast.next;
            } else {
                return;
            }
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        // detele
        LinkNode temp = slow.next;
        slow.next = temp.next;
    }


    /**
     * create by: Ting
     * description: TODO Given two (singly) linked lists, determine if the two lists intersect. Return the inter secting node
     * create time: 25/03/2024 16:55
     */
    public LinkNode getIntersectionNode(MyLink linkA, MyLink linkB){
        LinkNode indexA = linkA.head;
        LinkNode indexB = linkB.head;
        int sizeA = linkA.getSize();
        int sizeB = linkB.getSize();
        int n = sizeB - sizeA;;
        if(n >= 0){
            // to find the location of indexB
            for (int i = 0; i < n; i++) {
                if(indexB.next != null){
                    indexB = indexB.next;
                }else
                    return null;
            }
        } else {
            // to find the location of indexA
            for (int i = 0; i < -n; i++) {
                if(indexA.next != null){
                    indexA = indexA.next;
                }else
                    return null;
            }
        }
        // indexA and indexB move together
        while (indexA != null || indexB != null){
            if (indexA == indexB){  //when they are equal
                return indexA;
            }else {
                if(indexA != null)
                    indexA = indexA.next;
                if(indexB != null)
                    indexB = indexB.next;
            }
        }
        return null;
    }

    /**
     * create by: Ting
     * description: TODO 判断一个链表有没有环
     * create time: 25/03/2024 21:18
     */
    public boolean isCycle(){
        LinkNode slow = head;
        LinkNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return this.size;
    }

    public void setHead(LinkNode head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "MyLink{" +
                "linkNode=" + head +
                '}';
    }
}
