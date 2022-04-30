package com.kahraman.server.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void existsByEmail() {

        //Given
        Student student = new Student("Abdullah", "a.kahramnn@gmail.com", Gender.MALE);
        underTest.save(student);

        //When
        Boolean expected = underTest.existsByEmail(student.getEmail());

        //Then
        assertThat(expected).isTrue();
    }
}