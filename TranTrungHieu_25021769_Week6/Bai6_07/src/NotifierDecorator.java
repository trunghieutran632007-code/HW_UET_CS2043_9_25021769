package Bai6_07.src;

public abstract class NotifierDecorator implements Notifier {
    protected Notifier decoratedNoti;

    public NotifierDecorator(Notifier decoratedNoti) {
        this.decoratedNoti = decoratedNoti;
    }

    @Override
    public void send(String msg) {
        decoratedNoti.send(msg);
    }

}
