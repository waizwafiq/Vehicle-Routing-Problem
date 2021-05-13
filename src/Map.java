public class Map {
    /*
    Graph : Map.java
    Loc: Location.java
    Edge  : Path.java
     */

    private Location<Integer> depot;
    private int size;

    public Map() {
    }

    public int getSize() {
        //returns the number of locations in the map
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

    public boolean addLoc(double x, double y, int demand, int ID_num) {
        if (!hasLocation(ID_num)) {
            Location<Integer> currentLoc = depot; // starting from head location

            Location<Integer> newLoc = new Location<>(x, y, demand, ID_num);

            if (depot == null) {
                // if the map is empty, the new location is the head of the map
                depot = newLoc;
            } else {
                // if the map is not empty
                while (currentLoc.nextLoc != null) // traverse until currentLoc is the final location
                    currentLoc = currentLoc.nextLoc;

                currentLoc.nextLoc = newLoc; // add the new location next to the final location
                // the new location is going to be the new final location
            }
            size++; // increase the number of locations in the map
            return true;
        } else
            return false;
    }

    public boolean addEdge(int sourceID, int destinationID, double weight) {
        if (depot == null)
            //if the map is empty OR the source and destination locations don't exist
            return false;

        Location<Integer> sourceLoc = depot;

        while (sourceLoc != null) {
            if (sourceLoc.ID.compareTo(sourceID) == 0) {
                // traverse until it reaches the source location
                // search for destination location
                Location<Integer> destinationLoc = depot;

                while (destinationLoc != null) {
                    if (destinationLoc.ID.compareTo(destinationID) == 0) {
                        // traverse until destination location
                        Path currentEdge = sourceLoc.firstPath;
                        Path newEdge = new Path(destinationLoc, weight, currentEdge);

                        //add the new path connected from the source location to dest loc
                        sourceLoc.firstPath = newEdge;
                        sourceLoc.deg++;
                        destinationLoc.deg++;
                        return true;
                    }

                    destinationLoc = destinationLoc.nextLoc;
                }
            }

            sourceLoc = sourceLoc.nextLoc;
        }
        return false;
    }

    public void unvisitAll() {
        Location<Integer> currentLoc = depot;

        while (currentLoc != null) {
            currentLoc.unvisit();
            currentLoc = currentLoc.nextLoc;
        }
    }

    public void completeConnect() {
        Location<Integer> v1 = depot.visit(); //starting from the depot
        //NOTE: visit() is to avoid loop (location connects to itself)

        while (v1 != null) {
            Location<Integer> v2 = depot; //starting from the depot

            while (v2 != null) {
                if (v2.isVisited()) {
                    //if the location has been visited, go to the next location
                    v2 = v2.nextLoc;
                    continue;
                }
                //add the path here
                //addUnweightedEdge() is not used here because of multiple paths connections
                addEdge(v1.ID, v2.ID, dist(v1, v2)); //put weight here?
                v2 = v2.nextLoc; //go to the next location
            }
            unvisitAll(); //reset the visited variable
            if (v1.nextLoc == null) //to avoid NullPointerException
                break;

            v1 = v1.nextLoc.visit(); //visit the next variable
        }
    }

    private double dist(Location<Integer> v1, Location<Integer> v2) {
        double dX = v1.getX() - v2.getX();
        double dY = v1.getY() - v2.getY();
        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
    }

    public void printMap() {
        Location<Integer> currentLoc = depot;
        String dataLoc = "";

        while (currentLoc != null) {
            System.out.print("# " + currentLoc.ID + " : ");
            Path currentPath = currentLoc.firstPath;

            dataLoc += currentLoc.toString() + "\n";
            while (currentPath != null) {
                // go through all the paths from current location
                System.out.print("[" + currentLoc.ID + " --" + currentPath.weight + "--> " + currentPath.toLoc.ID + "]");

                currentPath = currentPath.nextPath;
            }
            System.out.println();

            currentLoc = currentLoc.nextLoc;
        }
        System.out.println(dataLoc);
    }
}
