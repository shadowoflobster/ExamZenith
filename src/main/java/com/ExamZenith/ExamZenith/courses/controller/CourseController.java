package com.ExamZenith.ExamZenith.courses.controller;


import com.ExamZenith.ExamZenith.courses.model.Course.CourseDTO;
import com.ExamZenith.ExamZenith.courses.persistence.Course.Course;
import com.ExamZenith.ExamZenith.courses.service.CourseService;
import com.ExamZenith.ExamZenith.courses.model.Course.CourseRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService service;

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long courseId){
        CourseDTO courseDTO = service.getCourse(courseId);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping
    public void createCourse(@RequestBody CourseRequest request){
        service.createCourse(request);
    }

    @DeleteMapping("/{course_id}")
    public void deleteCourse(@PathVariable Long course_id){
        service.deleteCourse(course_id);
    }

    @GetMapping
    public Page<CourseDTO> getCourses(@RequestParam int page, @RequestParam int pageSize){
        return service.getCourses(page,pageSize);
    }
}
