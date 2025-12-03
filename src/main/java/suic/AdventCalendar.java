package suic;

public class AdventCalendar {

    private static final String BASE_PACKAGE = AdventCalendar.class.getPackageName() + ".";

    private static final boolean PRINT_TIMING = true;
    private static final int WARMUP_RUNS = 100;

    static void main() {
        run();
    }

    private static void run() {
        System.out.println("-----------------------------------------------------------");
        run(3);
    }

    private static void run(int dayOfMonth) {
        try {
            Class<?> clazz = Class.forName(BASE_PACKAGE + pad(dayOfMonth));

            if (PRINT_TIMING) {
                for (int i = 0; i < WARMUP_RUNS; i++) {
                    Puzzle<?> warmup = (Puzzle<?>) clazz.getDeclaredConstructor().newInstance();
                    warmup.init();
                    warmup.part1();
                    warmup.part2();
                }
            }

            System.out.println("Day " + dayOfMonth + " Solution");

            long totalStart = System.nanoTime();

            Puzzle<?> puzzle = (Puzzle<?>) clazz.getDeclaredConstructor().newInstance();

            long initStart = System.nanoTime();
            puzzle.init();
            long initTime = System.nanoTime() - initStart;

            long part1Start = System.nanoTime();
            Object part1 = puzzle.part1();
            long part1Time = System.nanoTime() - part1Start;

            long part2Start = System.nanoTime();
            Object part2 = puzzle.part2();
            long part2Time = System.nanoTime() - part2Start;

            long totalTime = System.nanoTime() - totalStart;

            System.out.println("Part 1 result = " + part1);
            System.out.println("Part 2 result = " + part2);

            if (PRINT_TIMING) {
                System.out.println();
                System.out.printf("init() took %.3f ms%n", initTime / 1_000_000.0);
                System.out.printf("Part 1 took %.3f ms%n", part1Time / 1_000_000.0);
                System.out.printf("Part 2 took %.3f ms%n", part2Time / 1_000_000.0);
                System.out.printf("Total: %.3f ms%n", totalTime / 1_000_000.0);
            }

            System.out.println("-----------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runAll() {
        for (int i = 1; i <= 25; i++) {
            run(i);
        }
    }

    private static void runRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            run(i);
        }
    }

    public static String pad(int i) {
        return String.format("days.Day%02d", i);
    }
}
