package Bai6_06.src;

public class Shortcut implements FileSystemItem {
    private String name;
    private FileSystemItem target;
    private Folder parent;

    public Shortcut(String name, FileSystemItem target) {
        this.name = name;
        this.target = target;
    }
    
    public String getName() {
        return name;
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

    @Override
    public void print(String indent) {
        //Shortcut: <name> -> <targetPath>
        System.out.println(indent + "Shortcut: " + name + " -> " + target.getPath());
        
    }

}
