package Bai6_02.src;

public class SmsNotification implements Notification {
    @Override
    public void send(String msg) {
        System.out.println("Dang gui thong bao tu SMS voi noi dung: " + msg);
    }
}
