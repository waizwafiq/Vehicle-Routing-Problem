package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path implements Comparable<Path> {
    private Integer[] nodes;
    private double distance;
    private int capacity;
    private List<Path> pathList;
    private int ID;
    public Path(Integer[] nodes, double distance, int capacity) {
        this.ID = 0;
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
        String str = "";
        for(int i=0;i<nodes.length;i++){
            str+="{ID "+nodes[i]+"}";
            if(i!=nodes.length-1) str+= " --> ";
        }

        return str+"\nCapacity : "+capacity+"\nCost : "+distance;
    }

    public Integer[] getNodes() {
        return nodes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int compareTo(Path o) {

        return Integer.compare(this.nodes.length,o.nodes.length);
    }
}
