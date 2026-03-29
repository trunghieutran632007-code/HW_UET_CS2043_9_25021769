package Bai5_02.src;

import java.util.*;

public class CustomerQueue {
    public CustomerQueue() {

    }

    private Queue<Customer> queue = new LinkedList<>();

    // Them khach vao cuoi hang doi
    public void addCustomer(Customer customer) {
        queue.offer(customer);
        System.out.println("[Queue] " + customer.getName() + " da vao hang doi. So khach dang cho: " + queue.size());
    }

    // Lay khach dau hang doi ra xu ly
    public Customer processNext() {
        Customer customer = queue.poll();
        if (customer == null) {
            System.out.println("[Queue] Khong con khach doi.");
        } else {
            System.out.println("[Queue] Bat dau xu ly: " + customer.getName());
        }
        return customer;
    }

    // Xem khach tiep theo ma khong lay ra
    public Customer peekNext() {
        return queue.peek();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
