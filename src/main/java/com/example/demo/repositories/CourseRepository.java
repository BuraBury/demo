package com.example.demo.repositories;

import com.example.demo.entities.Course;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional //pozwala na otwarte polaczenie
public class CourseRepository {

    @Autowired
    EntityManager em;

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
            em.persist(course); //odpowiada INSERT INTO, nadaje ID
        } else {
            em.merge(course); //odpowiada UPDATE
        }
        return course;
    }

    public void playWithEntityManager() {
        Course course = new Course("Test");
        em.persist(course); //nadaje ID obiektowi wrzucanemu do bazy
        course.setName("TEST@@@");

    }


}
