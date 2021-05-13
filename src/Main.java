import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    private static int N, C;
    private static final String path = "D:\\_a_Lecture Notes FSKTM\\_Semester 2\\_WIA1002_DataStructures\\Projects\\src\\example.txt";
    private static Location[] loc;

    public static void main(String[] args) {
        readInputFile();
        for (Location location : loc){
            System.out.print(location.getID()+" ");
            System.out.print(location.getX_coord()+" ");
            System.out.print(location.getY_coord()+" ");
            System.out.println(location.getDemand_size()+" ");
        }
    }

    public static void readInputFile() {

        try {
            Scanner inText = new Scanner(new FileInputStream(path));

            String[] firstLine = inText.nextLine().split(" ");
            N = Integer.parseInt(firstLine[0]);
            C = Integer.parseInt(firstLine[1]);
            loc = new Location[N]; //N represents the number of locations in the text file

            for (int i = 0; i < N; i++) {
                String[] line_i = inText.nextLine().split(" ");
                loc[i] = new Location(Double.parseDouble(line_i[0]), Double.parseDouble(line_i[1]), Integer.parseInt(line_i[2]), i);
            }
            inText.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
