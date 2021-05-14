package GraphComponent;

public class Edge {
    Vertex destination; //holds destination vertex (toVertex)
    double dist;

    public Edge(Vertex destination, double dist) {
        this.destination = destination;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "|->" + destination + "d=" + dist;
    }
}
