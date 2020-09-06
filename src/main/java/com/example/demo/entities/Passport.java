package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    /* cykliczna relacja - możemy przejść z jednego obiektu do drugiego i z drugiego do pierwszego
      (student -> paszport, paszport -> student)
      automatycznie Eagre !!! */
    @OneToOne(mappedBy = "passport")
    private Student student;

    protected Passport(){}
    public Passport(String number) { this.number = number; }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }


}
