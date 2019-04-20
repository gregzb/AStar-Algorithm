import java.util.*;

public class AStar {
    public static List<INode> findPath(IGrid grid) {

        long startTime = System.nanoTime();

        List<INode> finalPath = new ArrayList<>();

        PriorityQueue<INode> openList = new PriorityQueue<>();
        Map<INode, Integer> visited = new HashMap<>();

        INode startNode = grid.getStartNode();
        INode endNode = grid.getEndNode();

        if (startNode == null || endNode == null) return null;

        openList.offer(startNode);

        boolean found = false;

        while (!openList.isEmpty()) {
            INode current = openList.peek();

            if (current.equals(endNode)) {
                found = true;
                break;
            }

            openList.poll();

            for (INode n : current.getNeighbors()) {
                if (!visited.containsKey(n) || n.getGCost() < visited.get(n)) {
                    visited.put(n, n.getGCost());
                    openList.offer(n);
                }
            }
        }

        if (found) {
            INode next = openList.peek();
            while ((next = next.getPrevNode()) != null) {
                finalPath.add(next);
            }
            finalPath.remove(finalPath.size()-1);
        }

        Collections.reverse(finalPath);

        long elapsedTime = System.nanoTime()-startTime;

        System.out.println("Calculated path in " + (elapsedTime * Math.pow(10, -9)) + " seconds.");

        return finalPath;
    }
}
