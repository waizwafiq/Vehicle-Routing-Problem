package map;

import mapComponent.*;

import java.util.ArrayList;

public class Map {
    public ArrayList<Vertex> vertexArrayList; //holds vertices arraylist

    public Map() {
        vertexArrayList = new ArrayList<>();
    }

    public int size() {
        return vertexArrayList.size();
    }

    public void addVertex(Vertex vertex) {
        vertexArrayList.add(vertex);
    }

    public void addEdge(int ID_source, int ID_dest) {
        double dist = computeDistance(getVertex(ID_source), getVertex(ID_dest)); //calculate the distance(weight)
        Edge newEdge = new Edge(getVertex(ID_dest), dist); // create the new edge
        getVertex(ID_source).EdgeList.add(newEdge);
    }

    public boolean hasEdge(int ID_source, int ID_dest) {
        double weight = computeDistance(getVertex(ID_source), getVertex(ID_dest));
        return getVertex(ID_source).EdgeList.contains(new Edge(getVertex(ID_dest), weight));
    }

    public Vertex getHead() {
        return vertexArrayList.get(0);
    }

    public Vertex getVertex(int idx) {
        if (idx < 0 || idx >= size())
            return null;

        return vertexArrayList.get(idx);
    }

    public Vertex getLast() {
        return vertexArrayList.get(vertexArrayList.size() - 1);
    }

    public void unvisitAll() {
        for (Vertex V : vertexArrayList)
            V.unvisit();

    }

    public boolean isAllVisited() {
        boolean out = false;
        for (Vertex V : vertexArrayList)
            out = V.isVisited();

        return out;
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

    public static double computeDistance(Vertex v1, Vertex v2) {
        double dx = v1.coordinateX - v2.coordinateX;
        double dy = v1.coordinateY - v2.coordinateY;

        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
