package com.db.tema8.service;

import com.db.tema8.model.Course;
import com.db.tema8.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCoursesCreditPointsGreaterThanThree() {
        return courseRepository.findAll().stream().filter((Course course) -> course.getCreditPoints() >= 3).collect(Collectors.toList());
    }
}
