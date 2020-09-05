/*
Dobry test jednostkowy:
1. Adnotacja @Test
2. Metoda testująca jest publiczna i void (! nie moze byc static !)
3. Metoda nie zwraca żadnej wartości - bo void
4. Metoda nie przyjmuje żadnych argumentów (chyba, że parametrized tests)
5. Asserty jako jedyny sposób porównywania danych
 */

package com.example.demo;

import com.example.demo.entities.Course;
import com.example.demo.repositories.CourseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findByIdTest() {
        Course course = courseRepository.findById(10001L);
        Assert.assertEquals("Testowo", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById(){
        courseRepository.deleteById(10002L);
        Assert.assertNull(courseRepository.findById(10002L));
    }



}
