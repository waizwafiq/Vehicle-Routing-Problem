package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path implements Comparable<Path> {
    private Integer[] nodes;
    private double distance;
    private int capacity;
    private List<Path> pathList;

    public Path(Integer[] nodes, double distance, int capacity) {
        this.nodes = nodes;
        this.distance = distance;
        this.capacity = capacity;
        this.pathList = new ArrayList<>();
    }

    public List<Path> getPathList() {
        return pathList;
    }

    public double getDistance() {
        return distance;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes) + " |d = "+distance+" |c = "+capacity;
    }


    @Override
    public int compareTo(Path o) {

        return Integer.compare(this.nodes.length,o.nodes.length);
    }
}
