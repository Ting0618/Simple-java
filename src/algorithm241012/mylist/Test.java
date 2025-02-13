package algorithm241012.mylist;

import algorithm241012.mylist.LinkNode;
import algorithm241012.mylist.MyList;

public class Test {

    public static void main(String[] args) {
        LinkNode n6 = new LinkNode(9,null);
//        LinkNode n5 = new LinkNode(5,n6);
//        LinkNode n4 = new LinkNode(4,n5);
        LinkNode n3 = new LinkNode(9,n6);
        LinkNode n2 = new LinkNode(9,n3);
        LinkNode n1 = new LinkNode(9,n2);
        LinkNode headA = new LinkNode(9,n1);

        LinkNode m3 = new LinkNode(3,null);
        LinkNode m2 = new LinkNode(2,m3);
        LinkNode m1 = new LinkNode(1,m2);
        LinkNode headB = new LinkNode(4,m1);

        MyList myList = new MyList();
        LinkNode res = myList.sortList(headB);
        System.out.println(res);
//        myList.swapPairs(headA);
//        System.out.println(headA);
//        LinkNode cur = new LinkNode();
//        LinkNode temp; // 临时节点，保存两个节点后面的节点
//        LinkNode firstnode; // 临时节点，保存两个节点之中的第一个节点
//        LinkNode secondnode;
//        cur.next = n1;
//        temp = cur.next.next.next;
//        firstnode = cur.next;
//        secondnode = cur.next.next;
//        cur.next = secondnode;       // 步骤一
//        secondnode.next = firstnode; // 步骤二
//        firstnode.next = temp;      // 步骤三
//        cur = firstnode;
        int[] nums = {1,2,3,4};
    }

}
