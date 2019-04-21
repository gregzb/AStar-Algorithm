import processing.core.PApplet;
import processing.core.PVector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main extends PApplet {

    public static final int GRID_SIZE = 20;

    private static Grid grid;
    private boolean prevMousePressed;
    private boolean prevKeyPressed;

    private List<INode> path;

    private Path pathRenderable;
    private Character mainChar;
    private PVector goalPos;

    private double lastTime;
    private double deltaTime = .01;

    private boolean editing = false;

    private double defaultNextPosTime = .1;
    private double nextPosCounter = defaultNextPosTime;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        resize(50, 50, GRID_SIZE);

        path = new ArrayList<>();

        pathRenderable = new Path(grid, path, color(40, 220, 40));
        grid.addRenderable(pathRenderable);

        mainChar = new Character(grid, new PVector(grid.getWidth()/2, grid.getHeight()/2), color(255, 0, 0));
        grid.addRenderable(mainChar);

        lastTime = System.nanoTime();
    }

    public boolean inBounds(PVector vector) {
        return vector.x < grid.getWidth() && vector.x >= 0 && vector.y >= 0 && vector.y < grid.getHeight();
    }

    public void updatePath() {
        path = AStar.findPath(grid);
        pathRenderable.setPositions(path);
    }

    public void draw() {
        deltaTime = (System.nanoTime() - lastTime) * Math.pow(10, -9);
        lastTime = System.nanoTime();

        background(255);

        grid.setStartPos(mainChar.getPosition().copy());

        PVector mouseGridLocation = new PVector(mouseX / GRID_SIZE, mouseY / GRID_SIZE);

        pathRenderable.setPositions(path);

        if (inBounds(mouseGridLocation)) {
            if (mousePressed) {
                pathRenderable.setPositions(new ArrayList<>());
                int fillVal = mouseButton == LEFT ? 1 : 0;
                grid.set(mouseGridLocation.copy(), fillVal);
                for (int i = 0; i < Node.dirs.length/2; i+=2) {
                    PVector newPos = mouseGridLocation.copy().add(Node.dirs[i], Node.dirs[i + 1]);
                    if (inBounds(newPos)) grid.set(newPos, fillVal);
                }
                editing = true;
            } else if (!mousePressed && prevMousePressed) {
                updatePath();
                editing = false;
            }
            if (keyPressed) {
                if (key == ' ') {
                    grid.setEndPos(mouseGridLocation.copy());
                    updatePath();
                    editing = false;
                }
            }
        }

        if (!editing) {
            while (nextPosCounter < 0) {
                if (path != null && path.size() > 0) {
                    PVector newPos = ((Node)path.remove(0)).getPosition();
                    mainChar.setPosition(newPos);
                }
                nextPosCounter += defaultNextPosTime;
            }
            nextPosCounter-= deltaTime;
        }

        grid.render();

        prevMousePressed = mousePressed;
        prevKeyPressed = keyPressed;
    }

//    void loadMaze(String filename) {
//        File file = new File(filename);
//
//        BufferedReader br = null;
//
//        int width = 0;
//        int height = 0;
//
//        String everything = "";
//
//        try {
//            br = new BufferedReader(new FileReader(file));
//
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                height ++;
//                width = line.length();
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//            everything = sb.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        System.out.println(width + ", " + height);
//        resize(width, height);
//        String[] lines = everything.split(System.lineSeparator());
//        for (int row = 0; row < lines.length; row++) {
//            for (int col = 0; col < lines[row].length(); col++) {
//                char myChar = lines[row].charAt(col);
//                if (myChar == '#') {
//                    grid.set(new PVector(col, row), 1);
//                } else if (myChar == ' ') {
//                    grid.set(new PVector(col, row), 0);
//                }
//            }
//        }
//    }

    void resize(int width, int height, int gridSize) {
        grid = new Grid(this, width, height, gridSize);
        surface.setSize(grid.getWidth() * gridSize, grid.getHeight() * gridSize);
    }
}
