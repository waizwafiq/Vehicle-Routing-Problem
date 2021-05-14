package GraphComponent;

import java.util.ArrayList;

public class Vertex {
    public double coordinateX, coordinateY;
    public int capacity, ID;

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

    @Override
    public String toString() {
        return "{ID " + ID + "} ";
    }
}
