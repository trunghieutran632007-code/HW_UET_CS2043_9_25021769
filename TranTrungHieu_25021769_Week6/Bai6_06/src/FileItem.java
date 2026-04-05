package Bai6_06.src;

public class FileItem implements FileSystemItem {
    private String name;
    private long kb;
    private Folder parent;

    public FileItem(String name, long kb) {
        this.name = name;
        this.kb = kb;
    }

    @Override
    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @Override
    public String getPath() {
        if (parent == null) {
            return "/" + getName();
        }
        return parent.getPath() + "/" + getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKb(long kb) {
        this.kb = kb;
    }

    public long getKb() {
        return kb;
    }

    public String getName() {
        return name;
    }

    @Override
    public void print(String indent) {
        // File: <name> (<size>KB)
        System.out.println(indent + "File: " + getName() + "(" + getKb() + "KB)");
    }

}
