package Bai6_02.src;

public class SmsApp extends NotificationApp {
    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }
}
