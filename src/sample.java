import java.util.List;
/**
 * Created by adominguez on 1/17/16.
 */
public class sample {
    public static void main(String args[]) {
        /*
        0 = non passable
        1 - n = cost
         */
        int[][] multi = new int[][]{
                { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 40, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 40, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };

        //Find and print the path
        long start = System.currentTimeMillis();
        JavAStar astar = new JavAStar(multi);
        List<ANode> path = astar.findPath(1, 1, 5, 3, true);
        long finish = System.currentTimeMillis();

        if(path != null) {
            for(ANode n : path) {
                System.out.println(n);
            }
        }
        else {
            System.out.println("There is no path to the target node");
        }


        System.out.println("Time (ms): " + (finish - start));
    }
}
