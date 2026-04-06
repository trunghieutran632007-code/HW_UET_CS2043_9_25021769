package Bai6_07.src;

public class SmsNotifier extends NotifierDecorator {
    public SmsNotifier(Notifier decoratedNoti) {
        super(decoratedNoti);
    }

    @Override
    public void send(String msg) {
        super.send(msg);
        System.out.println("Sms noti: " + msg);
    }

}
