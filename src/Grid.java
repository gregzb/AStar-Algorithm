import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Grid implements IGraph {
    private PApplet p;

    public final int[] colors;

    int[][] grid;
    float gridSize;

    private List<Renderable> renderables;

    private PVector startPos;
    private PVector endPos;

    public Grid(PApplet p, int width, int height, int gridSize) { //implement this
        this.p = p;
        this.grid = new int[height][width];
        this.gridSize = gridSize;

        colors = new int[] {p.color(255), p.color(0), p.color(0, 80, 255), p.color(255, 80, 0), p.color(0, 255, 0)};
        renderables = new ArrayList<>();
    }

    public void addRenderable(Renderable r) {
        renderables.add(r);
    }

    public List<Renderable> getRenderables() {
        return renderables;
    }

    public void drawSquare(float x, float y, int color) {
        p.fill(color);
        p.rect(x*gridSize, y*gridSize, gridSize, gridSize);
    }

    public void render() {

        //Draw normal squares
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                drawSquare(col, row, colors[grid[row][col]]);
            }
        }

//        //Path rendering
//        if (path != null) {
//            for (INode iN : path) {
//                Node n = (Node) iN;
//                drawSquare(n.getPosition().x, n.getPosition().y, colors[4]);
//            }
//        }


        //Render renderables, like characters
        for (Renderable r : renderables) {
            r.render();
        }

        //Gridlines
        p.stroke(0);
        renderGridLines();
    }

//    public void render(Renderable r) {
//        drawSquare(gridSize, r.getPosition().x, r.getPosition().y, r.getColor());
//        //System.out.println(r.getPosition());
//    }

    public void render(PVector position, int color) {
        drawSquare(position.x, position.y, color);
        //System.out.println(r.getPosition());
    }

    public void renderGridLines() {
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

    public PVector setStartPos(PVector pos) {
        PVector temp = this.startPos;
        this.startPos = pos;
        return temp;
    }

    public PVector setEndPos(PVector pos) {
        PVector temp = this.endPos;
        this.endPos = pos;
        return temp;
    }

    public PVector getStartPos() {
        return this.startPos;
    }

    public PVector getEndPos() {
        return this.endPos;
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

    public static int distance(PVector a, PVector b) {
        return (int) (10 * Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2)));
    }
}
