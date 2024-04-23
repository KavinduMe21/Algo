public class Cell {
    int row, col;
    boolean obstacle;
    int heuristicCost;
    int actualCost;
    Cell parent;

    public Cell(int row, int col, boolean obstacle) {
        this.row = row;
        this.col = col;
        this.obstacle = obstacle;
        this.heuristicCost = 0;
        this.actualCost = Integer.MAX_VALUE;
        this.parent = null;
    }
}
