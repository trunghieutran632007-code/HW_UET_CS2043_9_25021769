package Bai4_08.src;

public class Smartlight extends Device implements ChangeLevel {
    double level;
    public Smartlight(String id, String name) {
        super(id, name);
        this.level = 10;
    }

    @Override
    public void change(double level) {
        this.level = level;
    }


}
