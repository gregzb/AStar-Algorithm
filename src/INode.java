import java.util.List;

public interface INode {

    INode getPrevNode();

    List<INode> getNeighbors();

    int getGCost();

    int getHCost();

    int getFCost();

}
