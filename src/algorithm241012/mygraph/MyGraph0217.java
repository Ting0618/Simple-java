package algorithm241012.mygraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tingwong
 */
public class MyGraph0217 {
    /**
     * @Description TODO find all paths from root/ (or other node) to target, graph might have cycle
     * @Date 2025/2/18 11:27
     **/
    public void allPathDfs(Map<Integer, List<Integer>> graph, int startNode, int target, boolean[] onPath, List<Integer> path, List<List<Integer>> res){
        if(!graph.containsKey(startNode) || !graph.containsKey(target)){
            return;
        }
        if(onPath[startNode]){
            return;
        }
        onPath[startNode] = true;
        path.add(startNode);
        if(startNode == target){
            res.add(new ArrayList<>(path));
            path.removeLast();
            onPath[startNode] = false;
            return;
        }

        for(Integer item: graph.get(startNode)){
            if(!onPath[item]){
                allPathDfs(graph, item, target, onPath, path, res);
            }
        }
        onPath[startNode] = false;
        path.removeLast();
    }
}
