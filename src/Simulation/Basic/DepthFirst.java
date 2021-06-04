package Simulation.Basic;

import GraphComponent.Edge;
import GraphComponent.Vertex;
import map.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepthFirst {
    //the id is array
    private static Graph G;
    private static int C;
    private static List<String> tree;

    public static void run(Graph G, int C) {
        double tourCost = 0;
        DepthFirst.G = G;
        DepthFirst.C = C;
        System.out.println("---DF Search---\n");
        long start = System.nanoTime();
//        String result = search(G.getHead());
        long end = System.nanoTime();
        System.out.println("Tour Cost: " + tourCost);
        //System.out.println(result);
        System.out.println("Execution time: " + (double) (end - start) * Math.pow(10, -6) + "ms\n");
        tree = new ArrayList<>();
        search(0, 0, "");

        for (Object element : tree) {
            System.out.println(element);
        }

    }

    private static void search(int capacity, int vertexID, String currentList) {
        Vertex currentVertex = G.getVertex(vertexID);
        capacity += currentVertex.capacity;
        if (vertexID != 0) {
            currentList += vertexID + " ";
        }
        for (int i = 0; i < currentVertex.EdgeList.size(); i++) {
            Edge currentEdge = currentVertex.EdgeList.get(i);
            Vertex destination = currentEdge.destination;
            if (capacity + destination.capacity > C || currentList.contains(destination.ID + " ") || destination.ID == 0) {
                //put something here?
            } else {
                // System.out.println(capacity);
                search(capacity, destination.ID, currentList);
            }
        }
        tree.add(currentList);

    }
}
