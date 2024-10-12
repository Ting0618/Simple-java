import algorithm.mylink.LinkNode;

public class Main {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("haha");
        LinkNode n = new LinkNode(79);
        LinkNode nn = n;

        nn.setVal(0);
        System.out.println(n);
        System.out.println(nn);


//        int i = 6;
//        function2(i);
//        function3(s);
//        System.out.println(i);
    }

    public static void function2(int i) {
        i = 100;
    }



    public static void function3(StringBuffer s) {
        s.append("fun");
    }
}