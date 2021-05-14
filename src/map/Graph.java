package map;

import GraphComponent.*;
import java.util.ArrayList;

public class Graph {
    public ArrayList<Vertex> vertexArrayList;

    public Graph() {
        vertexArrayList = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertexArrayList.add(vertex);
    }

    public Vertex firstNode() {
        return vertexArrayList.get(0);
    }

    public void addAllEdge() {
        for (int i = 0; i < vertexArrayList.size(); i++) {
            Vertex sourceVertex = vertexArrayList.get(i);
            for (int j = 0; j < vertexArrayList.size(); j++) {
                Vertex destination = vertexArrayList.get(j);
                double dist = computeDistance(sourceVertex, destination);

                if (sourceVertex.ID == (destination.ID)) {

                } else {
                    Edge newEdge = new Edge(destination, dist);

                    sourceVertex.EdgeList.add(newEdge);

                    // destination.EdgeList.add(new Edge(sourceVertex,dist));
                }
            }
        }
    }

    public void printAllVertexEdges() {
        for (int i = 0; i < vertexArrayList.size(); i++) {
            Vertex curr = vertexArrayList.get(i);
            System.out.println("Vertex ID" + curr.ID + " " + curr.EdgeList.toString());
        }
    }

    double computeDistance(Vertex v1, Vertex v2) {
        int x = v1.coordinateX - v2.coordinateX;
        int y = v1.coordinateY - v2.coordinateY;

        return Math.sqrt((x * x) + (y * y));
    }
}
