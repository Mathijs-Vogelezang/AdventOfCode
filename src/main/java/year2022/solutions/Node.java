package year2022.solutions;

public abstract class Node {
    protected String name;
    protected long size;
    protected Directory parent;

    public Node(String name, long size, Directory parent) {
        this.name = name;
        this.size = size;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
}
