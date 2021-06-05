package Simulation.Basic;

import GraphComponent.Edge;
import GraphComponent.Vertex;
import map.Graph;
import map.Path;

import java.util.*;

public class DepthFirst {
    //the id is array
    private static Graph G;
    private static int C;
    private static List<String> tree;
    private static HashMap<String, Path> pathMap;
    private static List<Path> pathList;
    private static List<String> route;


    public static void run(Graph G, int C) {
        //initiazlize everything
        pathMap = new HashMap<>();
        pathList = new ArrayList<>();
        route = new ArrayList<>();
        double tourCost = 0;
        DepthFirst.G = G;
        DepthFirst.C = C;
        System.out.println("---DF Search---\n");
        long start = System.nanoTime();
//        String result = search(G.getHead());

        System.out.println("Tour Cost: " + tourCost);
        //System.out.println(result);

        tree = new ArrayList<>();
        generateTree(0, 0, "");
        search();
        //Collections.sort(pathList);
        for (Object element : pathList) {
            System.out.println(element);
        }
        printAllEdge();

        for(int i=0;i< pathList.size();i++){
            bestTour(i,"","");
        }

        for (Object element : route) {
            System.out.println(element);
        }

        System.out.println(pathList.size());
        // System.out.println(pathMap);
        long end = System.nanoTime();
        System.out.println("Execution time: " + (double) (end - start) * Math.pow(10, -6) + "ms\n");
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
        if (vertexID != 0) {
            tree.add("0 " + currentList + "0");
        }

    }

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
        for (Map.Entry<String, Path> entry : pathMap.entrySet()) {
            Path currentPath = entry.getValue();
            currentPath.setID(currentID);
            pathList.add(currentPath);
            currentID++;
        }

        for (Path path : pathList) {
            pathGraph(path);
        }

    }

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

    private static void pathGraph(Path currentPath) {
        List<Path> edges = currentPath.getPathList();

        for (Path edgePath : pathList) {
            if (!currentPath.equals(edgePath) && arrayPathChecker(currentPath, edgePath)) {
                edges.add(edgePath);
            }
        }
    }

    private static void bestTour(int pathID, String visitedPath,String visitedNodes) {
        Path currentPath = pathList.get(pathID);
        visitedPath+= pathID+" ";
        visitedNodes+= Arrays.toString(currentPath.getNodes());
        for(int i=0;i< currentPath.getPathList().size();i++){
            Path nextPath = currentPath.getPathList().get(i);

            if(!haveIntegerInString(nextPath.getNodes(), visitedNodes)){
                bestTour(nextPath.getID(), visitedPath,visitedNodes);
            }
        }
        if(StringContainAllNodes(visitedNodes)) {
            route.add(visitedPath);
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
    }
}
