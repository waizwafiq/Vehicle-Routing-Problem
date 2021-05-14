import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    private static int N, C;
    private static final String path = "C:\\Users\\Try\\Desktop\\AlwaysOnTime-Delivery\\src\\example.txt";
    private static final Map map = new Map();

    public static void main(String[] args) {
        readInputFile();
        map.printMap();
    }

    public static void readInputFile() {

        try {
            Scanner inText = new Scanner(new FileInputStream(path));

            String[] firstLine = inText.nextLine().split(" ");
            N = Integer.parseInt(firstLine[0]); //N represents the number of locations in the text file
            C = Integer.parseInt(firstLine[1]);

            for (int i = 0; i < N; i++) {
                String[] line_i = inText.nextLine().split(" ");
                map.addLoc(Double.parseDouble(line_i[0]), Double.parseDouble(line_i[1]), Integer.parseInt(line_i[2]), i);
            }
            map.completeConnect();
            inText.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
