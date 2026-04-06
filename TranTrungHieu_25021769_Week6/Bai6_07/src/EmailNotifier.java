package Bai6_07.src;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String msg) {
        System.out.println("Email noti: " + msg);
    }

}
