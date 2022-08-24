package com.db.tema8.controller;

import com.db.tema8.exception.SpecialtyNotFoundException;
import com.db.tema8.exception.StudentNotFoundException;
import com.db.tema8.model.Grade;
import com.db.tema8.model.Student;
import com.db.tema8.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("grades/{studentId}")
    public List<Grade> getAllGradesByStudent(@PathVariable Integer studentId)
            throws StudentNotFoundException {
        return studentService.getAllGradesByStudent(studentId);
    }

    @PutMapping("grades/add/{studentId}/{grade}")
    public void addGrade(@PathVariable Grade grade, @PathVariable Integer studentId)
            throws StudentNotFoundException {
        studentService.addGrade(grade, studentId);
    }

    @GetMapping("greaterThan8")
    public List<Student> getAllStudentsGradeGreaterThanEight() {
        return studentService.getAllStudentsGradeGreaterThanEight();
    }

    @GetMapping("studentWithHighestGrade")
    public Student getStudentWithHighestGrade() throws StudentNotFoundException {
        return studentService.getStudentWithHighestGrade();
    }

    @GetMapping("studentWithHighestGradeAtSpecialty/{specialtyId}")
    public Student getStudentWithHighestGradeAtSpecialty(@PathVariable Integer specialtyId)
            throws StudentNotFoundException, SpecialtyNotFoundException {
        return studentService.getStudentWithHighestGradeAtSpecialty(specialtyId);
    }
}
