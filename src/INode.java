public interface INode {

    INode getPrevNode();

    INode[] getNeighbors();

    int getGCost();

    int getHCost();

    int getFCost();

}
