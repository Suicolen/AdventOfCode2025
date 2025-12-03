package suic.days;

import suic.Puzzle;
import suic.util.FileUtils;

import java.util.*;

public class Day03 implements Puzzle<Long> {

    private List<BatteryBank> banks;

    @Override
    public void parse() {
        banks = FileUtils.readAsStream(getClass().getSimpleName() + "Input.txt")
                .map(BatteryBank::fromLine)
                .toList();

    }

    @Override
    public Long part1() {
        return solve(2);
    }

    @Override
    public Long part2() {
        return solve(12);
    }

    private List<Integer> findBestNBatteries(List<Integer> batteries, int n) {
        int batteryCount = batteries.size();
        if (batteryCount == n) {
            return batteries;
        }
        int removals = batteryCount - n;
        Deque<Integer> stack = new ArrayDeque<>(batteryCount);
        for (int battery : batteries) {
            while (removals > 0 && !stack.isEmpty() && stack.peekLast() < battery) {
                stack.removeLast();
                removals--;
            }
            stack.addLast(battery);
        }
        return stack.stream().limit(n).toList();
    }

    private long solve(int n) {
        long total = 0;
        for (BatteryBank bank : banks) {
            List<Integer> bestBatteries = findBestNBatteries(bank.batteries, n);
            total += sumBatteries(bestBatteries);
        }
        return total;
    }

    private long sumBatteries(List<Integer> batteries) {
        return batteries.stream().mapToLong(Integer::intValue).reduce(0, (a, b) -> a * 10 + b);
    }

    private record BatteryBank(List<Integer> batteries) {

        public static BatteryBank fromLine(String line) {
            List<Integer> batteries = new ArrayList<>();
            for (char c : line.toCharArray()) {
                batteries.add(Character.getNumericValue(c));
            }
            return new BatteryBank(batteries);
        }

    }

}
