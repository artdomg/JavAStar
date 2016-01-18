import java.util.LinkedList;
import java.util.List;

/**
 * Created by adominguez on 1/17/16.
 */
public class ANode {
    private List<ANode> connectedNodes;
    private ANode parent;
    private int heur, cost, currentCost = 0, totalCost = 0;
    private Object value;

    public ANode(List<ANode> connectedNodes, int heur, int cost) {
        this.connectedNodes = connectedNodes;
        if(this.connectedNodes == null) {
            this.connectedNodes = new LinkedList<>();
        }
        this.heur = heur;
        this.cost = cost;
        this.totalCost = this.heur;
    }

    public ANode(int heur, int cost) {
        this.connectedNodes = new LinkedList<>();
        this.heur = heur;
        this.cost = cost;
        this.totalCost = this.heur;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void addConnectedNode(ANode node) {
        this.connectedNodes.add(node);
    }

    public void setConnectedNodes(List<ANode> connectedNodes) {
        this.connectedNodes = connectedNodes;
    }

    public void setCurrentCost(int currentCost) {
        this.currentCost = currentCost;
        this.totalCost = this.heur + this.currentCost;
    }

    public void setParent(ANode parent) {
        this.parent = parent;
    }

    public int getTotalCost() {
        return this.totalCost;
    }

    public int getCost() {
        return this.cost;
    }

    public List<ANode> getConnectedNodes() {
        return this.connectedNodes;
    }

    public int getCurrentCost() {
        return this.currentCost;
    }

    public ANode getParent() {
        return this.parent;
    }

    public Object getValue() {
        return this.value;
    }

    public String toString() {
        return "f = " + this.totalCost + "; g = " + this.currentCost + " h = " + this.heur + " ; cost = " + this.cost + "; value = " + this.value;
    }

}
