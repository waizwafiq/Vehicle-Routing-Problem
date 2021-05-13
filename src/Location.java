public class Location {
    private double x_coord, y_coord;
    private int demand_size, ID;

    public Location(double x_coord, double y_coord, int demand_size, int ID) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.demand_size = demand_size;
        this.ID = ID;
    }

    public double getX_coord() {
        return x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public int getDemand_size() {
        return demand_size;
    }

    public int getID() {
        return ID;
    }
}
