public class Path extends Map {
    protected Location<Integer> toLoc;
    protected double weight;
    protected Path nextPath;

    public Path() {

    }

    public Path(Location<Integer> toLoc, double weight, Path nextPath) {
        this.toLoc = toLoc;
        this.weight = weight;
        this.nextPath = nextPath;
    }

    //GETTERS
    public Location<Integer> getToVertex() {
        return toLoc;
    }

    public double getWeight() {
        return weight;
    }

    public Path getNextEdge() {
        return nextPath;
    }

    //SETTERS
    public void setToVertex(Location<Integer> toLoc) {
        this.toLoc = toLoc;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setNextEdge(Path nextPath) {
        this.nextPath = nextPath;
    }
}
