package suic.days;

import suic.Puzzle;
import suic.util.FileUtils;

import java.util.List;

public class Day01 implements Puzzle<Integer> {


    @Override
    public void parse() {
        List<String> input = FileUtils.read(getClass().getSimpleName() + "Input.txt");
        System.out.println(input);
    }

    @Override
    public Integer part1() {
        return 0;
    }

    @Override
    public Integer part2() {
        return 0;
    }
}
