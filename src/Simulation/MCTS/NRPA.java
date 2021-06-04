package Simulation.MCTS;

import map.Graph;

import java.util.Arrays;

public class NRPA {
    private static Graph G;
    private static int N, C;
    private static double tourCost;

    private static int[][][] policy;

    public static void run(Graph G, int N, int C) {
        NRPA.G = G;
        NRPA.N = N;
        NRPA.C = C;
        int level = 3, iterations = 100, ALPHA = 1;

        NRPA.policy = new int[level][N][N];

        //FILL EACH ROW OF THE FIRST LEVEL WITH 0's
        for (int[][] tubes : policy)
            Arrays.stream(tubes).forEach(row -> Arrays.fill(row, 0));
    }

    private static int search(int level, int iterations) {

        if (level == 0)
            return rollout();

        int[][] globalPolicy = policy[level];

        for (int i = 0; i < iterations; i++){

        }
        return 0;
    }

    private static void adapt() {

    }

    private static int rollout() {
        return 0;
    }

}
