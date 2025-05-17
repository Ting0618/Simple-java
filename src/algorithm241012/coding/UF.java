package algorithm241012.coding;

/**
 * @author tingwong
 */
public class UF {
    int[] parent;
    int size = 0;
    // used to record connected components.
    private int count;
    /**
     * @Description TODO initialize
     * @Date 2025/2/24 11:15
     **/
    public UF(int size){
        this.size = size;
        count = size;
        parent = new int[size];
        // make each node point at themselves
        for(int i = 0; i < size; i++){
            parent[i] = i;
        }
    }

    /**
     * @Description TODO add u, v to this UF
     * @Date 2025/2/24 11:16
     **/
    public void join(int u, int v) {
        // check if they are in the same root
        int rootU = find(u);
        int rootV = find(v);
        if(rootU == rootV){
            return;
        }
        count--;
        // parent[rootV] = rootU is the same, We join based on the roots of u and v, rather than u and v directly.
        parent[rootU] = rootV;
    }

    /**
     * @Description TODO find its root
     * @Date 2025/2/24 11:18
     **/
    public int find(int u){
        // if its root is itself
        if(parent[u] == u){
            return u;
        }
        // compressed paths
        return parent[u] = find(parent[u]);
    }

    /**
     * @Description TODO check if two points are in the same set
     * @Date 2025/2/24 11:24
     **/
    public boolean isSameSet(int u, int v){
        u = find(u);
        v = find(v);
        return u == v;
    }

    /**
     * @Description TODO return the connected components
     * @Date 2025/2/24 11:53
     **/
    public int count() {
        return count;
    }

}
