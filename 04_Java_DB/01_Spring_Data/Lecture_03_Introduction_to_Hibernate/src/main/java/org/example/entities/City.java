package org.example.entities;

import java.util.Set;

public class City {
    private int id;

    private String name;

    /**
     * Релацията от гледна точка на града е: One-to-Many
     * В един град може да има много студенти.
     */
    private Set<Student> students;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}