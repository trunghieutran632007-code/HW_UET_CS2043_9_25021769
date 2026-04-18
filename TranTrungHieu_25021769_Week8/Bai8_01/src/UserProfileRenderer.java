public class UserProfileRenderer {
 
    public void render(User user) {
        System.out.println("[UI] Profile: " + user.getName()
                + " <" + user.getEmail() + ">");
    }
}