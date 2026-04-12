package Bai7_04.src;

import java.util.HashMap;
import java.util.Map;

public class BookStore {
    Map stock = new HashMap<String, Integer>();
    
    public BookStore(Map stock) {
        this.stock = stock;
    }

}
