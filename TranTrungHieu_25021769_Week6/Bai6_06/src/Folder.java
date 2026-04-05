package Bai6_06.src;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemItem {
    private String name;
    private Folder parent;
    private List<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(FileSystemItem item) {
        item.setParent(this);
        children.add(item);
    }

    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @Override
    public String getPath() {
        if (parent == null) {
            return "/" + name;
        }
        return parent.getPath() + "/" + name;
    }

    @Override
    public void print(String indent) {
        // In thu muc hien tai
        System.out.println(indent + "Folder: " + name);

        // In cac phan tu con
        for (FileSystemItem child : children) {
            child.print(indent + "  ");
        }

    }

}
