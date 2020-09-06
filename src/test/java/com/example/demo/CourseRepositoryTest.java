/*
Dobry test jednostkowy:
1. Adnotacja @Test
2. Metoda testująca jest publiczna i void (! nie moze byc static !)
3. Metoda nie zwraca żadnej wartości - bo void
4. Metoda nie przyjmuje żadnych argumentów (chyba, że parametrized tests)
5. Asserty jako jedyny sposób porównywania danych

porównywanie typu double
Asser.assertEquals(expected: 2.0, actual: 1.99, delta: 0.1);
delta - odstępstwo na ile jesteśmy zadowoleni z wyniku, dla liczb zmiennoprzecinkowych.
akceptujemy w tym przypadku wyniki: 1.9 i 2.1
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
import javax.persistence.Table;
import java.util.List;

@RunWith(SpringRunner.class) //służy do tego żeby przestawić się na tryb springa
@SpringBootTest(classes = DemoApplication.class) //wskazuje od której klasy zaczynamy testy

public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //adnotacja służy do tego, żeby uruchomić wstrzykiwanie zależności
            CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findByIdTest() {
        Course course = courseRepository.findById(10001L);
        Assert.assertEquals("Testowo", course.getName());
    }

    @Test
    @DirtiesContext //nie idzie z bufora hybernate
    public void deleteByIdTest() {
        courseRepository.deleteById(10002L);
        Assert.assertNull(courseRepository.findById(10002L));
    }

    //w obu ponizszych testach najpierw sprawdzamy warunki początkowe, potem ustawiamy nową nazwę i testujemy raz jeszcze
    @Test
    @DirtiesContext
    public void saveEditTest() {
        Course course = courseRepository.findById(10003L);
        Assert.assertEquals("Testowo3", course.getName());

        course.setName("Testowo3 - update");
        courseRepository.save(course);

        Course courseAfterUpdate = courseRepository.findById(10003L);
        Assert.assertEquals("Testowo3 - update", courseAfterUpdate.getName());
    }

    @Test
    @DirtiesContext
    public void saveInsertTest() {
        Course course = courseRepository.findById(1L);
        Assert.assertNull(course);

        Course newCourse = new Course("Testowo - insert");
        courseRepository.save(newCourse);

        Course courseAfterInsert = courseRepository.findById(1L);
        Assert.assertEquals("Testowo - insert", courseAfterInsert.getName());
    }

    @Test
    public void playWithEM() {
        courseRepository.playWithEntityManager();
    }

    @Test
    public void findAllJPQL() {
        List resultList = em.createQuery("SELECT c FROM Course c").getResultList();
        logger.info("Wynik => {}", resultList);
    }

    @Test
    public void findCourseWith3InName() {
        String ipql = "SELECT c FROM Course c WHERE name LIKE '%3%'";
        List resultList = em.createQuery(ipql).getResultList();
        logger.info("Wynik => {}", resultList);
    }

    @Test
    //@NamedQuery(name = "get_all_query_test_in_name", query = "SELECT c FROM Course c WHERE c.name LIKE 'Test%'")
    public void findCourseWithTestInName() {
        List resultList = em.createNamedQuery("get_all_query_test_in_name").getResultList();
        logger.info("Wynik => {}", resultList);
    }

    @Test
    public void findAllJPQLWithUpperName() {
        String ipql = "SELECT UPPER (c.name) FROM Course c";
        List resultList = em.createQuery(ipql).getResultList();
        logger.info("Wynik => {}", resultList);
    }

    @Test
    public void findCoursesWithIdBetween10003And10005() {
        String ipql = "SELECT c FROM Course c WHERE c.id BETWEEN 10003 AND 10005";
        //String ipql = "SELECT c FROM Course c WHERE c.id >= 10003 AND c.id <= 10005";
        List resultList = em.createQuery(ipql).getResultList();
        logger.info("Wynik => {}", resultList);
    }

    @Test
    //@NamedQuery(name="get_all_query", query = "SELECT c FROM Course c")
    public void findAllByNamedQuery() {
        List result = em.createNamedQuery("get_all_query").getResultList();
        logger.info("Wynik => {}", result);
    }


}
