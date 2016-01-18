import java.util.List;
import java.util.LinkedList;
import java.awt.Point;

/**
 * Created by adominguez on 1/17/16.
 */
public class JavAStar {
    private List<ANode> openList, closedList;
    private ANode startingNode, targetNode;
    private ANode[][] nodeGrid;

    public JavAStar() {
        this.openList = new LinkedList<>();
        this.closedList = new LinkedList<>();
    }

    public JavAStar(int grid[][]) {
        this.openList = new LinkedList<>();
        this.closedList = new LinkedList<>();
        nodeGrid = new ANode[grid.length][grid[0].length];

        int i, j;
        for(i=0; i<grid.length; i++) {
            for(j=0; j<grid[i].length; j++) {
                if(grid[i][j] != 0) {
                    this.nodeGrid[i][j] = new ANode(0, grid[i][j]);
                    this.nodeGrid[i][j].setValue(new Point(j, i));
                }
            }
        }
    }

    public List<ANode> findPath(int sx, int sy, int tx, int ty, boolean allowDiagonal) {
        this.startingNode = this.nodeGrid[sy][sx];
        this.targetNode = this.nodeGrid[tx][ty];

        int i, j;
        for(i=0; i<this.nodeGrid.length; i++) {
            for(j=0; j<this.nodeGrid[i].length; j++) {
                int h = (int)(Math.pow(i - ty, 2) + Math.pow(j - tx, 2));

                if(this.nodeGrid[i][j] != null) {
                    this.nodeGrid[i][j].setHeur(h);

                    if(j-1 >= 0 && this.nodeGrid[i][j-1] != null)
                        this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i][j-1]);
                    if(j+1 < this.nodeGrid[i].length && this.nodeGrid[i][j+1] != null)
                        this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i][j+1]);
                    if(i-1 >= 0 && this.nodeGrid[i-1][j] != null)
                        this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i-1][j]);
                    if(i+1 < this.nodeGrid.length && this.nodeGrid[i+1][j] != null)
                        this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i+1][j]);

                    if(allowDiagonal) {
                        if(j-1 >= 0 && i-1 >= 0 && this.nodeGrid[i-1][j-1] != null)
                            this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i-1][j-1]);
                        if(j-1 >= 0 && i+1 < this.nodeGrid.length && this.nodeGrid[i+1][j-1] != null)
                            this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i+1][j-1]);
                        if(j+1 < this.nodeGrid[i].length && i-1 >=0 && this.nodeGrid[i-1][j+1] != null)
                            this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i-1][j+1]);
                        if(j+1 < this.nodeGrid[i].length && i+1 < this.nodeGrid.length && this.nodeGrid[i+1][j+1] != null)
                            this.nodeGrid[i][j].addConnectedNode(this.nodeGrid[i+1][j+1]);
                    }
                }
            }
        }

        this.openList.add((this.startingNode));

        return iterate();
    }

    public List<ANode> findPath(ANode startingNode, ANode targetNode) {
        this.startingNode = startingNode;
        this.targetNode = targetNode;

        this.openList.add(this.startingNode);

        return iterate();
    }

    private List<ANode> iterate() {
        ANode lower = getLowerCostNode();

        if(lower == targetNode) {
            return this.createPath();
        }
        else if(lower == null) {
            return null;
        }
        else {
            List<ANode> connectedNodes = lower.getConnectedNodes();
            for(ANode n : connectedNodes) {
                int inList = this.isInList(n);

                if(inList == 0) {
                    this.openList.add(n);
                    n.setParent(lower);
                    n.setCurrentCost(lower.getCurrentCost() + n.getCost());
                }
                if(inList == 1) {
                    int costFromHere = lower.getCurrentCost() + n.getCost();
                    if(costFromHere < n.getCurrentCost()) {
                        n.setParent(lower);
                        n.setCurrentCost(costFromHere);
                    }
                }
            }

            this.openList.remove(lower);
            this.closedList.add(lower);

            return this.iterate();
        }
    }

    private int isInList(ANode node) {
        if(openList.contains(node))
            return 1;
        if(closedList.contains(node))
            return 2;

        return 0;
    }

    private List<ANode> createPath() {
        LinkedList<ANode> path = new LinkedList<>();
        ANode currentNode = this.targetNode;

        while(currentNode != startingNode) {
            path.addFirst(currentNode);
            currentNode = currentNode.getParent();
        }

        return path;
    }

    public ANode getLowerCostNode() {
        ANode node = null;
        int lowerCost = -1;
        for(ANode n : this.openList) {
            if(lowerCost == -1) {
                lowerCost = n.getTotalCost();
                node = n;
            }
            else if(n.getTotalCost() < lowerCost) {
                lowerCost = n.getTotalCost();
                node = n;
            }
        }
        return node;
    }
}
