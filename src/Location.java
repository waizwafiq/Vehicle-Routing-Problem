public class Location<T> extends Map {
    //the Location class refers to the vertices in the graph
    //note: the graph is complete, weighted (euclidean distance) and undirected.
    protected double x, y;
    protected int demand_size;
    protected T ID;

    protected int deg;
    protected Location<Integer> nextLoc;
    protected Path firstPath;
    protected boolean visited;

    public Location(double x, double y, int demand_size, T ID) {
        this.visited = false;
        this.x = x;
        this.y = y;
        this.demand_size = demand_size;
        this.ID = ID;
    }

    //GETTERS
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDemand_size() {
        return demand_size;
    }

    public T getID() {
        return ID;
    }

    public boolean isVisited() {
        return visited;
    }

    //SETTERS
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDemand(int demand_size) {
        this.demand_size = demand_size;
    }

    public void setID(T ID) {
        this.ID = ID;
    }

    public Location<Integer> visit() {
        this.visited = true;
        return (Location<Integer>) this;
    }

    public void unvisit() {
        this.visited = false;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", demand_size=" + demand_size +
                ", ID=" + ID +
                '}';
    }
}
