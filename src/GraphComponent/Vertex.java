package GraphComponent;

import java.util.ArrayList;

public class Vertex {
    public double coordinateX, coordinateY;
    public int capacity, ID;
    public boolean visited = false;

    public ArrayList<Edge> EdgeList;

    public Vertex() {
    }

    public Vertex(double coordinateX, double coordinateY, int capacity, int ID) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.ID = ID;
        this.capacity = capacity;
        EdgeList = new ArrayList<>();
    }

    public int visit() {
        visited = true;
        return this.ID;
    }

    public int unvisit() {
        visited = false;
        return this.ID;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public String toString() {
        return "{ID " + ID + "} ";
    }
}
