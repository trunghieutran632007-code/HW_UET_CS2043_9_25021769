package Bai6_01.src;

public class AppConfig {
    private String appName;
    private String version;

    // Bien static volatile de luu tru the hien duy nhat
    // volatile dam bao tinh nhin thay duoc (visibility) giua cac luong
    private static volatile AppConfig instance;

    private AppConfig() {
        this.appName = "Application";
        this.version = "1.0.0";
    }

    // Khoi tao luoi, sd Double-Checked Locking
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }
}
