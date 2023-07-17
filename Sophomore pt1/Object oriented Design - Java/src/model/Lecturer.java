package model;

import java.io.Serializable;
import java.util.Objects;

public class Lecturer implements Serializable  {

    private static int INSTANCE = 100;
    private int ID;
    private Institution i;
    private String FName;
    private String LName;
    private double Salary;
    private int Seniority;

    public Lecturer(Institution i, String FName, String LName, double salary, int seniority) {
        this.ID = ++INSTANCE;
        this.i = i;
        this.FName = FName;
        this.LName = LName;
        this.Salary = salary;
        this.Seniority = seniority;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Institution getI() {
        return i;
    }

    public void setI(Institution i) {
        this.i = i;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public int getSeniority() {
        return Seniority;
    }

    public void setSeniority(int seniority) {
        Seniority = seniority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;
        Lecturer lecturer = (Lecturer) o;
        return getID() == lecturer.getID() && Double.compare(lecturer.getSalary(), getSalary()) == 0 && getSeniority() == lecturer.getSeniority() && Objects.equals(getI(), lecturer.getI()) && Objects.equals(getFName(), lecturer.getFName()) && Objects.equals(getLName(), lecturer.getLName());
    }

    @Override
    public String toString() {
        return  "Lecturer's Name: " + FName + " " + LName +
                "\nLectures in: " + i.getName() +
                "\nSalary: " + (int)Salary +
                "\nSeniority: " + Seniority+ "\n";
    }
}
