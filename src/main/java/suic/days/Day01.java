package suic.days;

import suic.Puzzle;
import suic.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Day01 implements Puzzle<Integer> {

    private List<Rotation> rotations;

    @Override
    public void parse() {
        rotations = FileUtils.readAsStream(getClass().getSimpleName() + "Input.txt")
                .map(Rotation::fromLine)
                .toList();
    }

    @Override
    public Integer part1() {
        int pos = 50;
        int hits = 0;
        for (Rotation rotation : rotations) {
            int offset = switch (rotation.direction) {
                case LEFT -> -rotation.value;
                case RIGHT -> rotation.value;
            };
            pos = floorMod(pos + offset, 100);
            if (pos == 0) {
                hits++;
            }
        }

        return hits;
    }

    @Override
    public Integer part2() {
        int pos = 50;
        int hits = 0;
        for (Rotation rotation : rotations) {
            int offset = switch (rotation.direction) {
                case LEFT -> -rotation.value;
                case RIGHT -> rotation.value;
            };
            if (offset > 0) {
                hits += Math.floorDiv(pos + offset, 100) - Math.floorDiv(pos, 100);
            } else if (offset < 0) {
                hits += Math.floorDiv(pos - 1, 100) - Math.floorDiv(pos + offset - 1, 100);
            }
            pos += offset;
        }
        return hits;
    }

    private record Rotation(Direction direction, int value) {

        public static Rotation fromLine(String line) {
            return new Rotation(
                    line.charAt(0) == 'L' ? Direction.LEFT : Direction.RIGHT,
                    Integer.parseInt(line.substring(1))
            );
        }

    }

    private enum Direction {
        LEFT, RIGHT
    }

}
