package algorithm241012.mylist;

import java.util.Objects;

/**
 * create by: Ting
 * description: TODO
 * create time: 18/03/2024 13:28
 */
public class LinkNode {
    int val;
    LinkNode next;
    LinkNode random;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkNode linkNode = (LinkNode) o;
        return val == linkNode.val && Objects.equals(next, linkNode.next) && Objects.equals(random, linkNode.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next, random);
    }
}
