package Simulation;

import map.Graph;
import GraphComponent.*;

import java.util.ArrayList;
import java.util.Arrays;

//Dijkstra's Traversal Algorithm
public class Dijkstra {

    private static Graph G;
    private static int C;

    public static void run(Graph G, int C) {
        Dijkstra.G = G;
        Dijkstra.C = C;
        search(G.getVertex(0), G.getVertex(0));
    }

    private static void search(Vertex start, Vertex end) {
        /*
            min = +infinity
            distV = {0, +inf, +inf, ...} <- for every vertices
            minEdge = null <-- the edge with the lowest dist connected from the vertex
            Start from the depot (ID 0)

            Let currV: current Vertex
            Loop through all vertices other than its own vertex:
                Let currE: current Edge
                Loop through all edges connected from currV:
                    if the destination hasn't been visited yet:
                        min = Math.min(min, dist)


        */

        double min = 0;
        double[] distV = new double[G.size()];
        int tempC;
        ArrayList<Integer> visitedID = new ArrayList<>();

        while(visitedID.size() != distV.length-1) {
            Arrays.fill(distV, Double.POSITIVE_INFINITY);
            Vertex currentVertex = G.getHead().unvisit();
            Vertex nextVertex = G.getHead();
            tempC = C;

            System.out.print(currentVertex);
            for (int i = 0; i < G.size(); i++) {
                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    Edge currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge connected to the vertex
                    //System.out.println("\n"+currentVertex+"dest-> "+currentEdge.destination+"|| "+!currentEdge.destination.isVisited()+" ||| "+ (min + currentEdge.dist < distV[i]));
                    if (!currentEdge.destination.isVisited() && tempC - currentEdge.destination.capacity >= 0 && min + currentEdge.dist < distV[i] && !visitedID.contains(currentEdge.destination.ID)) {
                        //System.out.println(currentEdge.dist + " dist<");
                        G.unvisitAll();

                        nextVertex = currentEdge.destination.visit();
                        distV[i] = min + currentEdge.dist;

                        //System.out.println("C : "+C);
                    }
                }
                //System.out.println("---------------");
                visitedID.add(nextVertex.ID);
                System.out.print(" --> " + nextVertex);
                min = distV[i];
                tempC -= nextVertex.capacity;
                currentVertex = nextVertex;

                if (currentVertex.ID == 0)
                    break;
            }
/*            for(Vertex V: G.vertexArrayList)
                System.out.println(V+" "+V.isVisited());*/

            visitedID.remove((Integer) 0); //<-- make it as an object
            /*for(int a: visitedID)
                System.out.println("bb "+a);*/
            System.out.println();
        }
    }
}