package Simulation.Greedy;

import map.Graph;
import GraphComponent.*;

import java.util.ArrayList;
import java.util.Arrays;

//A* Traversal Algorithm
public class A_star {

    private static Graph G;
    private static int C;
    private static double tourCost;

    public static void run(Graph G, int C) {
        tourCost = 0; //just in case if we want to do multiple A* searches (to reset)
        A_star.G = G;
        A_star.C = C;
        System.out.println("---A* Search---\n");
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
                    Update the total distance travelled by the vehicle. (dT = dV - chosen_demand)
                    Deduct the capacity (send the package)
                    Go to the chosen vertex using the chosen path.
        */
        //array of expected edge cost selected by each vertex, (initially +inf to get the minimum)
        double[] costV = new double[G.size()];
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot
        StringBuilder outString = new StringBuilder();

        int vehicleCount = 0;
        while (visitedID.size() != costV.length - 1) {
            //while all vertices haven't been visited
            outString.append("Vehicle ").append(++vehicleCount).append("\n"); //EACH LOOP REPRESENTS ONE DELIVERY VEHICLE

            double dT = 0; //the total distance travelled
            Arrays.fill(costV, Double.POSITIVE_INFINITY);

            Vertex currentVertex = G.getHead();
            Vertex nextVertex = G.getHead();
            int tempC = C; //to deduct the capacity in vehicle whenever a vertex is visited

            outString.append(currentVertex);
            for (int i = 0; i < G.size(); i++) {
                //go through every vertices in the graph

                int tempCap = 0; //holds temp capacity to subtract at dT
                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    //go through every edges connected to current vertex
                    Edge currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge

                    if (tempC >= currentEdge.destination.capacity && dT + currentEdge.dist + currentEdge.destination.capacity < costV[i] && !visitedID.contains(currentEdge.destination.ID)) {
                        /* IF (capacity >= demand) AND (dT + dist + capacity < expected_path_cost) AND (the destination hasn't been visited yet):
                                choose this path.
                        */
                        nextVertex = currentEdge.destination; // path to go
                        costV[i] = dT + currentEdge.dist + currentEdge.destination.capacity;  //update the path cost value the vertex holds
                        tempCap = currentEdge.destination.capacity;
                    }
                }
                visitedID.add(nextVertex.ID); //the nextVertex has been visited.
                outString.append(" --> ").append(nextVertex);

                //update the values
                dT = costV[i] - tempCap; //update total distance travelled
                tempC -= nextVertex.capacity; //deduct capacity
                currentVertex = nextVertex;

                if (currentVertex.ID == 0)
                    //if the vehicle returns to the depot, break the loop/go to the next vehicle
                    break;
            }
            visitedID.remove((Integer) 0); //used Integer to make it as an object
            outString.append("\nCapacity: ").append(C - tempC);
            outString.append("\nCost: ").append(dT).append("\n");
            tourCost += dT;
        }
        return outString.toString();
    }
}