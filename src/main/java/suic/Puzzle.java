package suic;

public interface Puzzle<O> {
    void parse();

    O part1();

    O part2();

    default void init() {
        parse();
    }
}