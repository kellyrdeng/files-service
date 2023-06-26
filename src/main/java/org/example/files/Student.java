package org.example.files;

public class Student {
    private String name;
    private static long studentCount;

    public Student(String name) {
        this.name = name;
        studentCount++;
    }

    public void sleep() {
        System.out.println(name + " is sleeping zzz");
    }

    public static void main(String[] args) {
        Student longxing = new Student("longxing");
        longxing.sleep();
        System.out.println(Student.studentCount);

        Student kelly = new Student("kelly");
        System.out.println(studentCount);
    }
}
