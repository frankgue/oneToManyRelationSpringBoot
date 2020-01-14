package com.gkfcsolution.onetomanyrealtion.controller;

import com.gkfcsolution.onetomanyrealtion.exception.ResourceNotFoundException;
import com.gkfcsolution.onetomanyrealtion.model.Course;
import com.gkfcsolution.onetomanyrealtion.repository.CourseRepository;
import com.gkfcsolution.onetomanyrealtion.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/gkfc")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructors/{instructorId}/courses")
    public List<Course> getCoursesByInstructor(@PathVariable(value = "instructorId") int instructorId){
        return courseRepository.findByInstructorId(instructorId);
    }

    @PostMapping("/instructors/{instructorId}/courses")
    public Course createCourse(@PathVariable(value = "instructorId") int instructorId, @Valid @RequestBody Course course) throws ResourceNotFoundException {

        return instructorRepository.findById(instructorId).map(instructor -> {
            course.setInstructor(instructor);
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found"));

    }

    @PutMapping("/instructors/{instructorId}/courses/{courseId}")
    public Course updatecourse(@PathVariable("instructorId") int instructorId, @PathVariable("courseId") int courseId, @Valid @RequestBody Course courseDetails) throws ResourceNotFoundException{

        if (!(instructorRepository.existsById(instructorId))){
            throw  new ResourceNotFoundException("Instructor Not Found");
        }

        return courseRepository.findById(courseId).map(course -> {
            course.setTitle(courseDetails.getTitle());
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("Course Id not found"));

    }

    @DeleteMapping("/instructors/{instructorId}/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable(value = "instructorId") int instructorId, @PathVariable(value = "courseId") int courseId) throws ResourceNotFoundException{

        return  courseRepository.findByIdAndInstructorId(courseId,instructorId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Course Not found with id " + courseId + " and instructorId " + instructorId));
    }
}
