package com.example.demo;
/*
1. Spring - tworzenie mikroserwisów. Dostarcza niefunkcjonalne funkcje za darmo (metryki użytkowania, statusy zdrowia)
2. Uruchomienie serwera TomCat - http:, ustawia się na porcie 80 i nie mieli cały czas, tylko czeka na akcje
3. Spring ma budowe modularną, jeśli coś potrzebujemy pobieramy plik .jar, konfiguracja w pom.xml
4. start.spring.io - template ktory generuje plik pom.xml
5. JPA - java persistant api - zestaw interface czyli biblioteka ORM - modelowanie z obiektowego do relacyjnego. Z encji generuje tabelki,
automatyczne crudowe operacje, generuje potrzebne zapytania. nie wdraza funkcjonalnosci
6. Funkcjonalnosci dostarcza provider - Hibernate - główny ORM
7. Większość języków programowania korzysta z ORM-ów
8. Baza danych H2 - działa w kilku trybach, mozemy ją zainstalować, możemy zapisywać z niej do pliku dane,
może działac w pamieci - czyli działa i tworzy się podczas uruchomienia aplikacji - zamyka się i czyści po zamknięci aplikacji - potrzebny plik data.sql!!!

 */

import com.example.demo.entities.Course;
import com.example.demo.repositories.CourseRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args); //run() - launcher uruchamiający springa

    }

    @Override
    public void run(String... args) throws Exception {
        Course course = courseRepository.findById(10001L);
        logger.info("Course 10001: {}", course);
    }
}
