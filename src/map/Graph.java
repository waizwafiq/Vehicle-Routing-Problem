package map;

import GraphComponent.*;

import java.util.ArrayList;

public class Graph {
    public ArrayList<Vertex> vertexArrayList; //holds vertices arraylist

    public Graph() {
        vertexArrayList = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertexArrayList.add(vertex);
    }

    public Vertex getHead() {
        return vertexArrayList.get(0);
    }

    public void completeConnect() {
        //to make a complete graph
        for (int i = 0; i < vertexArrayList.size(); i++) {
            Vertex sourceVertex = vertexArrayList.get(i);
            for (Vertex destination : vertexArrayList) {
                double dist = computeDistance(sourceVertex, destination);

                if (sourceVertex.ID != destination.ID)
                /*(sourceVertex.ID == (destination.ID)) {
                    //maybe for future use?
                } else */ {
                    //avoid duplication
                    Edge newEdge = new Edge(destination, dist);

                    sourceVertex.EdgeList.add(newEdge);

                    // destination.EdgeList.add(new Edge(sourceVertex,dist));
                }
            }
        }
    }

    public void printConnections() {
        System.out.println(vertexArrayList.get(0));
        for (Vertex curr : vertexArrayList) {
            System.out.println("Vertex ID" + curr.ID + " " + curr.EdgeList.toString());
        }
    }

    private double computeDistance(Vertex v1, Vertex v2) {
        double dx = v1.coordinateX - v2.coordinateX;
        double dy = v1.coordinateY - v2.coordinateY;

        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
