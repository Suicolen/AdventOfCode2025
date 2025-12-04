package suic.util.grid;

import java.util.List;

public final class GridUtils {

    public static CharGrid parseCharGrid(List<String> lines) {
        int rows = lines.size();
        int columns = lines.getFirst().length();
        CharGrid grid = new CharGrid(rows, columns);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                char value = lines.get(x).charAt(y);
                grid.set(x, y, value);
            }
        }
        return grid;
    }

    private GridUtils() {

    }

}