package algorithm241012.mylist;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyList {
    /**
     * description: TODO  14.Reverse Linked List
     * create time: Oct 20 2024 22:13
     */
    public LinkNode reverseList(LinkNode head) {
        // 1. 没想到用两个指针 2. 没想到pre要赋值为null
        LinkNode pre = null;
        LinkNode cur = head;
        LinkNode temp = null;
        // 3. 这里不应该用 cur.next != null
        while (cur != null){
            temp = cur.next;
            cur.next = pre;
            // 4. pre也要更新
            pre = cur;
            cur = temp;
        }
        // 5. 这里不能用 head = cur，因为这时候的 cur = null
        head = pre;
        return head;
    }


    /**
     * description: TODO 14.Swap Nodes in Pairs
     * create time: Oct 20 2024 22:17
     */
    public LinkNode swapPairs(LinkNode head) {
        // 1. 没想到要创建虚拟头节点，因为涉及到对第一个节点对操作
        LinkNode dummy = new LinkNode();
        dummy.next = head;
        LinkNode cur = dummy;
        // 2. 临时节点first second temp
        LinkNode first;
        LinkNode second;
        LinkNode temp;
        // 3. 操纵的节点始终是cur节点的后两个，不是cur和它的下一个
        while (cur.next != null && cur.next.next != null){
            first = cur.next;
            second = first.next;
            temp = second.next;
            // 交换节点，因为first 和 second是用的 = 赋值，在内存中还是使用的以前的地址，只是给了个名字而已，所以在后面的操作中，
            // 直接操作first和second就相当于在操作原数据
            cur.next = second;
            second.next = first;
            first.next = temp;
            // 4. 要让cur指向要交换的节点的前一个节点
            cur = temp;
        }
        // 5. 不能返回head，因为head.next还指向原来的第一个节点，此时原来的第一个节点已经交换位置到第二去了
        return dummy.next;
    }

    /**
     * description: TODO 16.remove the nth node from the end of the list and return its head. only go through once
     * create time: Oct 21 2024 22:28
     */
    public LinkNode removeNthFromEnd(LinkNode head, int n) {
        LinkNode dummy = new LinkNode();
        dummy.next = head;
        LinkNode pre = dummy;
        LinkNode temp;
        LinkNode cur = dummy;
        int i = 0;
        //
        while (cur != null){
            while (cur != null && i <= n){
                cur = cur.next;
                i++;
            }
            if(cur == null) continue;
            else{
                cur = cur.next;
            }
            pre = pre.next;
            cur = cur.next;
        }
        temp = pre.next.next;
        pre.next = temp;
        return dummy.next;
    }

    /**
     * description: TODO 17.Given two (singly) linked lists, determine if the two lists intersect.
     * create time: Oct 22 2024 20:56
     */
    public LinkNode getIntersectionNode(LinkNode headA, LinkNode headB) {
        LinkNode fast = null;
        LinkNode slow = null;
        // 找出A，B的长度
        int sizeA = 0, sizeB = 0;
        LinkNode temp = headA;
        while (temp != null){
            sizeA++;
            temp = temp.next;
        }
        temp = headB;
        while (temp != null){
            sizeB++;
            temp = temp.next;
        }
        // 找出长度差,为遍历做准备
        int step = 0;
        if(sizeA > sizeB){
            step = sizeA - sizeB;
            fast = headA;
            slow = headB;
        }else{
            step = sizeB - sizeA;
            fast = headB;
            slow = headA;
        }
        // 长的先走step步
        while (step > 0){
            fast = fast.next;
            step--;
        }
        // 同时遍历，判断是否有相等的节点
        while (slow != null && fast != null){
            if(slow == fast){
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return null;
    }

    /**
     * description: TODO 18.return the node where the cycle begins. If there is no cycle, return null.
     * create time: Oct 22 2024 21:44
     */
    public LinkNode detectCycle(LinkNode head) {
        // we define two pointers, one moves 1 step at a time, the other one moves 2 steps one time
        LinkNode fast = head;
        LinkNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            // if it has a cycle
            if(fast == slow){
                // define two pointers
                LinkNode index1 = head;
                LinkNode index2 = fast;
                while (true){
                    // when they meet each other, this is the entrance of the cycle
                    if(index1 == index2){
                        return index2;
                    }
                    // they move 1 step at a time
                    index1 = index1.next;
                    index2 = index2.next;
                }
            }
        }
        return null;
    }

    /**
     * description: TODO 41. (lc234) Palindrome linked list
     * create time: Nov 03 2024 15:29
     */

    public boolean isPalindrome(LinkNode head) {
        Deque<LinkNode> stack = new LinkedList<>();
        LinkNode fast = head;
        LinkNode slow = head;
        while(fast != null && fast.next != null){
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }
        // 如果链表长度是奇数，跳过中间节点
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null){
            if(slow.val != stack.pop().val){
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     * description: TODO 42. (lc141) Linked List Cycle
     * create time: Nov 04 2024 10:06
     */
    public boolean hasCycle(LinkNode head) {
        LinkNode slow = head;
        LinkNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * description: TODO 43. (lc 21) Merge Two Lists --- Recursion
     * create time: Nov 04 2024 10:25
     */
    public LinkNode mergeTwoLists(LinkNode list1, LinkNode list2) {
        if(list1 == null && list2 == null){
            return null;
        }
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        // maintain an increasing list
        // don't understand Recursion
        if(list1.val <= list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * description: TODO 44. (lc 2) Add Two Numbers
     * create time: Nov 04 2024 11:01
     */
    public LinkNode addTwoNumbers(LinkNode l1, LinkNode l2) {
        LinkNode res = new LinkNode();
        int flag = 0;
        while (l1 != null || l2 != null){
            int val = 0;
            // plus the value of l1
            if(l1 != null){
                val = val + l1.val;
                l1 = l1.next;
            }
            // plus the value of l2
            if(l2 != null){
                val = val + l2.val;
                l2 = l2.next;
            }
            // don't forget flag, the ultimate value should be val + flag
            val = val + flag;
            if(val >= 10){
                val = val % 10;
                flag = 1;
            }else {
                // change flag to 0 is very important
                flag = 0;
            }
            append(res, val);
        }
        // if there are many 99999, don't forget to add a new node(value is 1) at the end of result
        if(flag == 1){
            append(res, 1);
        }
        return res.next;
    }
    // 尾插法插入节点
    public void append(LinkNode head,int value) {
        LinkNode newNode = new LinkNode(value);
        if (head == null) {
            head = newNode;
        } else {
            LinkNode current = head;
            // 找到链表的最后一个节点
            while (current.next != null) {
                current = current.next;
            }
            // 将新节点连接到最后一个节点的后面
            current.next = newNode;
        }
    }

    /**
     * description: TODO 45. (lc25) Reverse Nodes in K-groups
     * create time: Nov 04 2024 12:22
     */
    public LinkNode reverseKGroup(LinkNode head, int k) {
        // check if the list's size is larger than k, if not, just return the original list
        int count = 0;
        LinkNode temp = head;
        while(temp != null){
            temp = temp.next;
            count++;
        }
        // the size of head less than k
        if(count < k){
            return head;
        }
        // reverse k nodes, from head to Kth nodes
        // 1. why pre = head and cur = head.next?
        LinkNode pre = head;
        LinkNode cur = head.next;
        for (int i = 0;i < k - 1;i++) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // recursion
        // 2. why head.next = ?
        head.next = reverseKGroup(cur, k);
        // why return pre?
        return pre;
    }

    /**
     * description: TODO 46. (lc138) Copy List With Random Pointer
     * create time: Nov 05 2024 10:20
     */
    public LinkNode copyRandomList(LinkNode head) {
        // Create a key-value mapping between the nodes of the original linked list and the corresponding nodes
        // in the new linked list, then traverse to set up each node’s next and random references in the new list.
        Map<LinkNode, LinkNode> map = new HashMap<>(16);
        LinkNode temp = head;
        while (temp != null){
            // create a new node, copy everything of the original node, except random pointer, record into map
            LinkNode node = new LinkNode(temp.val);
            map.put(temp, node);
            temp = temp.next;
        }
        // traverse the map and assign values to the next and random pointers
        for(Map.Entry<LinkNode, LinkNode> entry: map.entrySet()){
            // these are original nodes' value
            LinkNode tempKey = entry.getKey();
            LinkNode next = tempKey.next;
            LinkNode random = tempKey.random;
            // these are copy nodes' value
            LinkNode tempValue = entry.getValue();
            tempValue.next = map.get(next);
            tempValue.random = map.get(random);
        }
        return map.get(head);
    }

    /**
     * description: TODO 47. (lc148) Sort List in ascending order
     * create time: Nov 05 2024 10:57
     */
    public LinkNode sortList(LinkNode head) {
        // terminal condition: the size of head less than 2 (only one or none node)
        if(head == null || head.next == null){
            return head;
        }
        // divide list into two parts, fast slow pointers
        LinkNode fast = head.next;
        LinkNode slow = head;
        // when list is odd, slow points to the center node, when it's even, points to the left-middle node
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // cut the list into two parts
        LinkNode rightNode = slow.next;
        slow.next = null;
        // Recursively find the smallest part that can split the linked list into two halves
        LinkNode left = sortList(head);
        LinkNode right = sortList(rightNode);
        // after the first recursing, left and right is null or has one node, because of the return condition
        // sort and merge
        LinkNode tou = new LinkNode();
        tou = mergeTwoLists(left, right);
        return tou;
    }

    /**
     * description: TODO 128 lc83 delete all duplicates
     * create time: Dec 20 2024 09:04
     */
    public LinkNode deleteDuplicates(LinkNode head) {
        if(head == null){
            return head;
        }
        LinkNode slow = head;
        LinkNode fast = head.next;
        while(fast != null){
            if(slow.val != fast.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // what we need is from head to slow
        slow.next = null;
        return head;
    }



}
