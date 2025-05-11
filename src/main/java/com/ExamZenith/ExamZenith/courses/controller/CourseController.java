package com.ExamZenith.ExamZenith.courses.controller;


import com.ExamZenith.ExamZenith.courses.model.Course.CourseDTO;
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
        if(courseDTO!=null) {
            return ResponseEntity.ok(courseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public Page<CourseDTO> getCourses(@RequestParam int page, @RequestParam int pageSize){
        return service.getCourses(page,pageSize);
    }


    @PostMapping
    public void createCourse(@RequestBody CourseRequest request){
        service.createCourse(request);
    }

    @DeleteMapping("/{course_id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long course_id){
        if(!service.deleteCourse(course_id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Course Deleted Successfully!");
    }


    @PutMapping("/{course_id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long course_id,@RequestBody CourseRequest request){
        if(!service.updateCourse(course_id,request)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Course Updated Successfully!");
    }
}
