package Simulation.Greedy;

import mapComponent.Edge;
import mapComponent.Vertex;
import map.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class BestPath {
    private static Map G;
    private static int C, lorries;
    private static double tourCost = 0;

    public static void run(Map G, int C, int numberOfLorries) {
        BestPath.lorries = numberOfLorries;
        BestPath.G = G;
        BestPath.C = C;
        System.out.println("---Best-Path Search---\n");
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
                Let cV: the expected path cost each vertex take
                Set cV = {+inf, +inf, +inf, ...}
                Send a vehicle.
                Start from the depot (ID 0)
                Let currV: current Vertex
                Loop through all other vertices:
                    Let currE: current Edge
                    Loop through all edges/path connected from currV:
                        if the demand is sufficient AND the destination isn't visited AND dT + the path's distance + the heuristic< cV:
                            NOTE: heuristic = the destination's demand size
                            choose this path.
                            update cV with the new expected total cost value
                            keep the chosen path's demand size to update dTs
                    Add the chosen vertex to go into the "visited" list
                    Update the total distance travelled by the vehicle.
                    Deduct the capacity (send the package)
                    Go to the chosen vertex using the chosen path.
        */
        //array of expected edge cost selected by each vertex, (initially +inf to get the minimum)
        double[] costV = new double[G.size()];
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot
        StringBuilder outString = new StringBuilder();

        int vehicleCount = 0, lorryCount = 0;
        while (visitedID.size() != costV.length - 1) {
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
            Arrays.fill(costV, Double.POSITIVE_INFINITY);

            Vertex currentVertex = G.getHead();
            Vertex nextVertex = G.getHead();

            outString.append(currentVertex);
            int totalCap = 0;
            boolean dispatched = false;
            for (int i = 0; i < G.size(); i++) {
                //go through every vertices in the graph


                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    //go through every edges connected to current vertex
                    Edge currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge

                    if (lorryUsed && currentEdge.destination.narrowArea)
                        //if lorry is currently used and the destination is in the narrow area, don't go here
                        continue;

                    if (tempC >= currentEdge.destination.capacity && currentEdge.dist < costV[i] && !visitedID.contains(currentEdge.destination.ID)) {
                        /* IF (capacity >= demand) AND (dist < expected_path_dist) AND (the destination hasn't been visited yet):
                                choose this path.
                        */
                        dispatched = true;
                        nextVertex = currentEdge.destination; // path to go
                        costV[i] = currentEdge.dist;  //update the path cost value the vertex holds

                    }
                }

                if (!dispatched && nextVertex.ID == 0) {
                    outString.append(" --X-> NOT DISPATCHED");
                    break;
                }

                visitedID.add(nextVertex.ID); //the nextVertex has been visited.
                outString.append(" --> ").append(nextVertex);

                //update the values
                dT += costV[i]; //update total distance travelled
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
