public class Location {
    //the Location class refers to the vertices in the graph
    //note: the graph is complete, weighted (euclidean distance) and undirected.
    private double x, y;
    private int demand_size, ID;
    private boolean visited;

    public Location(double x, double y, int demand_size, int ID) {
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

    public int getID() {
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public Location visit() {
        this.visited = true;
        return this;
    }

    public Location unvisit() {
        this.visited = false;
        return this;
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
