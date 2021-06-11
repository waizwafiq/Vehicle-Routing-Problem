import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import Simulation.Greedy.*;
import Simulation.MCTS.NRPA;
import mapComponent.Vertex;
import Simulation.Basic.*;
import map.Map;


public class Main {
    private static final String path = "Sample\\Sample2.txt";
    private static int N, C;
    private static final Map map = new Map();

    public static void main(String[] args) {
        readInputFile(false);
        progressBar("Printing map...", 1.2);
        int lorries = 0;
        System.out.println("Number of lorries = " + lorries + "\nC = " + C);

        map.printConnections();

        double startup_time;
        if (N - 1 <= 10)
            startup_time = 6;
        else
            startup_time = 60;

        final String[] concur = new String[2];
        try {
            Runnable r1 = () -> concur[0] = NRPA.run(map, N, C);
            Runnable r2 = () -> {
                if (N <= 20)
                    concur[1] = RevisedDFS.run(map, C);
            };
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();

            progressBar("Starting up simulations... ", startup_time);

            progressBar("Searching using Basic Simulations... ", 2);
            System.out.println(concur[1]);
            BlindDFS.run(map, N, C, lorries);

            progressBar("Searching using Greedy Simulation... ", 2);
            Dijkstra.run(map, C, lorries);
            A_star.run(map, C, lorries);
            BestFirst.run(map, C, lorries);
            BestPath.run(map, C, lorries);

            progressBar("Searching using MCTS Algorithm... ", 6);
            while (concur[0] == null)
                System.out.print("");

        } catch (ConcurrentModificationException e) {
            concur[0] = NRPA.run(map, N, C);
        }
        System.out.println(concur[0]);
    }

    public static void readInputFile(boolean site_dependent) {
        readInputFile(0, 0, 4, site_dependent);
    }

    public static void readInputFile(double x_c, double y_c, double radius) {
        readInputFile(x_c, y_c, radius, true);
    }

    private static void readInputFile(double x_c, double y_c, double r, boolean site_dependent) {

        try {
            Scanner inText = new Scanner(new FileInputStream(path));

            String[] firstLine = inText.nextLine().split(" ");
            N = Integer.parseInt(firstLine[0]); //N represents the number of locations in the text file
            C = Integer.parseInt(firstLine[1]);

            for (int i = 0; i < N; i++) {
                String[] line_i = inText.nextLine().split(" ");
                Vertex temp = new Vertex(Double.parseDouble(line_i[0]), Double.parseDouble(line_i[1]), Integer.parseInt(line_i[2]), i);
                /* IF SITE-DEPENDENT **CUSTOMERS** IS ON:
                The narrow site will be inside the area of a circle with the radius of r and centered at (x_c, y_c)
                If the customer is in the narrow site, then set the narrowArea boolean of the vertex to true.
                 */
                double dx = temp.coordinateX - x_c, dy = temp.coordinateY - y_c;
                temp.narrowArea = i != 0 && site_dependent && ((dx * dx) + (dy + dy) <= (r * r));

                map.addVertex(temp);
            }

            map.completeConnect(); //create a complete connection
            inText.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    private static void updateProgress(String text, double percent) {
        final int width = 50; // progress bar width in chars
        System.out.print("\r" + text + " [");
        int i = 0;
        for (; i <= (int) (percent / 100 * width); i++)
            System.out.print("=");

        for (; i < width; i++) {
            System.out.print(" ");
        }
        System.out.print("] " + percent + "%");
    }

    private static void progressBar(String text, double timeInSec) {
        //timeInSec isn't accurate
        double perInterval = timeInSec / 100;
        try {
            for (int i = 0; i <= 100; i += 1) {
                updateProgress(text, i);
                Thread.sleep((long) (perInterval * (int) Math.pow(10, 3)));
            }
        } catch (InterruptedException ignored) {
        }
        System.out.println();
    }
}