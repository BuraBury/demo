package com.example.demo.repositories;

import com.example.demo.entities.Course;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional //pozwala na otwarte polaczenie
public class CourseRepository {

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course courseToDelete = findById(id);
        em.remove(courseToDelete);
    }






}
