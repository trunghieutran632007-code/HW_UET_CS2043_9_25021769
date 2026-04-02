package Bai6_02.src;

public class Main {
    public static void main(String[] args) {
        //Chon ung dung Email
        System.out.println("=== Email ===");
        NotificationApp emailApp = new EmailApp();
        emailApp.notifyUser("Dang nhap thanh cong");

        //Chon ung dung SmsApp
        System.out.println("=== Sms ===");
        NotificationApp smsApp = new SmsApp();
        smsApp.notifyUser("Gui tin nhan thanh cong");

    }

}
