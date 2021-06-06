import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

import GraphComponent.Vertex;
import Simulation.Basic.*;
import Simulation.Greedy.*;
import map.Graph;


public class Main {
    private static final String path = "Sample\\Sample3.txt";
    private static int N, C;
    private static final Graph map = new Graph();

    public static void main(String[] args) {
        readInputFile();
        map.printConnections();
        DepthFirst.run(map,C);
//        BlindDFS.run(map, N, C);
//        Dijkstra.run(map, C);
//        A_star.run(map, C);
//        BestFirst.run(map, C);
//        BestPath.run(map, C);
//        BestPath_v2.run(map, C);
        // GreedySearch.run(map, C);

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

    public static void readInputFile() {

        try {
            Scanner inText = new Scanner(new FileInputStream(path));

            String[] firstLine = inText.nextLine().split(" ");
            N = Integer.parseInt(firstLine[0]); //N represents the number of locations in the text file
            C = Integer.parseInt(firstLine[1]);

            for (int i = 0; i < N; i++) {
                String[] line_i = inText.nextLine().split(" ");
                Vertex temp = new Vertex(Double.parseDouble(line_i[0]), Double.parseDouble(line_i[1]), Integer.parseInt(line_i[2]), i);
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