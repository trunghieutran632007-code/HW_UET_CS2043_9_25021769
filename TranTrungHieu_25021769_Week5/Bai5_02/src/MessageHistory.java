package Bai5_02.src;

import java.util.Stack;

public class MessageHistory {
    public MessageHistory() {

    }

    private Stack<Message> stack = new Stack<>();
    private int nextId = 1;

    // Nhan vien go tin nhan -> push vao stack
    public void typeMessage(String content) {
        Message msg = new Message(nextId++, content);
        stack.push(msg);
        System.out.println("  [Stack] Da go: \"" + content + "\" (tong " + stack.size() + " dong)");
    }

    // Undo: xoa tin nhan vua go (pop ra khoi stack)
    public void undo() {
        if (stack.isEmpty()) {
            System.out.println("  [Stack] Khong co gi de undo.");
            return;
        }
        Message removed = stack.pop();
        System.out.println("  [Stack] Undo! Da xoa: \"" + removed.getContent() + "\"");
    }

    // View Last: xem tin nhan gan nhat ma khong xoa (peek)
    public void viewLast() {
        if (stack.isEmpty()) {
            System.out.println("  [Stack] Chua co tin nhan nao.");
            return;
        }
        Message top = stack.peek();
        System.out.println("  [Stack] Dong cuoi hien tai: \"" + top.getContent() + "\"");
    }

    // In toan bo lich su tin nhan (tu duoi stack len dinh)
    public void printHistory() {
        if (stack.isEmpty()) {
            System.out.println("  [Stack] Lich su trong.");
            return;
        }
        System.out.println("  [Stack] Noi dung hien tai (" + stack.size() + " dong):");
        // Duyet tu chi so cao (dinh) xuong thap (day) de hien thi dung thu tu
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println("    [" + (i + 1) + "] " + stack.get(i).getContent());
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

}
