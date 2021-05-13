public class Map {
    /*
    Graph : Map.java
    Vertex: Location.java
    Edge  : Path.java
     */

    private Location<Integer> depot;
    private int size;

    public Map() {
    }

    public int getSize() {
        //returns the number of locations in the graph
        return size;
    }

    public int getDepot() {
        return depot.ID;
    }

    public Location<Integer> getDepotLocation() {
        return depot;
    }

    public boolean hasLocation(int ID_Num) {
        //check if the location exists (given the ID number)
        if (depot == null) //if the map is empty
            return false;

        Location<Integer> currentLocation = depot; //starting from the depot
        while (currentLocation != null) {
            //go through the locations available
            if (currentLocation.ID.compareTo(ID_Num) == 0) //if found location from ID number
                return true;

            currentLocation = currentLocation.nextLoc; //go to the next location
        }

        return false; //if not found
    }

    public boolean hasPath(int sourceID, int destinationID) {
        if (depot == null)
            //if no depot
            return false;

        Location<Integer> sourceLocation = depot; //starting from the depot

        while (sourceLocation != null) {
            //go through all available locations on the map
            if (sourceLocation.ID.compareTo(sourceID) == 0) {
                //find the source location (from given ID)
                Path currentPath = sourceLocation.firstPath;

                while (currentPath != null) {
                    //go through all path possible from the source
                    if (currentPath.toLoc.ID.compareTo(destinationID) == 0)
                        //find the path to destination location (from given ID)
                        return true; //if found

                    currentPath = currentPath.nextPath;
                }
            }

            sourceLocation = sourceLocation.nextLoc;
        }

        return false; //if not found
    }

    public int getDeg(int ID_num) {
        Location<Integer> currentLoc = depot; // starting from the depot

        while (currentLoc != null) {
            //go through all available locations
            if (currentLoc.ID.compareTo(ID_num) == 0)
                return currentLoc.deg; // if the location is found, return the degree value;

            currentLoc = currentLoc.nextLoc;
        }

        return -1; // not found
    }
}
