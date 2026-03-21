package Bai4_03.src;

public abstract class Employee implements IWorkable {
    String id;
    String name;
    double baseSalary;
//Employee khong can implements void work(), co the de class con thuc hien dieu do
}

class OfficeWorker
