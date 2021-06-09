package Simulation.Greedy;

import map.Map;
import mapComponent.*;

import java.util.ArrayList;
import java.util.Arrays;

//Best-First Traversal Algorithm
public class BestFirst {

    private static Map G;
    private static int C, lorries;
    private static double tourCost;

    public static void run(Map G, int C, int numberOfLorries) {
        BestFirst.lorries = numberOfLorries;
        tourCost = 0; //just in case if we want to do multiple A* searches (to reset)
        BestFirst.G = G;
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

                Let hV: the expected path heuristic function each vertex take
                Set hV = {+inf, +inf, +inf, ...}

                Send a vehicle.
                Start from the depot (ID 0)

                Let currV: current Vertex
                Loop through all other vertices:
                    Let currE: current Edge
                    Loop through all edges/path connected from currV:
                        if the demand is sufficient AND the destination isn't visited AND the heuristic < hV AND the destination is not the depot:
                            NOTE: heuristic = the destination's demand size
                            choose this path.
                            update hV with the new expected total heuristic value
                            keep the chosen path's distance

                    if the capacity is insufficient OR all vertices are visited:
                        choose the path to the depot.
                        update hV with the new expect heuristic value
                        keep the chosen path's distance

                    Add the chosen vertex to go into the "visited" list
                    Update the total distance travelled by the vehicle. (from the saved chosen path's distance)
                    Deduct the capacity (send the package)
                    Go to the chosen vertex using the chosen path, if possible.
        */
        //array of expected edge heuristic selected by each vertex, (initially +inf to get the minimum)
        double[] heurV = new double[G.size()];
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot
        StringBuilder outString = new StringBuilder();

        int vehicleCount = 0, lorryCount = 0;
        while (visitedID.size() != heurV.length - 1) {
            //while all vertices haven't been visited
            int tempC;
            boolean lorryUsed = false;
            outString.append("---------------------\n");
            if (lorries != 0) {
                //if there are still lorries to be dispatched out:
                tempC = 2 * C; //to deduct the capacity in lorry whenever a vertex is visited
                outString.append("Vehicle ").append(++vehicleCount).append(" (Lorry ").append(++lorryCount).append(")\n");
                lorries--;
                lorryUsed = true;
            } else {
                tempC = C;
                outString.append("Vehicle ").append(++vehicleCount).append("\n");
            }
            double dT = 0; //the total distance travelled
            Arrays.fill(heurV, Double.POSITIVE_INFINITY);

            Vertex currentVertex = G.getHead();
            Vertex nextVertex = G.getHead();

            outString.append(currentVertex);
            int totalCap = 0;
            boolean dispatched = false;
            for (int i = 0; i < G.size(); i++) {
                //go through every vertices in the graph
                Edge currentEdge = currentVertex.EdgeList.get(0);
                double tempD = 0; //holds temp distance for dT
                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    //go through every edges connected to current vertex
                    currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge

                    if (lorryUsed && currentEdge.destination.narrowArea)
                        //if lorry is currently used and the destination is in the narrow area, don't go here
                        continue;

                    if (tempC >= currentEdge.destination.capacity && currentEdge.destination.capacity < heurV[i] && !visitedID.contains(currentEdge.destination.ID) && currentEdge.destination.ID != 0) {
                        /* IF (capacity >= demand) AND (capacity < expected_path_cost) AND (the destination hasn't been visited yet) AND (the dest is not the depot):
                                choose this path.
                        */
                        dispatched = true;
                        nextVertex = currentEdge.destination; // path to go
                        heurV[i] = currentEdge.destination.capacity;  //update the path cost value the vertex holds
                        tempD = dT + currentEdge.dist;
                    }
                }
                if (tempC < currentEdge.destination.capacity || (visitedID.size() == heurV.length - 1)) {
                    // if the capacity is insufficient OR all vertices are visited
                    // this is to avoid infinite loop
                    // Duplicates happens when the capacity in the vehicle is still sufficient but all vertices are visited.
                    currentEdge = currentVertex.EdgeList.get(0); //go back to the depot
                    nextVertex = currentEdge.destination; // path to go
                    heurV[i] = currentEdge.destination.capacity;  //update the path cost value the vertex holds
                    tempD = dT + currentEdge.dist;
                }

                if (!dispatched && nextVertex.ID == 0) {
                    outString.append(" --X-> NOT DISPATCHED");
                    break;
                }

                visitedID.add(nextVertex.ID); //the nextVertex has been visited.
                outString.append(" --> ").append(nextVertex);

                //update the values
                dT = tempD; //update total distance travelled
                tempC -= nextVertex.capacity; //deduct capacity
                totalCap += nextVertex.capacity;
                currentVertex = nextVertex;

                if (nextVertex.ID == 0)
                    break;
            }
            visitedID.remove((Integer) 0); //used Integer to make it as an object
            outString.append("\nCapacity: ").append(totalCap);
            outString.append("\nCost: ").append(dT).append("\n");
            tourCost += dT;
        }
        return outString.toString();
    }
}