package suic.days;

import suic.Puzzle;
import suic.util.FileUtils;

import java.util.Arrays;
import java.util.List;

public class Day02 implements Puzzle<Long> {

    private List<Range> ranges;

    @Override
    public void parse() {
        ranges = FileUtils.readAsStream(getClass().getSimpleName() + "Input.txt")
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(Range::fromLine)
                .toList();

    }

    @Override
    public Long part1() {
        long sum = 0;
        for (Range range : ranges) {
            sum += range.sumInvalidPart1();
        }
        return sum;
    }

    @Override
    public Long part2() {
        long sum = 0;
        for (Range range : ranges) {
            sum += range.sumInvalidPart2();
        }
        return sum;
    }

    private record Range(long start, long end) {

        public static Range fromLine(String line) {
            String[] components = line.split("-");
            long start = Long.parseLong(components[0]);
            long end = Long.parseLong(components[1]);
            return new Range(start, end);
        }

        public long sumInvalidPart1() {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                if (isInvalidPart1(i)) {
                    sum += i;
                }
            }
            return sum;
        }

        public long sumInvalidPart2() {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                if (isInvalidPart2(i)) {
                    sum += i;
                }
            }
            return sum;
        }

        private boolean isInvalidPart1(long n) {
            String str = Long.toString(n);
            int len = str.length();
            if (len % 2 == 1) {
                return false;
            }
            String half = str.substring(0, len / 2);
            return half.repeat(2).equals(str);
        }

        private boolean isInvalidPart2(long n) {
            String str = Long.toString(n);
            if (str.length() < 2) {
                return false;
            }
            String doubled = str.repeat(2);
            String middle = doubled.substring(1, doubled.length() - 1);
            return middle.contains(str);
        }
    }

}
