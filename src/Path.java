import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Path implements Renderable {
    private List<PVector> positions;
    private int color;
    private Grid grid;

    public Path(Grid grid, List<INode> positions, int color) {
        iNodesToPositions(positions);
        this.color = color;
        this.grid = grid;
    }

    private void iNodesToPositions(List<INode> positions) {
        this.positions = new ArrayList<>();
        if (positions != null) {
            for (INode iNode : positions) {
                this.positions.add(((Node) iNode).getPosition());
            }
        }
    }

    public List<PVector> getPositions() {
        return positions;
    }

    public List<PVector> setPositions(List<INode> positions) {
        List<PVector> temp = this.positions;
        iNodesToPositions(positions);
        return temp;
    }

    @Override
    public void render() {
        for (PVector pos : positions) {
            if (pos != null) {
                grid.render(pos.copy(), color);
            }
        }
    }
}
