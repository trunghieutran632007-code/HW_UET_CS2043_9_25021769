package Bai6_06.src;

public interface FileSystemItem {
    void print(String indent);
    void setParent(Folder parent);
    String getPath();

}
