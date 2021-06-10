package mapComponent;


import java.util.ArrayList;
import java.util.List;

public class Tour {
    private double totalDistance;
    private List<List<Vertex>> route;

    public Tour() {
        this.totalDistance = 0;
        route = new ArrayList<>();
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<List<Vertex>> getRoute() {
        return route;
    }

    public void setRoute(List<List<Vertex>> route) {
        this.route = route;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tour\n");

        for (int i = 0; i < route.size(); i++) {
            sb.append("Vehicle ").append(i + 1).append("\n");
            sb.append(route.get(i)).append("\n");
            sb.append("Cost: ").append(distanceRoute(route.get(i))).append("\n");
        }

        return sb.toString();
    }

    double distanceRoute(List<Vertex> vertexList) {
        double distance = 0;
        for (int i = 0; i < vertexList.size() - 1; i++) {
            Vertex current = vertexList.get(i);
            Vertex next = vertexList.get(i + 1);
            double dx = current.coordinateX - next.coordinateX;
            double dy = current.coordinateY - next.coordinateY;
            distance += Math.sqrt(dx * dx + dy * dy);
        }
        return distance;
    }
}
