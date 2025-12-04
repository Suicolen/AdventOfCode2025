package suic.days;

import suic.Puzzle;
import suic.util.FileUtils;
import suic.util.grid.CharGrid;
import suic.util.grid.GridPosition;
import suic.util.grid.GridUtils;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements Puzzle<Integer> {

    private static final char PAPER = '@';
    private static final char EMPTY = '.';

    private CharGrid grid;

    @Override
    public void parse() {
        List<String> lines = FileUtils.read(getClass().getSimpleName() + "Input.txt");
        grid = GridUtils.parseCharGrid(lines);
    }

    @Override
    public Integer part1() {
        return findAccessiblePapers().size();
    }

    @Override
    public Integer part2() {
        int rows = grid.getRows();
        int cols = grid.getColumns();
        int[][] adjacentCount = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid.get(r, c) == PAPER) {
                    adjacentCount[r][c] = grid.countAdjacent(r, c, PAPER);
                }
            }
        }

        int removals = 0;
        while (true) {
            List<GridPosition> accessible = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid.get(r, c) == PAPER && adjacentCount[r][c] < 4) {
                        accessible.add(new GridPosition(r, c));
                    }
                }
            }

            if (accessible.isEmpty()) {
                break;
            }

            for (GridPosition pos : accessible) {
                int row = pos.row();
                int col = pos.col();

                grid.set(row, col, EMPTY);
                removals++;

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx == 0 && dy == 0) {
                            continue;
                        }

                        int r = row + dx;
                        int c = col + dy;

                        if (!grid.inBounds(r, c)) {
                            continue;
                        }

                        if (grid.get(r, c) == PAPER) {
                            adjacentCount[r][c]--;
                        }
                    }
                }
            }
        }

        return removals;
    }


    private List<GridPosition> findAccessiblePapers() {
        List<GridPosition> accessible = new ArrayList<>();
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getColumns(); c++) {
                if (isAccessiblePaper(r, c)) {
                    accessible.add(new GridPosition(r, c));
                }
            }
        }
        return accessible;
    }

    private boolean isAccessiblePaper(int row, int col) {
        return grid.get(row, col) == PAPER && grid.countAdjacent(row, col, PAPER) < 4;
    }
}
