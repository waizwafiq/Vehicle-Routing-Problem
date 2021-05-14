package GraphComponent;

public class Edge {
    Vertex destination;
    double distance;

    public Edge(Vertex destination, double distance) {
        this.destination = destination;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "->" + destination + "d=" + distance;
    }
}
