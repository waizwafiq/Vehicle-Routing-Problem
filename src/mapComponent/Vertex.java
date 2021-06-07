package mapComponent;

import java.util.ArrayList;

public class Vertex {
    public double coordinateX, coordinateY;
    public int capacity, ID;
    public boolean visited = false, narrowArea = false;
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

    public Vertex visit() {
        visited = true;
        return this;
    }

    public void unvisit() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setNarrowArea(boolean narrowArea) {
        this.narrowArea = narrowArea;
    }

    public boolean isNarrowArea() {
        return narrowArea;
    }

    @Override
    public String toString() {
        return "{ID " + ID + "} [NARROW: " + narrowArea + "] ";
    }
}
