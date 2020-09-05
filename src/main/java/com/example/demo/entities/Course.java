package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //na podstawie klasy bedzie generowana tabela w bazie danych
public class Course {

    @Id //oznaczenia klucza głównego
    @GeneratedValue //generowanie wartości automatycznie, nie jest równoznaczne z autoincrement, to biblioteka nadaje Id a nie baza danych
    private Long id;

    private String name; //nazwa kursu

    protected Course(){}
    public Course(String name) { this.name = name; } //gdy robimy konstruktor argumentowy to ZAWSZE robimy tez bezargumentowy!!!

    public Long getId() { return id; } //nigdy nie robimy setId() !!!

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
