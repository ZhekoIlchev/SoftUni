package org.example.entities;

public class Student {
    private int id;

    private String firstName;

    private String lastName;

    /**
     * Релацията от гледна точка на студента е: Many-to-One.
     * Може да има много студенти в един град.
     */
    private City city;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, First Name: %s, Last Name: %s, Town: %s.",
                this.getId(), this.getFirstName(), this.getLastName(), this.city.getName());
    }
}