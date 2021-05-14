package zul.src.GraphComponent;

import zul.src.GraphImplementation.Graph;

import java.util.ArrayList;

public class Vertex extends Graph {
    public int coordinateX;
    public int coordinateY;
    public int ID;
    public int capacity;

    public ArrayList<Edge> EdgeList;

    public Vertex() {

    }

    public Vertex(int coordinateX, int coordinateY, int capacity, int ID) {
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
        return "Vertex{" +
                "ID=" + ID +
                '}';
    }

}
