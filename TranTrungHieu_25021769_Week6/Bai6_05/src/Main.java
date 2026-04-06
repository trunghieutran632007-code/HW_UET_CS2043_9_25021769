package Bai6_05.src;

public class Main {
    public static void main(String[] args) {
        //1. Tao Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println("1. Tao logger duy nhat: ");
        System.out.println(logger1.hashCode());
        System.out.println(logger2.hashCode());

        //2. Tao cac doi tuong Export
        System.err.println("\n2. Tao cac doi tuong Export: ");
        Export excel = ExportApp.getExportInstance("Excel");
        Export pdf = ExportApp.getExportInstance("PDF");

        //3.Adapter
        System.out.println("\n3. Adapter: ");
        OldPlayer oldPlayer = new OldPlayer();
        PlayerAdapter playerAdapter = new PlayerAdapter(oldPlayer);
        playerAdapter.play("Music.mp3");

        //4. Ban sao 
        System.out.println("\n4. Ban sao: ");
        Config config = new Config("App", "1.0.0");
        Config configCopy = config.clone();

        System.out.println(config);
        System.out.println();
        System.out.println(configCopy);
        

    }

}
