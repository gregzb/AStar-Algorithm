import processing.core.PVector;

public class Character implements Renderable{
    private PVector position;
    private int color;
    private Grid grid;

    public Character(Grid grid, PVector position, int color) {
        this.position = position;
        this.color = color;
        this.grid = grid;
    }

    public PVector setPosition(PVector position) {
        PVector temp = this.position;
        this.position = position;
        return temp;
    }

    public PVector getPosition() {
        return position.copy();
    }

    @Override
    public void render() {
        grid.render(getPosition(), color);
    }
}
