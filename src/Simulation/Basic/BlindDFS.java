package Simulation.Basic;

import mapComponent.Edge;
import mapComponent.Vertex;
import map.Map;

import java.util.ArrayList;

public class BlindDFS {

    private static Map G;
    private static int N, C, lorries;
    private static double tourCost;

    public static void run(Map G, int N, int C, int numberOfLorries) {
        BlindDFS.lorries = numberOfLorries;
        BlindDFS.G = G;
        BlindDFS.N = N;
        BlindDFS.C = C;

        System.out.println("---Blind DFS Search---\n");
        long start = System.nanoTime();
        String result = search();
        long end = System.nanoTime();

        System.out.println("Tour Cost: " + tourCost);
        System.out.println(result);
        System.out.println("Execution time: " + (double) (end - start) * Math.pow(10, -6) + "ms\n");
    }

    private static String search() {
        ArrayList<Integer> visitedID = new ArrayList<>(); //a list of visited vertices (based on ID) except depot
        StringBuilder outString = new StringBuilder();

        int vehicleCount = 0, lorryCount = 0;
        while (visitedID.size() != N - 1) {
            //while all vertices haven't been visited
            int tempC = 0;
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

            Vertex currentVertex = G.getHead();
            Vertex nextVertex = G.getHead();

            outString.append(currentVertex);
            int totalCap = 0;
            double tempD = 0;
            for (int i = 0; i < N; i++) {
                //go through every vertices in the graph
                for (int j = 0; j < currentVertex.EdgeList.size(); j++) {
                    //go through every edges connected to current vertex
                    Edge currentEdge = currentVertex.EdgeList.get(j); //starting from the first edge

                    if (tempC >= currentEdge.destination.capacity && !visitedID.contains(currentEdge.destination.ID)) {
                        /* IF (capacity >= demand) AND (the destination hasn't been visited yet):
                                choose this path.
                        */
                        nextVertex = currentEdge.destination; // path to go
                        tempD = currentEdge.dist;  //update the distance travelled
                        //break;
                    }
                }
                visitedID.add(nextVertex.ID); //the nextVertex has been visited.
                outString.append(" --> ").append(nextVertex);

                //update the values
                dT += tempD; //new total distance travelled
                tempC -= nextVertex.capacity; //deduct capacity
                totalCap += nextVertex.capacity;
                currentVertex = nextVertex;

                if (currentVertex.ID == 0)
                    //if the vehicle returns to the depot, break the loop/go to the next vehicle
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
