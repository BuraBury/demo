package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //na podstawie klasy bedzie generowana tabela w bazie danych
@Table(name = "course") //zmiana nazwy tabeli
@NamedQueries(value = {
        @NamedQuery(name = "get_all_query",
                query = "SELECT c FROM Course c"), //mozemy sie odwołać po nazwie do naszego zapytania
        @NamedQuery(name = "get_all_query_test_in_name",
                query = "SELECT c FROM Course c WHERE c.name LIKE 'Test%'")
})

public class Course {

    @Id //oznaczenia klucza głównego
    @GeneratedValue
    //generowanie wartości automatycznie, nie jest równoznaczne z autoincrement, to Hibernate nadaje Id a nie baza danych
    private Long id;

    @Column(name = "name", nullable = false, length = 100) //ustawianie wlasciwosci encji
    private String name; //nazwa kursu

    /* relacja jeden-do-wielu
      (jeden kurs -> wiele ocen)
      automatycznie Lazy !!!*/
    @OneToMany(mappedBy = "course") //wlascicielem relacji jest ta encja, która jest @ManyToOne
    private List<Review> rv = new ArrayList<>();

    @ManyToMany(mappedBy = "courses") //typ: Lazy
    private List<Student> students = new ArrayList<>();

    protected Course() {
    } //wykorzystuje go Hibernate

    public Course(String name) {
        this.name = name;
    } //gdy robimy konstruktor argumentowy to ZAWSZE robimy tez bezargumentowy!!!

    public Long getId() {
        return id;
    } //nigdy nie robimy setId() !!!

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getRv() {
        return rv;
    }
    public void addRv(Review reviewToAdd) {
        rv.add(reviewToAdd);
    }

    public List<Student> getStudents() {
        return students;
    }
    public void addStudent(Student studentToAdd) {
        students.add(studentToAdd);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
