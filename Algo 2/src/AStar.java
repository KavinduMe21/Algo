import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

//    public static void findShortestPath(Cell[][] cells, Cell start, Cell destination) {
//        PriorityQueue<Cell> openCells = new PriorityQueue<>((c1, c2) ->
//                Integer.compare(c1.heuristicCost + c1.actualCost, c2.heuristicCost + c2.actualCost));
//
//        start.actualCost = 0;
//        openCells.add(start);
//
//        while (!openCells.isEmpty()) {
//            Cell current = openCells.poll();
//
//            if (current == destination) {
//                printPath(current);
//                break;
//            }
//
//            for (int[] dir : directions) {
//                int newRow = current.row + dir[0];
//                int newCol = current.col + dir[1];
//
//                if (isValid(newRow, newCol, cells.length, cells[0].length)) {
//                    Cell neighbor = cells[newRow][newCol];
//
//                    if (!neighbor.obstacle) {
//                        int newCost = current.actualCost + 1;
//                        if (newCost < neighbor.actualCost) {
//                            neighbor.actualCost = newCost;
//                            neighbor.heuristicCost = calculateHeuristic(neighbor, destination);
//                            neighbor.parent = current;
//                            openCells.add(neighbor);
//                        }
//                    }
//                }
//            }
//        }
//    }
        public static void findShortestPath(Cell[][] cells, Cell start, Cell destination) {
            PriorityQueue<Cell> openCells = new PriorityQueue<>((c1, c2) ->
                    Integer.compare(c1.heuristicCost + c1.actualCost, c2.heuristicCost + c2.actualCost));

            start.actualCost = 0;
            openCells.add(start);

            while (!openCells.isEmpty()) {
                Cell current = openCells.poll();

                // Continue sliding until the destination is reached
                if (cells[current.row][current.col] == destination) {
                    printPath(current);
                    break;
                }

                // Iterate over the neighboring cells to find the direction to slide
                for (int[] dir : directions) {
                    int newRow = current.row;
                    int newCol = current.col;

                    // Slide in the direction until an obstacle or the boundary is encountered
                    while (isValid(newRow + dir[0], newCol + dir[1], cells.length, cells[0].length)
                            && !cells[newRow + dir[0]][newCol + dir[1]].obstacle) {
                        newRow += dir[0];
                        newCol += dir[1];
                    }

                    // Check if sliding in this direction reduces the cost
                    Cell neighbor = cells[newRow][newCol];
                    int newCost = current.actualCost + Math.abs(newRow - current.row) + Math.abs(newCol - current.col);
                    if (newCost < neighbor.actualCost) {
                        neighbor.actualCost = newCost;
                        neighbor.heuristicCost = calculateHeuristic(neighbor, destination);
                        neighbor.parent = current;
                        openCells.add(neighbor);
                    }
                }
            }
        }

    private static boolean isValid(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    private static int calculateHeuristic(Cell cell, Cell destination) {
        return Math.abs(cell.row - destination.row) + Math.abs(cell.col - destination.col);
    }

    private static void printPath(Cell destination) {
        List<String> path = new ArrayList<>();
        Cell current = destination;
        Cell prev = null; // Keep track of the previous cell
        while (current != null) {
            if (prev != null) {
                String move = getMove(prev, current); // Get the movement direction
                path.add(move);
            }
//            path.add("Move to (" + (current.row + 1) + "," + (current.col + 1) + ")");
            prev = current;
            current = current.parent;
        }
        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.println((i + 1) + ". " + path.get(i));
        }
    }


    private static String getMove(Cell prev, Cell current) {
        int rowDiff = current.row -  prev.row;
        int colDiff = current.col - prev.col;
        if (prev.row < current.row) {
            return "Move left" + "("+current.row + "," + current.col + ")";
//            return "Move left" + "("+current.row + "," + current.col + ")";
        } else if (prev.row > current.row) {
            return "Move right" + "("+current.row + "," + current.col + ")";
//            return "Move left" + "("+current.row + "," + current.col + ")";
        } else if (prev.col < current.col) {
            return "Move up" + "("+current.row + "," + current.col + ")";
//            return "Move left" + "("+current.row + "," + current.col + ")";
        } else if (prev.col > current.col) {
            return "Move down" + "("+current.row + "," + current.col + ")";
//            return "Move left" + "("+current.row + "," + current.col + ")";
        }
        return ""; // No movement
    }
}
