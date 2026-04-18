package Bai8_01.src;

public class EmailService {
 
    public void sendWelcomeEmail(User user) {
        System.out.println("[Email] Welcome email sent to " + user.getEmail());
    }
 
    public void sendPasswordResetEmail(User user) {
        System.out.println("[Email] Password reset email sent to " + user.getEmail());
    }
}