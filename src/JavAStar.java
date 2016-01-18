import java.util.List;
import java.util.LinkedList;

/**
 * Created by adominguez on 1/17/16.
 */
public class JavAStar {
    private List<ANode> openList, closedList;
    private ANode startingNode, targetNode;

    public JavAStar(ANode startingNode, ANode targetNode) {
        this.startingNode = startingNode;
        this.targetNode = targetNode;
        this.openList = new LinkedList<>();
        this.closedList = new LinkedList<>();

        this.openList.add(this.startingNode);
    }

    public List<ANode> findPath() {
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

            return this.findPath();
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
