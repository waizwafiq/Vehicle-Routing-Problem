import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    private static int N, C;
    private static final String path = "example.txt";
    private static Location<Integer>[] loc;

    public static void main(String[] args) {
        readInputFile();
        for (Location<Integer> location : loc) {
            System.out.println(location.toString());
        }
    }

    public static void readInputFile() {

        try {
            Scanner inText = new Scanner(new FileInputStream(path));

            String[] firstLine = inText.nextLine().split(" ");
            N = Integer.parseInt(firstLine[0]);
            C = Integer.parseInt(firstLine[1]);
            loc = (Location<Integer>[]) new Location[N]; //N represents the number of locations in the text file

            for (int i = 0; i < N; i++) {
                String[] line_i = inText.nextLine().split(" ");
                loc[i] = new Location<Integer>(Double.parseDouble(line_i[0]), Double.parseDouble(line_i[1]), Integer.parseInt(line_i[2]), i);
            }
            inText.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
