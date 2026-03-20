package Bai3_10.src;

public class Employee {
    private String name;
    private double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public double calculateBonus() {
        return baseSalary * 0.1;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String getName() {
        return name;
    }
}

class Developer extends Employee {
    private int overtimeHours;

    public Developer (String name, double baseSalary, int overtimeHours) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculateBonus() {
        return getBaseSalary() * 0.1 + overtimeHours * 200000;
    }
}

class Tester extends Employee {
    private int bugsFound;

    public Tester (String name, double baseSalary, int bugsFound) {
        super(name, baseSalary);
        this.bugsFound = bugsFound;
    }

    @Override
    public double calculateBonus() {
        return getBaseSalary() * 0.1 + (bugsFound * 50000);
    }
}
