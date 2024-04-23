import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Define the file path
        String filePath = "src/resource/maze30_5.txt"; // Change this to the path of your text file

        // Read the maze from the text file
        char[][] maze = readMazeFromFile(filePath);
        // Display the maze
        displayMaze(maze);

        // Initialize cells
        Cell[][] cells = new Cell[maze.length][maze[0].length];
        Cell start = null;
        Cell destination = null;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                cells[i][j] = new Cell(i, j, maze[i][j] == '0');
                if (maze[i][j] == 'S') start = cells[i][j];
                if (maze[i][j] == 'F') destination = cells[i][j];
            }
        }

        // Find shortest path
        AStar.findShortestPath(cells, start, destination);
    }

    private static char[][] readMazeFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the maze content from the file
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Determine the number of rows and columns
            int numRows = lines.size();
            int numCols = lines.get(0).length();

            // Create the maze array
            char[][] maze = new char[numRows][numCols];

            // Fill the maze array with the content from the file
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    maze[i][j] = lines.get(i).charAt(j);
                }
            }

            return maze;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void displayMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
