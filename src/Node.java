import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>, INode{

    private Grid grid;
    private Node prevNode;
    private PVector position;

    private int gCost;

    public static int[] dirs = new int[] {1, 0, 0, 1, -1, 0, 0, -1, 1, 1, -1, -1, 1, -1, -1, 1};

    public Node(Grid grid, Node prevNode, PVector position, int gCost) {
        this.grid = grid;
        this.prevNode = prevNode;
        this.position = position;
        this.gCost = gCost;
    }

    public PVector getPosition() {
        return position;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public List<INode> getNeighbors() {
        List<INode> temp = new ArrayList<>();
        for (int i = 0; i < dirs.length; i+=2) {
            PVector newPos = position.copy().add(dirs[i], dirs[i+1]);
            if (newPos.x >= 0 && newPos.x < grid.getWidth() && newPos.y >= 0 && newPos.y < grid.getHeight()) {
                if (grid.get(newPos) != 1) {
                    int val = i < 2*4 ? 10 : 14;
                    temp.add(new Node(grid, this, newPos, gCost + val));
                }
            }
        }
        return temp;
    }

    public int getGCost() {
        return gCost;
    }

    public int getHCost() {
        return Grid.distance(this.getPosition(), grid.getEndNode().getPosition());
    }

    public int getFCost() {
        return getGCost() + getHCost();
    }

    public String toString() {
        return "Node: " + position + " f: " + getFCost();
    }

    @Override
    public int compareTo(Node o) {
        int fCost = getFCost();
        int otherFCost = o.getFCost();
        return Integer.compare(fCost, otherFCost);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Node)) return false;
        Node n = (Node) o;
        return position.equals(n.position);
    }

    public int hashCode() {
        return position.hashCode();
    }
}
