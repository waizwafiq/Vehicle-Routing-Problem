package Simulation.Basic;

import GraphComponent.Edge;
import GraphComponent.Vertex;
import map.Graph;

import java.util.ArrayList;
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
        generateTree(0, 0, "");
        search();
        for (Object element : tree) {
            System.out.println(element);
        }

    }

    private static void generateTree(int capacity, int vertexID, String currentList) {
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
                generateTree(capacity, destination.ID, currentList);
            }
        }
        if(vertexID!=0) {
            tree.add("0 "+currentList+"0");
        }

    }

    private static void search(){
        for(String element : tree){

            double distance = 0;
            int capacity = 0;

            //Generate array of nodes
            String[] strArray = element.split(" ");
            int[] nodes = new int[strArray.length];
            for(int i=0;i< strArray.length;i++){
                nodes[i] = Integer.parseInt(strArray[i]);
                capacity+=G.getVertex(nodes[i]).capacity;
            }



            for(int i=0;i< nodes.length-1;i++){
                distance+= computeDistance(G.getVertex(nodes[i]),G.getVertex(nodes[i+1]));
            }
            System.out.println(distance+" c| "+capacity);
        }
    }

    private static double computeDistance(Vertex v1, Vertex v2) {
        double dx = v1.coordinateX - v2.coordinateX;
        double dy = v1.coordinateY - v2.coordinateY;

        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
