import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

import Simulation.Greedy.*;
import mapComponent.Vertex;
import Simulation.Basic.*;
import map.Map;


public class Main {
    private static final String path = "Sample\\Sample1.txt";
    private static int N, C;
    private static final Map map = new Map();

    public static void main(String[] args) {
        readInputFile(false);
        map.printConnections();
        int lorries = 3;

//        //DepthFirst.run(map,C);
//        System.out.println("\nC = " + C);
        Dijkstra.run(map, C, lorries);
//        BlindDFS.run(map, N, C, lorries);
//        Dijkstra.run(map, C, lorries);
//        A_star.run(map, C, lorries);
//        BestFirst.run(map, C, lorries);
//        BestPath.run(map, C, lorries);
//        BestPath_v2.run(map, C, lorries);
//        GreedySearch.run(map, C, lorries);

        /*
        progressBar("Printing tree... ", 3);
        map.printConnections();

        System.out.println("\n");
        progressBar("Printing tree... ", 3);
        Tree.run(map, C); // generate Tree

        //Dijkstra.run(map, C); //duplicate to get the best exec. time
        progressBar("Searching using Greedy Algorithms... ", 7);
        Dijkstra.run(map, C);
        A_star.run(map, C);
        BestFirst.run(map, C);
        BestPath.run(map, C);
        BestPath_v2.run(map, C);
        GreedySearch.run(map, C);
        //DepthFirst.run(map, C);
        */
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
                The narrow site will be in the area of a circle with the radius of r and centered at (x_c, y_c)
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