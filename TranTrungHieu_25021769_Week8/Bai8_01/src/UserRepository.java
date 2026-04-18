package Bai8_01.src;

public class UserRepository {
 
    public User findById(int id) {
        // Giả lập
        return new User(id, "User " + id, "user" + id + "@example.com");
    }
}