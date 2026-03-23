package Bai4_10.src;

public abstract class MediaItem {
    private String id;
    private String name;

    public MediaItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String displayInfo();

    @Override
    public String toString() {
        return name + " - id: " + id;
    }
}
