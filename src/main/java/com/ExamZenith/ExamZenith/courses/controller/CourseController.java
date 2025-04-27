package com.ExamZenith.ExamZenith.courses.controller;


import com.ExamZenith.ExamZenith.courses.service.CourseService;
import com.ExamZenith.ExamZenith.courses.model.Course.CourseRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService service;

    @PostMapping
    public void createCourse(@RequestBody CourseRequest request){
        service.createCourse(request);
    }

    @DeleteMapping("/{course_id}")
    public void deleteCourse(@PathVariable Long course_id){
        service.deleteCourse(course_id);
    }

}
