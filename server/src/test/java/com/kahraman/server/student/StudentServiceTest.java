package com.kahraman.server.student;

import com.kahraman.server.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    void canAddStudent() {
        //Given
            Student student = new Student(
                    "Ceren",
                    "ceren@gmail.com",
                    Gender.FEMALE
            );
        //When
            underTestStudentService.addStudent(student);
        //Then
        ArgumentCaptor<Student> argumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(argumentCaptor.capture());

        Student capturedStudent = argumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void willThrownMessageWhenEmailIsTaken() {
        //Given
        Student student = new Student(
                "Ceren",
                "ceren@gmail.com",
                Gender.FEMALE
        );
        given(studentRepository.existsByEmail(student.getEmail())).willReturn(true);

        //When
        //Then

        assertThatThrownBy(() -> underTestStudentService.addStudent(student)).
                isInstanceOf(BadRequestException.class).
                hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudent() {

        //Given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(true);
        // when
        underTestStudentService.deleteStudent(id);

        // then
        verify(studentRepository).deleteById(id);
    }


}