package Bai4_09.src;

import java.util.*;

public class Warehouse<T extends Product> {
    private String name;
    private List<T> products;

    public Warehouse(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    //Nhap kho
    public void Import(T item) {
        products.add(item);
        System.out.println("Da nhap " + item + " vao " + name);
    }

    //Xuat kho
    public void Export(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equalsIgnoreCase(id)) {
                T item = products.remove(i);
                System.out.println("Da xuat " + item + " khoi " + name);
                return;
            }
        }
        System.out.println("Khong tim thay id!");
    }

    //Kiem Ke
    public void check() {
        System.out.println("=== Kiem ke: " + name + " ===");
        if (products.isEmpty()) {
            System.out.println("Kho trong!");
            return;
        }

        for (T item : products) {
            System.out.println(" *" + item.checkInfo());
        }
    }

}
