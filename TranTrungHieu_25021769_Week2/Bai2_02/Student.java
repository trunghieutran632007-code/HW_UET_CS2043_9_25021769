package TranTrungHieu_25021769_Week2.Bai2_02;

public class Student {
    private String id;
    private String name;
    private String email;
    private double gpa;


    //Constructor khong tham so
    public Student(){

    }

    //Constructor co 2 tham so (id, name)
    public Student(String id, String name){
        this.id = id;
        this.name = name;
    
    }

    //Constructor co day du tham so
    public Student(String id, String name, String email, double gpa){
        this.id = id;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
    }
    //Copy constructor
    public Student(Student otherStudent){
        this.id = otherStudent.id;
        this.name = otherStudent.name;
        this.email = otherStudent.email;
        this.gpa = otherStudent.gpa;
    }

    public void setid(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGpa(double gpa){
        if (gpa < 0.0 || gpa > 4.0){
            System.out.println("Gpa phai nam trong khoang 0.0 den 4.0 !");
        }
        else{
            this.gpa = gpa;
        }
    }

    public void displayInfo(){
        System.out.println("Thong tin hoc sinh:");
        System.out.println("Ho va ten: " + this.name);
        System.out.println("Ma sinh vien: " + this.id);
        System.out.println("email: " + this.email);
        System.out.println("gpa: " + this.gpa);
    }

    //main
    public static void main(String[] args) {
        //Khong co tham so
        Student stu1 = new Student();

        //Co 2 tham so (id, name)
        Student stu2 = new Student("25021234", "Nguyen Van A");

        //Day du tham so
        Student stu3 = new Student("25021235", "Nguyen Van B", "1235@vnu.edu.vn", 3.5);

        //Copy constructor
        Student stu4 = new Student(stu3);

        //Set gpa cua stu4 < 0
        stu4.setGpa(-2.5);

        stu1.displayInfo();
        stu2.displayInfo();
        stu3.displayInfo();
        stu4.displayInfo();
        
        

        
    }

}
