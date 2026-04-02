package Bai6_02.src;

public abstract class NotificationApp {
    public abstract Notification createNotification();

    public void notifyUser(String msg) {
        Notification noti = createNotification();
        noti.send(msg);
    }

}
