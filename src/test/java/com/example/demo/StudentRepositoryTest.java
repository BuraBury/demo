package com.example.demo;

import com.example.demo.entities.Passport;
import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    @Test
    public void saveStudentWithPassportTest() {
        studentRepository.saveStudentWithPassport();
    }

    @Test
    public void findByIdWithPassport() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student => {}", student);
        logger.info("Passport => {}", student.getPassport());

    }

    @Test
    public void findStudentByPassportIdTest() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Passport => {}", passport);
        logger.info("Student => {}", passport.getStudent());
    }

    @Test
    public void getStudentAllCourses() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student => {}", student);
        logger.info("Courses => {}", student.getCourses());
    }


}
