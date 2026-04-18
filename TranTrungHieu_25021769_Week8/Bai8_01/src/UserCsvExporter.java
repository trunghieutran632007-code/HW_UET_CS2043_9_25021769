package Bai8_01.src;

public class UserCsvExporter {
 
    public String export(User user) {
        return user.getId() + "," + user.getName() + "," + user.getEmail();
    }
}

