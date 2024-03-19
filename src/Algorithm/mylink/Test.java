package Algorithm.mylink;

/**
 * create by: Ting
 * description: TODO
 * create time: 18/03/2024 13:41
 */
public class Test {
    public static void main(String[] args){
//        LinkNode n6 = new LinkNode(0,null);
//        LinkNode n5 = new LinkNode(1,n6);
//        LinkNode n4 = new LinkNode(2,n5);
//        LinkNode n3 = new LinkNode(3,n4);
//        LinkNode n2 = new LinkNode(4,n3);
//        LinkNode n1 = new LinkNode(5,n2);
//        LinkNode head = new LinkNode(6,n1);
        MyLink myLink = new MyLink();
        myLink.addAtIndex(0,5);
        myLink.addAtIndex(1,9);
        myLink.addAtIndex(1,8);
        int size = myLink.getSize();
        System.out.println(size);
        System.out.println(myLink);
        myLink.deleteAtIndex(1);
        System.out.println(myLink.getSize());
//        LinkNode res = myLink.removeNodes(head,3);
//        int res2 = myLink.getIndexth(head,1);
//        myLink.addAtHead(9);
//        myLink.addAtHead(8);
//        myLink.addAtTail(0);
//        myLink.addAtTail(5);
//        myLink.addAtTail(9);

        System.out.println(myLink);
    }
}
