package Bai6_02.src;

public class EmailApp extends NotificationApp {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }

}
