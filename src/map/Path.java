package map;

import java.util.Arrays;

public class Path {
    private Integer[] nodes;
    private double distance;
    private int capacity;

    public Path(Integer[] nodes, double distance, int capacity) {
        this.nodes = nodes;
        this.distance = distance;
        this.capacity = capacity;
    }

    public double getDistance() {
        return distance;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes) + "|d = "+distance+" |c = "+capacity;
    }
}
