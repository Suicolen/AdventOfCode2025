package suic;

public class AdventCalendar {

    private static final String BASE_PACKAGE = AdventCalendar.class.getPackageName() + ".";

    static void main() {
        run();
    }

    private static void run() {
        System.out.println("-----------------------------------------------------------");
        run(1);
    }

    private static void run(int dayOfMonth) {
        try {
            Class<?> clazz = Class.forName(BASE_PACKAGE + pad(dayOfMonth));
            System.out.println("Day " + dayOfMonth + " Solution");
            Puzzle<?> puzzle = (Puzzle<?>) clazz.getDeclaredConstructor().newInstance();
            puzzle.init();
            System.out.println("Part 1 result = " + puzzle.part1());
            System.out.println("Part 2 result = " + puzzle.part2());
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
