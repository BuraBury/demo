package com.example.demo;

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
