package suic.util.grid;

import lombok.Getter;

public class CharGrid {

    private final char[][] grid;

    @Getter
    private final int rows;

    @Getter
    private final int columns;

    public CharGrid(char[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        this.grid = grid;
    }

    public CharGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new char[rows][columns];
    }

    public char get(int row, int column) {
        return grid[row][column];
    }

    public void set(int row, int column, char value) {
        grid[row][column] = value;
    }

    public char get(GridPosition pos) {
        return grid[pos.row()][pos.col()];
    }

    public void set(GridPosition pos, char value) {
        grid[pos.row()][pos.col()] = value;
    }

    public boolean inBounds(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean inBounds(GridPosition pos) {
        return inBounds(pos.row(), pos.col());
    }

    public CharGrid copy() {
        char[][] copy = new char[rows][columns];
        for (int r = 0; r < rows; r++) {
            System.arraycopy(grid[r], 0, copy[r], 0, columns);
        }
        return new CharGrid(copy);
    }

    public int countAdjacent(int row, int col, char target) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                int r = row + dx;
                int c = col + dy;

                if (inBounds(r, c) && grid[r][c] == target) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countAdjacent(GridPosition pos, char target) {
        return countAdjacent(pos.row(), pos.col(), target);
    }

}
