package GraphComponent;

import map.Graph;
import java.util.ArrayList;

public class Vertex extends Graph {
    public double coordinateX;
    public double coordinateY;
    public int ID;
    public int capacity;

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

    public void printEdges (){
        EdgeList.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "ID=" + ID +
                "} ";
    }
}
