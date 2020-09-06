package com.example.demo.repositories;

import com.example.demo.entities.Course;

import com.example.demo.entities.Review;
import com.example.demo.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional //pozwala na otwarte polaczenie
public class CourseRepository {

    @Autowired
    EntityManager em; //pełni rolę Proxy, uruchamia logikę, która ma wykonać zapytania na bazie danych
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course courseToDelete = findById(id);
        em.remove(courseToDelete);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course); //odpowiada INSERT INTO, nadaje ID obiektowi, po zakonczeniu tranzakcji obiekt zostaje wrzucony do bazy danych
        } else {
            em.merge(course); //odpowiada UPDATE
        }
        return course;
    }

    public void playWithEntityManager() {
        Course course = new Course("Test");
        em.persist(course); //nadaje ID obiektowi wrzucanemu do bazy, czeka na wrzucenie do bazy danych
        em.flush(); //zapisany obiekt do bazy danych

        //em.detach(course); //zaprzestanie obserwowania obiektu
        //em.clear(); - zaprzestanie obserwowania wszystkich obiektów - hurtowy detach()

        course.setName("TEST@@@");
        em.refresh(course); //odzyskuje wartosc name = "Test";

    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);

        for (Review review : reviews) {
            course.addRv(review);
            review.setCourse(course);

            em.persist(review);
        }

    }

    public void addStudentWithCourse(Long studentId, Long courseId) {
        Student student = em.find(Student.class, studentId);
        Course course = findById(courseId);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);

    }


}
