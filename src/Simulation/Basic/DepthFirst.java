package Simulation.Basic;

import mapComponent.Edge;
import mapComponent.Vertex;
import map.Map;
import map.Path;

import java.util.*;

public class DepthFirst {
    //the id is array
    private static Map G;
    private static int C;
    private static List<String> tree;
    private static HashMap<String, Path> pathMap;
    private static List<Path> pathList;
    private static List<String> route;
    private static double tourDistance = Double.MAX_VALUE;
    private static long  start,end,time;
    private static final long maxTime = 60;


    public static void run(Map G, int C) {
        //initiazlize everything
        pathMap = new HashMap<>();
        pathList = new ArrayList<>();
        route = new ArrayList<>();
        tree = new ArrayList<>();
        DepthFirst.G = G;
        DepthFirst.C = C;
        System.out.println("---DF Search---\n");
        start = System.currentTimeMillis();
        //System.out.println(result);
        generateTree(0, 0, "");
        search();


        //finding the best tour, the last in list of "route" is considered the lowest
        end = System.currentTimeMillis();
        for(int i=0;i< pathList.size() && time < maxTime ;i++){
            System.out.println("Current time in for loop : "+((end - start)/1000));

            bestTour(i,"","",0.0);

        }
        //printAllEdge();
        //printing the best tour
        for( String element : route){
            System.out.println(element);
        }
        String[] bestTour = route.get(route.size()-1).split(" ");
        System.out.println("Tour cost : "+tourDistance);
        for(int i=0;i< bestTour.length;i++){
            System.out.println("Vehicle "+(i+1));
            System.out.println(pathList.get(Integer.parseInt(bestTour[i])));
        }

        System.out.println("Execution time: " + (double) (end - start) * Math.pow(10, -6) + "ms\n");
    }

    //generate tree (limiting the capacity)
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
        if (vertexID != 0) {
            tree.add("0 " + currentList + "0");
        }

    }

    //search for the tree
    private static void search() {
        //putting the
        for (String element : tree) {

            double distance = 0;
            int capacity = 0;

            //Generate array of nodes
            String[] strArray = element.split(" ");
            Integer[] nodes = new Integer[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                nodes[i] = Integer.parseInt(strArray[i]);
                capacity += G.getVertex(nodes[i]).capacity;
            }

            //compute distance fore every path
            for (int i = 0; i < nodes.length - 1; i++) {
                distance += computeDistance(G.getVertex(nodes[i]), G.getVertex(nodes[i + 1]));
            }

            //use map to detect if visited nodes is same
            //example visited nodes 2 3 4 with 50 distance
            //vs visited nodes 3 2 4 with 40 distance
            //the second will replace the first one
            Integer[] nodesSort = nodes.clone();
            Arrays.sort(nodesSort);
            Path newPath = new Path(nodes, distance, capacity);
            Path path = pathMap.putIfAbsent(Arrays.toString(nodesSort), newPath);
            if (path != null) {
                if (path.getDistance() > distance) {
                    pathMap.replace(Arrays.toString(nodesSort), path, newPath);
                }
            }


        }
        //insert everything into list of path with ID
        int currentID = 0;
        for (java.util.Map.Entry<String, Path> entry : pathMap.entrySet()) {
            Path currentPath = entry.getValue();
            currentPath.setID(currentID);
            pathList.add(currentPath);
            currentID++;
        }

        for (Path path : pathList) {
            pathGraph(path);
        }

    }

    //calculate distance between two vertex
    private static double computeDistance(Vertex v1, Vertex v2) {
        double dx = v1.coordinateX - v2.coordinateX;
        double dy = v1.coordinateY - v2.coordinateY;

        return Math.sqrt((dx * dx) + (dy * dy));
    }


    private static boolean arrayPathChecker(Path from, Path dest) {
        Integer[] a = from.getNodes();
        Integer[] b = dest.getNodes();

        for (Integer integer : a) {
            if (integer.equals(0)) continue;
            for (Integer value : b) {
                if (integer.equals(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    //creating the graph ( edges for every path )
    private static void pathGraph(Path currentPath) {
        List<Path> edges = currentPath.getPathList();

        for (Path edgePath : pathList) {
            if (!currentPath.equals(edgePath) && arrayPathChecker(currentPath, edgePath)) {
                edges.add(edgePath);
            }
        }
    }

    //finding the bset tour
    private static void bestTour(int pathID, String visitedPath,String visitedNodes,double distance) {
        end = System.currentTimeMillis();
        time = ((end - start)/1000);
        System.out.println("Current time : "+time);
        if(time < maxTime) {
            Path currentPath = pathList.get(pathID);
            visitedPath += pathID + " ";
            visitedNodes += Arrays.toString(currentPath.getNodes());
            distance += currentPath.getDistance();
            for (int i = 0; i < currentPath.getPathList().size(); i++) {
                Path nextPath = currentPath.getPathList().get(i);

                if (!haveIntegerInString(nextPath.getNodes(), visitedNodes)) {
                    bestTour(nextPath.getID(), visitedPath, visitedNodes, distance);
                }
            }
            if (StringContainAllNodes(visitedNodes)) {
                if (distance < tourDistance) {
                    tourDistance = distance;
                    route.add(visitedPath);
                }
            }

        }
    }

    private static boolean haveIntegerInString(Integer[] nodes, String visited) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == 0) {
                continue;
            }
            if (visited.contains(" " + nodes[i])) {
                return true;
            }

        }

        return false;
    }
    private static boolean StringContainAllNodes(String visited){
        for(int i=0;i<G.size();i++){
            if(!visited.contains(" "+i)) {
                return false;
            }

        }
        return true;
    }
    private static void printAllEdge(){
        for(int i=0;i<pathList.size();i++){
            Path current = pathList.get(i);
            System.out.print("Current "+current);
            List<Path> currentEdge = current.getPathList();
            for(int j=0;j< currentEdge.size();j++){
                System.out.print("Edge "+j+" "+currentEdge.get(j));
            }
            System.out.println();
        }

        System.out.println("----------------");
    }
}
