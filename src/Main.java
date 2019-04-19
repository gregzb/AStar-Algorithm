import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class Main extends PApplet {

    public static final int GRID_SIZE = 30;

    private static Grid grid;
    private boolean prevMousePressed;
    private boolean prevKeyPressed;

    List<Node> path;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        grid = new Grid(this, 25, 25);
        surface.setSize(grid.getWidth() * GRID_SIZE, grid.getHeight() * GRID_SIZE);
    }

    public void draw() {
        background(255);

        PVector mouseGridLocation = new PVector(mouseX / GRID_SIZE, mouseY / GRID_SIZE);
        if (mouseGridLocation.x < grid.getWidth() && mouseGridLocation.x >= 0 && mouseGridLocation.y < grid.getHeight() && mouseGridLocation.y >= 0) {
            if (mousePressed && !prevMousePressed) {
                path = null;
                if (mouseButton == LEFT) {
                    if (keyPressed && (key == 's' || key == 'S')) {
                        grid.setStartPos(mouseGridLocation);
                        System.out.println("LEFT: " + mouseGridLocation);
                    } else if (keyPressed && (key == 'e' || key == 'E')) {
                        grid.setEndPos(mouseGridLocation);
                        System.out.println("RIGHT: " + mouseGridLocation);
                    }
                }
            } else if (mousePressed && !keyPressed) {
                path = null;
                if (mouseButton == LEFT) {
                    grid.setPassable(mouseGridLocation, false);
                } else if (mouseButton == RIGHT) {
                    grid.setPassable(mouseGridLocation, true);
                }
            }
        }

        if (keyPressed && !prevKeyPressed) {
            path = null;
            if (key == ' ') {
                path = AStar.findPath(grid);
                for (int i = 0; i < path.size(); i++) {
                    Node node = path.get(i);
                    System.out.print(node + " --> ");
                }
            }
        }

        grid.render(GRID_SIZE, path);

        prevMousePressed = mousePressed;
        prevKeyPressed = keyPressed;
    }
}
