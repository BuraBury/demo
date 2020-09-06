package com.example.demo.entities;

import javax.persistence.*;

@Entity //na podstawie klasy bedzie generowana tabela w bazie danych
@Table(name = "course") //zmiana nazwy tabeli
@NamedQueries(value = {
        @NamedQuery(name="get_all_query", query = "SELECT c FROM Course c"), //mozemy sie odwołać po nazwie do naszego zapytania
        @NamedQuery(name = "get_all_query_test_in_name", query = "SELECT c FROM Course c WHERE c.name LIKE 'Test%'")
})

public class Course {

    @Id //oznaczenia klucza głównego
    @GeneratedValue //generowanie wartości automatycznie, nie jest równoznaczne z autoincrement, to Hibernate nadaje Id a nie baza danych
    private Long id;

    @Column(name = "name", nullable = false, length = 100) //ustawianie wlasciwosci encji
    private String name; //nazwa kursu

    protected Course(){} //wykorzystuje go Hibernate
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
