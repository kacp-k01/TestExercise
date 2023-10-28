package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentExistEmail() {

        String email = "kajto@gmail.com";
        Student student = new Student("Kajto", email, Gender.MALE);
        underTest.save(student);

        boolean exists = underTest.selectExistsEmail(email);

        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudentNotExistEmail() {

        String email = "kajto@gmail.com";

        boolean exists = underTest.selectExistsEmail(email);

        assertThat(exists).isFalse();
    }
}