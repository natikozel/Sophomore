package model;

import java.io.Serializable;
import java.util.Objects;

public class Institution implements Serializable {

    private static int INSTANCE = 0;
    private int ID;
    private String Name;
    private String City;

    public Institution(String Name, String City) {
        this.ID=++INSTANCE;
        this.Name=Name;
        this.City=City;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Institution)) return false;
        Institution that = (Institution) o;
        return getID() == that.getID() && Objects.equals(getName(), that.getName()) && Objects.equals(getCity(), that.getCity());
    }

    @Override
    public String toString() {
        return "Institution's ID: " + this.getID() +"\nInstitution's City: " + this.getCity() + "\nInstitution's Name: " + this.getName()+"\n";
    }
}
