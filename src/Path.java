public class Path {
    private Location toVertex;
    private double weight;
    private Path nextEdge;

    public Path() {

    }

    public Path(Location toVertex, double weight, Path nextEdge) {
        this.toVertex = toVertex;
        this.weight = weight;
        this.nextEdge = nextEdge;
    }

    //GETTERS
    public Location getToVertex() {
        return toVertex;
    }

    public double getWeight() {
        return weight;
    }

    public Path getNextEdge() {
        return nextEdge;
    }

    //SETTERS
    public void setToVertex(Location toVertex) {
        this.toVertex = toVertex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setNextEdge(Path nextEdge) {
        this.nextEdge = nextEdge;
    }
}
