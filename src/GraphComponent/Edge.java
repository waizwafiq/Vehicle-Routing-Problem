package GraphComponent;

public class Edge implements Comparable<Edge> {
    public Vertex destination; //holds destination vertex (toVertex)
    public double dist;

    public Edge(Vertex destination, double dist) {
        this.destination = destination;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "|-> " + destination + "d=" + dist + "|| h=" + destination.capacity;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.dist, o.dist);
    }
}
