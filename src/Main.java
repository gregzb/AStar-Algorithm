import processing.core.PApplet;
import processing.core.PVector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Main extends PApplet {

    public static final int GRID_SIZE = 20;

    private static Grid grid;
    private boolean prevMousePressed;
    private boolean prevKeyPressed;

    List<INode> path;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        resize(50, 50);
    }

    public void draw() {
        background(255);

        PVector mouseGridLocation = new PVector(mouseX / GRID_SIZE, mouseY / GRID_SIZE);
//        if (mouseGridLocation.x < grid.getWidth() && mouseGridLocation.x >= 0 && mouseGridLocation.y < grid.getHeight() && mouseGridLocation.y >= 0) {
//            if (mousePressed && !prevMousePressed) {
//                path = null;
//                if (mouseButton == LEFT) {
//                    if (keyPressed && (key == 's' || key == 'S')) {
//                        grid.setStartPos(mouseGridLocation);
//                        //System.out.println("LEFT: " + mouseGridLocation);
//                    } else if (keyPressed && (key == 'e' || key == 'E')) {
//                        grid.setEndPos(mouseGridLocation);
//                        //System.out.println("RIGHT: " + mouseGridLocation);
//                    }
//                }
//                if (!keyPressed) {
//                    if (mouseButton == LEFT) {
//                        grid.setPassable(mouseGridLocation, false);
//                    } else if (mouseButton == RIGHT) {
//                        grid.setPassable(mouseGridLocation, true);
//                    }
//                }
//            } else if (mousePressed && !keyPressed) {
//                path = null;
//                if (mouseButton == LEFT) {
//                    grid.setPassable(mouseGridLocation, false);
//                    for (int i = 0; i < Node.dirs.length/2; i+=2) {
//                        PVector newPos = mouseGridLocation.copy().add(Node.dirs[i], Node.dirs[i + 1]);
//                        if (newPos.x >= 0 && newPos.x < grid.getWidth() && newPos.y >= 0 && newPos.y < grid.getHeight())
//                        grid.setPassable(newPos, false);
//                    }
//                } else if (mouseButton == RIGHT) {
//                    grid.setPassable(mouseGridLocation, true);
//                    for (int i = 0; i < Node.dirs.length/2; i+=2) {
//                        PVector newPos = mouseGridLocation.copy().add(Node.dirs[i], Node.dirs[i + 1]);
//                        if (newPos.x >= 0 && newPos.x < grid.getWidth() && newPos.y >= 0 && newPos.y < grid.getHeight())
//                        grid.setPassable(newPos, true);
//                    }
//                }
//            }
//        }
//
//        if (keyPressed && !prevKeyPressed) {
//            path = null;
//            if (key == ' ') {
//                path = AStar.findPath(grid);
//            } else if (key == 'R' || key == 'r') {
//                grid = new Grid(this, grid.getWidth(), grid.getHeight());
//            } else if (key == 'L' || key == 'l') {
//                grid = new Grid(this, grid.getWidth(), grid.getHeight());
//                loadMaze("maze.txt");
//            }
//        }
//
//        if (prevMousePressed && !mousePressed) {
//            path = AStar.findPath(grid);
//        }

        grid.render(GRID_SIZE, path);

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

    void resize(int width, int height) {
        grid = new Grid(this, width, height);
        surface.setSize(grid.getWidth() * GRID_SIZE, grid.getHeight() * GRID_SIZE);
    }
}
