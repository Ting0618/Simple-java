package algorithm241012.mygraph;

public class Test {
    public static void main(String[] args) {
        MyGraph0217 myGraph = new MyGraph0217();
        int[][] nums = {{1,0},{0,1}};
        boolean finish = myGraph.canFinish(2, nums);
        System.out.println(finish);
    }
}
