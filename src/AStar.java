import java.util.*;

public class AStar {
    public static List<Node> findPath(Grid grid) {
        List<Node> finalPath = new ArrayList<>();

        PriorityQueue<Node> openList = new PriorityQueue<>();
        List<Node> closedList = new ArrayList<>();

        Node startNode = grid.getStartNode();
        Node endNode = grid.getEndNode();

        if (startNode == null || endNode == null) return null;

        openList.offer(startNode);

        boolean found = false;

        while (!found) {
            //System.out.println("yee");
            Node current = openList.poll();
            for (Node n : current.getNeighbors()) {
                //System.out.println(n.getPosition() + ", " + endNode.getPosition());
                if (n.getPosition().equals(endNode.getPosition())) {
                    closedList.add(n);
                    found = true;
                    break;
                }
                if (closedList.contains(n)) {
                    int idx = closedList.indexOf(n);
                    if (closedList.get(idx).getFCost() > n.getFCost()) {
                        closedList.remove(idx);
                        openList.offer(n);
                    }
                } else {
                    openList.offer(n);
                }
            }
            closedList.add(current);
        }

        //System.out.println("doot");
        //System.out.println(closedList.size());

        if (found) {
//            for (int i = closedList.size()-1; i >= 0; i--) {
//                System.out.println(i);
//                finalPath.add(closedList.get(i));
//            }
            Node next = closedList.get(closedList.size()-1);
            finalPath.add(next);
            while ((next = next.getPrevNode()) != null) {
                //System.out.println("weee");
                finalPath.add(next);
            }
            finalPath.remove(finalPath.size()-1);
        }

        Collections.reverse(finalPath);

        //System.out.println(found);
        //System.out.println("done");

        return finalPath;
    }
}
