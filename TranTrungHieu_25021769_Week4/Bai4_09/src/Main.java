package Bai4_09.src;

public class Main {
    public static void main(String[] args) {

        // Kho thuc pham
        Warehouse<Food> foodWarehouse = new Warehouse<>("Kho thuc pham");
        foodWarehouse.Import(new Food("F01", "Banana", "30/03/2026"));
        foodWarehouse.Import(new Food("F02", "Apple", "29/03/2026"));
        foodWarehouse.Import(new Food("F03", "Water", "01/01/2027"));

        foodWarehouse.check();
        System.out.println();
        foodWarehouse.Export("F02");
        System.out.println();
        foodWarehouse.check();

        // Kho dien tu
        Warehouse<Electronic> electronicWarehouse = new Warehouse<>("Kho dien tu");
        electronicWarehouse.Import(new Electronic("E01", "Iphone", "1 nam"));
        electronicWarehouse.Import(new Electronic("E02", "Bphone", "2 nam"));

        System.out.println();
        electronicWarehouse.check();

        int n = 0;

        //Cac tinh huong bi loi:
        // electronicWarehouse.Import(n); 
        // --> Loi: The method Import(Electronic) in the type Warehouse<Electronic> is not applicable for the arguments (int)
        
        //electronicWarehouse.Import(new Food("F03", "Water", "01/01/2027"));
        //--> Loi: The method Import(Electronic) in the type Warehouse<Electronic> is not applicable for the arguments (Food)
    }
}