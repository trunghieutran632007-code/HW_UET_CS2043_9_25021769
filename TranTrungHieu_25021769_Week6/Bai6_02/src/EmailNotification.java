package Bai6_02.src;

public class EmailNotification implements Notification {
    @Override
    public void send(String msg) {
        System.out.println("Dang gui thong bao Email voi noi dung: " + msg);
    }
}
