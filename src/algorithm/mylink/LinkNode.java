package algorithm.mylink;

/**
 * create by: Ting
 * description: TODO
 * create time: 18/03/2024 13:28
 */
public class LinkNode {
    int val;
    LinkNode next;
    public LinkNode(){}
    public LinkNode(int val){
        this.val = val;
    }
    public LinkNode(int val, LinkNode next){
        this.val = val;
        this.next = next;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
