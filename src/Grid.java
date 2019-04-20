import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Grid implements IGrid{
    private PApplet p;

    public final int[] colors;

    int[][] grid;

    private List<Renderable> renderables;
    private Character mainChar;

    private PVector goalPos;

    public Grid(PApplet p, int width, int height, int gridSize) { //implement this
        this.p = p;
        this.grid = new int[height][width];

        colors = new int[] {p.color(255), p.color(0), p.color(0, 80, 255), p.color(255, 80, 0), p.color(0, 255, 0)};
        renderables = new ArrayList<>();
        mainChar = new Character(new PVector(p.width/2, p.height/2));
        renderables.add(mainChar);
    }

    public void drawSquare(float gridSize, float x, float y, int color) {
        p.fill(color);
        p.rect(x*gridSize, y*gridSize, gridSize, gridSize);
    }

    public void render(int gridSize, List<INode> path) {

        //Draw normal squares
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                drawSquare(gridSize, col, row, colors[grid[row][col]]);
            }
        }

        //Path rendering
        if (path != null) {
            for (INode iN : path) {
                Node n = (Node) iN;
                drawSquare(gridSize, n.getPosition().x, n.getPosition().y, colors[4]);
            }
        }

        //Gridlines
        p.stroke(0);
        renderGridLines(gridSize);
    }

    public void render(Renderable r) {
        drawSquare(gridSize, n.getPosition().x, n.getPosition().y, colors[4]);
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
//
//    public void setStartPos(PVector startPos) {
//        if (this.startPos != null) {
//            set(this.startPos, 0);
//        }
//        this.startPos = startPos;
//        set(this.startPos, 2);
//    }
//
//    public void setEndPos(PVector endPos) {
//        if (this.endPos != null) {
//            set(this.endPos, 0);
//        }
//        this.endPos = endPos;
//        set(this.endPos, 3);
//    }
//
//    public void setPassable(PVector pos, boolean passable) {
//        int oldVal = set(pos, passable ? 0 : 1);
//        if (oldVal == 2) {
//            startPos = null;
//        } else if (oldVal == 3) {
//            endPos = null;
//        }
//    }

    public int get(PVector pos) {
        return this.grid[(int)pos.y][(int)pos.x];
    }

    public int set(PVector pos, int val) {
        int prevVal = this.grid[(int)pos.y][(int)pos.x];
        this.grid[(int)pos.y][(int)pos.x] = val;
        return prevVal;
    }

    public Node getStartNode() {
        if (mainChar.getPosition() == null) return null;

        return new Node(this, null, mainChar.getPosition().copy(), 0);
    }

    public Node getEndNode() {
        if (goalPos == null) return null;

        return new Node(this, null, goalPos.copy(), 0);
    }

    public static int manhattanDistance(PVector a, PVector b) {
        return (int) (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }

    public static int distance(PVector a, PVector b) {
        return (int) (10 * Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2)));
    }
}
