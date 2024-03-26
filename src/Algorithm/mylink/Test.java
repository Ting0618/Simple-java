package Algorithm.mylink;

/**
 * create by: Ting
 * description: TODO
 * create time: 18/03/2024 13:41
 */
public class Test {
    public static void main(String[] args){
        LinkNode n6 = new LinkNode(6,null);
        LinkNode n5 = new LinkNode(5,n6);
        LinkNode n4 = new LinkNode(4,n5);
        LinkNode n3 = new LinkNode(3,n4);
        LinkNode n2 = new LinkNode(2,n3);
        LinkNode n1 = new LinkNode(1,n2);
        LinkNode headA = new LinkNode(0,n1);

        LinkNode m2 = new LinkNode(22,null);
        LinkNode m1 = new LinkNode(11,m2);
        LinkNode headB = new LinkNode(0,m1);

        MyLink myLink = new MyLink(headA,6);
//        myLink.setHead(headA);

        MyLink myLinkB = new MyLink(headB,5);
//        myLinkB.setHead(headB);
//        myLink.addAtIndex(0,4);
//        myLink.addAtIndex(1,2);
//        myLink.addAtIndex(2,3);


        System.out.println(myLink);
//        myLink.removeNthFromEnd(1);
//        myLink.swapPairs();
//        LinkNode res = myLink.removeNodes(head,3);
//        int res2 = myLink.getIndexth(head,1);
//        myLink.addAtHead(9);
//        myLink.addAtHead(8);


        System.out.println(myLinkB);
        LinkNode temp = myLink.getIntersectionNode(myLink,myLinkB);
        System.out.println(temp);
    }
}
