package year2022.day7;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution extends Day {
    private Directory root;
    private List<Directory> directories;
    private long totalSize = 0;

    public Solution() throws IOException {
    }

    private void loadFilesystem() {
        root = new Directory("/", 0, null);
        Directory current = root;

        for (String line : input) {
            String[] command = line.split(" ");
            if (command[0].equals("$")) {
                if (command[1].equals("cd")) {
                    current = switch (command[2]) {
                        case "/" -> root;
                        case ".." -> current.parent;
                        default -> (Directory) current.getNode(command[2]);
                    };
                }
            } else if (command[0].equals("dir")) {
                current.addNode(new Directory(command[1], 0 , current));
            } else {
                long size = Integer.parseInt(command[0]);
                current.addNode(new File(command[1], size, current));
            }
        }
    }

    private long countSizes(Directory directory) {
        for (Node node : directory.getContent().values()) {
            if (node instanceof File) {
                directory.setSize(directory.getSize() + node.getSize());
            } else if (node instanceof Directory) {
                directory.setSize(directory.getSize() + countSizes((Directory) node));
            }
        }

        return directory.getSize();
    }

    private List<Directory> listDirectories(Directory directory) {
        List<Directory> directories = new ArrayList<>();
        directories.add(directory);

        if (directory.getContent() != null) {
            for (Node node : directory.getContent().values()) {
                if (node instanceof Directory) {
                    directories.addAll(listDirectories((Directory) node));
                }
            }
        }

        return directories;
    }

    @Override
    public void setup() {
        loadFilesystem();
        totalSize = countSizes(root);
        directories = listDirectories(root);
    }

    @Override
    public String part1() {
        long totalSize = 0;

        for (Directory directory : directories) {
            long size = directory.getSize();
            if (size <= 100000) {
                totalSize += size;
            }
        }

        return Long.toString(totalSize);
    }

    @Override
    public String part2() {
        long size = directories.stream().filter(x -> totalSize - x.getSize() < 40000000).min(Comparator.comparingLong(Node::getSize)).get().getSize();
        // used space is 70000000 - 30000000 = 40000000
        return Long.toString(size);
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
