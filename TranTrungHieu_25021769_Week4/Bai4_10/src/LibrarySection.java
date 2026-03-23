package Bai4_10.src;

import java.util.*;

public class LibrarySection<T extends MediaItem> {
    private String sectionName;
    private List<T> items;

    public LibrarySection(String sectionName) {
        this.sectionName = sectionName;
        this.items = new ArrayList<>();
    }

    // Them tai lieu
    public void add(T item) {
        items.add(item);
        System.out.println("Da them: " + item + " vao " + sectionName);
    }

    // Xoa tai lieu theo id
    public void remove(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equalsIgnoreCase(id)) {
                T item = items.remove(i);
                System.out.println("Da xoa: " + item + " khoi " + sectionName);
                return;
            }
        }
        System.out.println("Khong tim thay id: " + id);
    }

    // Hien thi danh sach
    public void display() {
        System.out.println("=== " + sectionName + " ===");
        if (items.isEmpty()) {
            System.out.println("Khong co tai lieu");
            return;
        }
        for (T item : items) {
            System.out.println(" *" + item.displayInfo());
        }
    }
}
