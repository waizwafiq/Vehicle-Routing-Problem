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
        PSEUDOCODE?:
            Given a graph, G.
            While all vertices aren't visited:
                Set dT as 0. (dT is the total distance travelled)

                Let dV: the expected path each vertex take
                Set dV = {+inf, +inf, +inf, ...}

                Send a vehicle.
                Start from the depot (ID 0)

                Let currV: current Vertex
                Loop through all other vertices:
                    Let currE: current Edge
                    Loop through all edges/path connected from currV:
                        if the demand is sufficient AND the destination isn't visited AND dT + the path's distance < dV:
                            choose this path.
                            update dV with the new expected total distance value
                            
                    Add the chosen vertex to go into the "visited" list
                    Update the total distance travelled by the vehicle.
                    Deduct the capacity (send the package)
                    Go to the chosen vertex
        */
        //array of expected dist selected by each vertex, (initially +inf to get the minimum)
        double[] distV = new double[G.size()];
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot

        while (visitedID.size() != distV.length - 1) {
            //while all vertices haven't been visited
            //EACH LOOP REPRESENTS ONE DELIVERY VEHICLE

            double dT = 0; //the total distance travelled
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

                    if (tempC >= currentEdge.destination.capacity && dT + currentEdge.dist < distV[i] && !visitedID.contains(currentEdge.destination.ID)) {
                        /* IF (capacity >= demand) AND (dT + dist < expected_path_dist) AND (the destination hasn't been visited yet):
                                choose this path.
                        */
                        nextVertex = currentEdge.destination; // path to go
                        distV[i] = dT + currentEdge.dist;  //update the path value the vertex holds
                    }
                }
                visitedID.add(nextVertex.ID); //the nextVertex has been visited.
                System.out.print(" --> " + nextVertex);

                //update the values
                dT = distV[i]; //update total distance travelled
                tempC -= nextVertex.capacity; //deduct capacity
                currentVertex = nextVertex;

                if (currentVertex.ID == 0)
                    //if the vehicle returns to the depot, break the loop/go to the next vehicle
                    break;
            }
            visitedID.remove((Integer) 0); //used Integer to make it as an object
            System.out.println();
            System.out.println("dT : "+dT);
        }
    }
}