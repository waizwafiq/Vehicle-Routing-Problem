import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

import GraphComponent.Vertex;
import Simulation.*;
import map.Graph;

public class Main {
    private static final String path = "D:\\_a_Lecture Notes FSKTM\\_Semester 2\\_WIA1002_DataStructures\\AlwaysOnTime-Delivery\\src\\example.txt";
    private static int N, C;
    private static Graph map = new Graph();

    public static void main(String[] args) {
        readInputFile();

        map.printConnections();
        System.out.println("\n\n\n");
        //Dijkstra.run(map, C); //duplicate to get the best exec. time
        Dijkstra.run(map, C);
        A_star.run(map, C);
        BestFirst.run(map, C);
        BestPath.run(map, C);
        BestPath_v2.run(map, C);

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
}
