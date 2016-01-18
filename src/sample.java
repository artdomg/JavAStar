import java.util.List;
/**
 * Created by adominguez on 1/17/16.
 */
public class sample {
    public static void main(String args[]) {
        /*
        0 = passable
        2 = non passable
        8 = starting point
        9 = target point
         */
        int[][] multi = new int[][]{
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 2, 2, 2, 2, 2, 2, 2, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
                { 0, 0, 0, 0, 9, 0, 2, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
                { 0, 0, 0, 2, 2, 2, 2, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };



        ANode[][] nodes = new ANode[multi.length][multi[0].length];

        //Map integers into Nodes
        int i, j;
        ANode startNode = null, targetNode = null;
        for(i=0; i<multi.length; i++) {
            for(j=0; j<multi[i].length; j++) {
                if(multi[i][j] == 0 || multi[i][j] == 8 || multi[i][j] == 9) {
                    int h = (int)(Math.pow(i - 2, 2) + Math.pow(j - 2, 2));
                    nodes[i][j] = new ANode(null, h, 1);
                    nodes[i][j].setValue("[" + j + ", " + i + "]");
                }

                if(multi[i][j] == 8)
                    startNode = nodes[i][j];
                if(multi[i][j] == 9)
                    targetNode = nodes[i][j];
            }
        }

        //Create adjacent node connections
        for(i=0; i<nodes.length; i++) {
            for(j=0; j<nodes[i].length; j++) {
                if(nodes[i][j] != null) {
                    if(j-1 >= 0 && nodes[i][j-1] != null)
                        nodes[i][j].addConnectedNode(nodes[i][j-1]);
                    if(j+1 < nodes[i].length && nodes[i][j+1] != null)
                        nodes[i][j].addConnectedNode(nodes[i][j+1]);
                    if(i-1 >= 0 && nodes[i-1][j] != null)
                        nodes[i][j].addConnectedNode(nodes[i-1][j]);
                    if(i+1 < nodes.length && nodes[i+1][j] != null)
                        nodes[i][j].addConnectedNode(nodes[i+1][j]);
                }
            }
        }

        //Find and print the path
        long start = System.currentTimeMillis();
        JavAStar astar = new JavAStar(startNode, targetNode);
        List<ANode> path = astar.findPath();
        long finish = System.currentTimeMillis();

        for(ANode n : path) {
            System.out.println(n);
        }

        System.out.println("Time (ms): " + (finish - start));
    }
}
