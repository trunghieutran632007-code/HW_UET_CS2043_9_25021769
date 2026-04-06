package Bai6_07.src;

public class Main {
    public static void main(String[] args) {
        String msg = "Hello World !";
        Notifier mixNoti = new SmsNotifier(new FacebookNotifier(new EmailNotifier()));
        mixNoti.send(msg);
    }

}
