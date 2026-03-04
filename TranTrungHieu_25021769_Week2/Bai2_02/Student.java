package TranTrungHieu_25021769_Week2.Bai2_02;

public class Student {
    private String id;
    private String name;
    private String email;
    private float gpa;


    //Constructor khong tham so
    public Student(){

    }

    //Constructor co 2 tham so (id, name)
    public Student(String id, String name){
        this.id = id;
        this.name = name;
    }

    //Constructor co day du tham so
    public Student(String id, String name, String email, float gpa){
        this.id = id;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
    }

    public Student(Student otherStudent){
        this.id = id;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
