package com.db.tema8.controller;

import com.db.tema8.exception.ProfessorNotFoundException;
import com.db.tema8.model.Course;
import com.db.tema8.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("professor")
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping("courses/{professorId}")
    public List<Course> getAllCourseByProfessor(@PathVariable Integer professorId)
            throws ProfessorNotFoundException {
        return professorService.getAllCourseByProfessor(professorId);
    }
}
