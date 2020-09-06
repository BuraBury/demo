package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    //relacja 1 do 1 - jeden student - jeden paszport
    //mapowanie tabel - nawiązanie relacji
    @OneToOne
    private Passport passport;

    protected Student(){}
    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }





}
