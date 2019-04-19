import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class Grid{
    private PApplet p;

    public final int[] colors;

    int[][] grid;

    PVector startPos;
    PVector endPos;

    private int openSlotCount;

    public Grid(PApplet p, int width, int height) {
        this.p = p;
        this.grid = new int[height][width];

        colors = new int[] {p.color(255), p.color(0), p.color(0, 80, 255), p.color(255, 80, 0), p.color(0, 255, 0)};

        openSlotCount = width * height;
    }

    public void render(int gridSize, List<Node> path) {

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                p.fill(colors[grid[row][col]]);
                p.rect(col*gridSize, row*gridSize, gridSize, gridSize);
            }
        }

        if (path != null) {
            for (Node n : path) {
                p.fill(colors[4]);
                p.rect(n.getPosition().x*gridSize, n.getPosition().y*gridSize, gridSize, gridSize);
            }
        }

        p.stroke(0);
        renderGridLines(gridSize);
    }

    public void renderGridLines(int gridSize) {
        for (int x = 0; x < getWidth()+1; x++) {
            p.line(x * gridSize, 0, x * gridSize, p.height);
        }
        for (int y = 0; y < getHeight()+1; y++) {
            p.line(0, y * gridSize, p.width, y * gridSize);
        }
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public void setStartPos(PVector startPos) {
        if (this.startPos != null) {
            set(this.startPos, 0);
            openSlotCount++;
        }
        this.startPos = startPos;
        if (set(this.startPos, 2) == 0) {
            openSlotCount--;
        }
    }

    public void setEndPos(PVector endPos) {
        if (this.endPos != null) {
            set(this.endPos, 0);
            openSlotCount++;
        }
        this.endPos = endPos;
        if (set(this.endPos, 3) == 0) {
            openSlotCount--;
        }
    }

    public void setPassable(PVector pos, boolean passable) {
        if (set(pos, passable ? 0 : 1) == 0) {
            openSlotCount--;
        }
        if (passable) {
            openSlotCount++;
        }
    }

    public int get(PVector pos) {
        return this.grid[(int)pos.y][(int)pos.x];
    }

    public int set(PVector pos, int val) {
        int prevVal = this.grid[(int)pos.y][(int)pos.x];
        this.grid[(int)pos.y][(int)pos.x] = val;
        return prevVal;
    }

    public Node getStartNode() {
        if (startPos == null) return null;

        return new Node(this, null, startPos.copy(), 0);
    }

    public Node getEndNode() {
        if (endPos == null) return null;

        return new Node(this, null, endPos.copy(), 0);
    }

    public static int manhattanDistance(PVector a, PVector b) {
        return (int) (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }

    public int getOpenSlotCount() {
        return openSlotCount;
    }
}
