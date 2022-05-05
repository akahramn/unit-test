package com.kahraman.server.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock private StudentRepository studentRepository;
    private StudentService underTestStudentService;

    @BeforeEach
    void setUp() {
        underTestStudentService = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        //when
            underTestStudentService.getAllStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    @Disabled
    void addStudent() {
    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}