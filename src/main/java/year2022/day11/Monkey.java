package year2022.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    public final List<Long> items = new ArrayList<>();
    public final String operation;
    public final int test;
    public final int ifTrue;
    public final int ifFalse;
    public long inspectedItems = 0;

    public Monkey(String operation, int test, int ifTrue, int ifFalse) {
        this.operation = operation;
        this.test = test;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
}
