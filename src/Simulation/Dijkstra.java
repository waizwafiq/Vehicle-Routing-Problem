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
        PSEUDOCODE:
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

        //array of shortest dist selected by each vertex, (initially +inf to get the minimum)
        double[] distV = new double[G.size()];
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot

        while (visitedID.size() != distV.length - 1) {
            //while all vertices haven't been visited
            //EACH LOOP REPRESENTS ONE DELIVERY VEHICLE
            Arrays.fill(distV, Double.POSITIVE_INFINITY);

            Vertex currentVertex = G.getHead();
            Vertex nextVertex = G.getHead();
            int tempC = C; //to deduct the capacity in vehicle whenever a vertex is visited

            System.out.print(currentVertex); //to print the depot
            for (int i = 0; i < G.size(); i++) {
                //go through every vertices in the graph
                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    //go through every edges connected to current vertex
                    Edge currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge

                    if (tempC >= currentEdge.destination.capacity && min + currentEdge.dist < distV[i] && !visitedID.contains(currentEdge.destination.ID)) {
                        /* IF (capacity >= demand) AND (min + dist < chosen_path_dist) AND (the destination hasn't been visited yet):
                                choose this path.
                         */
                        nextVertex = currentEdge.destination;
                        distV[i] = min + currentEdge.dist;
                    }
                }
                visitedID.add(nextVertex.ID);
                System.out.print(" --> " + nextVertex);
                min = distV[i];
                tempC -= nextVertex.capacity;
                currentVertex = nextVertex;

                if (currentVertex.ID == 0)
                    break;
            }
            visitedID.remove((Integer) 0); //<-- make it as an object
            System.out.println();
        }
    }
}