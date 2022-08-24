package com.db.tema8.controller;

import com.db.tema8.model.Course;
import com.db.tema8.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("all/greaterThanThree")
    public List<Course> getAllCoursesCreditPointsGreaterThanThree() {
        return courseService.getAllCoursesCreditPointsGreaterThanThree();
    }
}
