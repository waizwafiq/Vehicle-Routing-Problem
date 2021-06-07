package Simulation.MCTS;

import map.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class NRPA {
    private static Map G;
    private static int N, C;
    private static double tourCost;

    private static ArrayList<Integer> checkedID;

    private static int[][][] policy;
    private static int[][] globalPolicy;

    public static void run(Map G, int N, int C) {
        NRPA.G = G;
        NRPA.N = N;
        NRPA.C = C;
        checkedID = new ArrayList<>();
        int level = 3, iterations = 100, ALPHA = 1;

        NRPA.policy = new int[level][N][N];

        //FILL EACH ROW OF THE FIRST LEVEL WITH 0's
        for (int[][] tubes : policy)
            Arrays.stream(tubes).forEach(row -> Arrays.fill(row, 0));
    }

    private static int search(int level, int iterations) {

        if (level == 0)
            return rollout();

        policy[level] = NRPA.globalPolicy;

        for (int i = 0; i < iterations; i++) {
            /*
            new_tour = search(level-1, iterations)
            if new_tour's cost < best_tour's cost:
                best_tour = new_tour
                adapt(best_tour, level)

            if processing_time exceed time limit:
                return best_tour
             */

        }
        globalPolicy = policy[level];
        return 0;
    }

    private static void adapt(/*a_tour, level*/) {
        /*
        for every route in a_tour
            for every stop in route
                policy[level][stop][next_stop] += ALPHA
                z = 0.0
                for every possible move that can be made by stop
                    if the move is not visited yet
                        z += Math.exp(globalPolicy[stop][move]);
                for every possible move that can be made by stop
                    if the move is not visited yet
                        policy[level][stop][move] -= ALPHA * (Math.exp(globalPolicy[stop][move]) / z)
                set stop as visited
         */


    }

    private static int rollout() {
        /*
        initialize new_tour with first route with first stop at 0  // every route must start and end at depot (ID=0)
        while true
            currentStop = new_tour last route last stop
            find every possible successors that is not yet checked for the currentStop
            if no successors is available
                currentRoute is completed and should return to depot
                if all stop are visited
                    break while loop  // rollout process is done
                add new route into new_tour  // else add new vehicle, again start at depot with ID 0
                continue  // skip to next loop to continue search a route for new vehicle
            nextStop = select_next_move(currentStop, possible_successors)
            if add nextStop to currentRoute does not violate any rules
                add nextStop to currentRoute
                set nextStop as visited
            else
                set nextStop as checked
        return new_tour
         */

        return 0;
    }

    private static int select_next_move(){
        /*
        initialize 1d probability array that have same size with possible_successors
        sum = 0
        for i=0 to size of possible_successors
            probability[i] = Math.exp(globalPolicy[currentStop][possible_successors[i]])
            sum += probability[i]
        mrand = new Random().nextDouble() * sum
        i = 0;
        sum = probability[0];
        while sum < mrand
            sum += probability[++i];
        return possible_successors[i]
         */

        return 0;
    }

}
