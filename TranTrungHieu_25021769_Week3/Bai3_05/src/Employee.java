package Bai3_05.src;

public abstract class Employee {
    private String name;
    private String dob;
    private String id;

    public Employee (String name, String dob, String id) {
        this.name = name;
        this.dob = dob;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getId() {
        return id;
    }

    public abstract double calculateSalary();

    public abstract String getType();
}

class FullTimeEmployee extends Employee {
    private double baseSalary;
    private double bonus;
    private double penalty;

    public FullTimeEmployee (String name, String dob, String id, double baseSalary, double bonus, double penalty) {
        super(name, dob, id);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.penalty = penalty;

    }

    @Override
    public double calculateSalary() {
        return baseSalary + (bonus - penalty);
    }

    @Override
    public String getType() {
        return "Full-time";
    }

}

class PartTimeEmployee extends Employee {
    private double workingHours;
    private double hourlyRate;

    public PartTimeEmployee(String name, String dob, String id, double workingHours,double hourlyRate ) {
        super(name, dob, id);
        this.workingHours = workingHours;
        this.hourlyRate = hourlyRate;
    }
    @Override
    public double calculateSalary() {
        return workingHours * hourlyRate;
    }

    @Override
    public String getType() {
        return "Part-time";
    }

    
}
