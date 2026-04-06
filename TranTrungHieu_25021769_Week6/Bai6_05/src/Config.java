package Bai6_05.src;

public class Config implements Cloneable {
    private String name;
    private String version;

    public Config(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public Config clone() {
        try {
            Config cloned = (Config) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" + "Version: " + getVersion() + "\n" + "Hashcode: " + this.hashCode();
    }



}
