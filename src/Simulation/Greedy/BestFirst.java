package Simulation.Greedy;

import mapComponent.Edge;
import mapComponent.Vertex;
import map.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BestFirst {

    private static Map G;
    private static int N, C, lorries;
    private static double tourCost = 0;

    public static void run(Map G, int N, int C, int numberOfLorries) {
        BestFirst.lorries = numberOfLorries;
        BestFirst.G = G;
        BestFirst.N = N;
        BestFirst.C = C;
        System.out.println("---Best-First Search---\n");
        long start = System.nanoTime();
        String result = search();
        long end = System.nanoTime();

        System.out.println("Tour Cost: " + tourCost);
        System.out.println(result);
        System.out.println("Execution time: " + (double) (end - start) * Math.pow(10, -6) + "ms\n");
    }

    private static String search() {
        /*
        PSEUDOCODE?:
            Given a graph, G.
            While all vertices aren't visited:
                Set dT as 0. (dT is the total distance travelled)
                Let hV: the expected path cost each vertex take
                Set hV = {+inf, +inf, +inf, ...}
                Send a vehicle.
                Start from the depot (ID 0)
                Let currV: current Vertex
                Loop through all other vertices:
                    Let currE: current Edge
                    Loop through all edges/path connected from currV:
                        if the demand is sufficient AND the destination isn't visited AND the heuristic < hV:
                            NOTE: heuristic = the destination's demand size
                            choose this path.
                            update cV with the new expected total cost value
                            keep the chosen path's demand size to update dTs
                    Add the chosen vertex to go into the "visited" list
                    Update the total distance travelled by the vehicle. (dT = dV - chosen_demand)
                    Deduct the capacity (send the package)
                    Go to the chosen vertex using the chosen path.
        */
        //array of expected edge cost selected by each vertex, (initially +inf to get the minimum)

        for (int i = 0; i < G.size(); i++) {
            ArrayList<Edge> e = G.getVertex(i).EdgeList;
            Collections.sort(e);
        }


        double[] hV = new double[G.size()];
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot
        StringBuilder outString = new StringBuilder();

        int vehicleCount = 0, lorryCount = 0;
        while (visitedID.size() != N - 1) {
            //while all vertices haven't been visited
            int tempC;
            if (lorries != 0) {
                //if there are still lorries to be dispatched out:
                tempC = 2 * C; //to deduct the capacity in lorry whenever a vertex is visited
                outString.append("Vehicle ").append(++vehicleCount).append(" (Lorry ").append(++lorryCount).append(")\n");
                lorries--;
            } else {
                tempC = C;
                outString.append("Vehicle ").append(++vehicleCount).append("\n");
            }
            double dT = 0; //the total distance travelled
            Arrays.fill(hV, 0);

            Vertex currentVertex = G.getHead();
            Vertex nextVertex = G.getHead();

            outString.append(currentVertex);
            int totalCap = 0;
            boolean dispatched = false;
            for (int i = 0; i < G.size(); i++) {
                //go through every vertices in the graph

                double tempD = 0; //holds temp distance
                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    //go through every edges connected to current vertex
                    Edge currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge

                    if (tempC >= currentEdge.destination.capacity && currentEdge.destination.capacity > hV[i] && !visitedID.contains(currentEdge.destination.ID) && currentEdge.destination.ID != 0) {
                        /* IF (capacity >= demand) AND (destination's capacity < expected_path_dist) AND (the destination hasn't been visited yet):
                                choose this path.
                        */
                        dispatched = true;
                        nextVertex = currentEdge.destination; // path to go

                        hV[i] = currentEdge.destination.capacity;  //update the path cost value the vertex holds
                        tempD = currentEdge.dist;
                    }
                }
                if (tempC < nextVertex.capacity || visitedID.size() == N - 1 || currentVertex == nextVertex) {
                    Edge currentEdge = nextVertex.EdgeList.get(0);
                    nextVertex = G.getHead();
                    tempD = Map.computeDistance(currentVertex, nextVertex);
                }

                if (!dispatched && nextVertex.ID == 0) {
                    outString.append(" --X-> NOT DISPATCHED");
                    break;
                }

                visitedID.add(nextVertex.ID); //the nextVertex has been visited.
                outString.append(" --> ").append(nextVertex);

                //update the values
                dT += tempD; //update total distance travelled
                tempC -= nextVertex.capacity; //deduct capacity
                totalCap += nextVertex.capacity;
                currentVertex = nextVertex;
                if (currentVertex.ID == 0)
                    //if the vehicle returns to the depot, break the loop/go to the next vehicle
                    break;
            }
            if (currentVertex.ID == 0 && nextVertex.ID == 0 && visitedID.size() == N - 1)
                break;
            visitedID.remove((Integer) 0); //used Integer to make it as an object
            outString.append("\nCapacity: ").append(totalCap);
            outString.append("\nCost: ").append(dT).append("\n");
            tourCost += dT;
        }
        return outString.toString();
    }
}