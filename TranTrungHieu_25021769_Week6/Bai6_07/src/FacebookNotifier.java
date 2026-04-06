package Bai6_07.src;

public class FacebookNotifier extends NotifierDecorator {
    public FacebookNotifier(Notifier decoratedNoti) {
        super(decoratedNoti);
    }

    @Override
    public void send(String msg) {
        super.send(msg);
        System.out.println("Facebook noti: " + msg);
    }

}
