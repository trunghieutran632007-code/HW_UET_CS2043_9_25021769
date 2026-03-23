package Bai4_10.src;

public class DVD extends MediaItem {
    private String director;
    private int duration;

    public DVD(String id, String name, String director, int duration) {
        super(id, name);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }
    public int getDuration() {
        return duration;
    }

    @Override
    public String displayInfo() {
        return getName() + " - " + director + " - " + duration + " phut";
    }
}
