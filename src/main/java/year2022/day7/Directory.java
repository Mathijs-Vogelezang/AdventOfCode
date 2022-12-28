package year2022.day7;

import java.util.HashMap;
import java.util.Map;

public class Directory extends Node{
    private final Map<String, Node> content;

    public Directory(String name, long size, Directory parent) {
        super(name, size, parent);
        content = new HashMap<>();
    }

    public Map<String, Node> getContent() {
        return content;
    }

    public void addNode(Node node) {
        content.put(node.getName(), node);
    }

    public Node getNode(String name) {
        return content.get(name);
    }

    public void setSize(long size) {
        super.size = size;
    }

//    public Node getNodeOrAdd(String name, Node node) {
//        content.putIfAbsent(name, node);
//        return content.get(name);
//    }
}
