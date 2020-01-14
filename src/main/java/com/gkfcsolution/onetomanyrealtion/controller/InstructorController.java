package com.gkfcsolution.onetomanyrealtion.controller;

import com.gkfcsolution.onetomanyrealtion.exception.ResourceNotFoundException;
import com.gkfcsolution.onetomanyrealtion.model.Instructor;
import com.gkfcsolution.onetomanyrealtion.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/gkfc")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructors")
    public List<Instructor> getInstructors(){
        return instructorRepository.findAll();
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable(value = "id") int instructorId) throws ResourceNotFoundException{
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        return ResponseEntity.ok().body(instructor);
    }

    @PostMapping("/instructors")
    public Instructor createInstructor(@Valid @RequestBody Instructor instructor){
        return instructorRepository.save(instructor);
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") int instructorId, @Valid @RequestBody Instructor instructorDetails) throws ResourceNotFoundException{
        Instructor oldInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found :: " + instructorId ));

        oldInstructor.setFirstName(instructorDetails.getFirstName());
        oldInstructor.setLastName(instructorDetails.getLastName());
        oldInstructor.setEmail(instructorDetails.getEmail());

        final Instructor updateInstructor  = instructorRepository.save(oldInstructor);
        return ResponseEntity.ok(updateInstructor);

    }

    @DeleteMapping("/instructors/{id}")
    public Map<String, Boolean> deleteInstructor(@PathVariable("id") int instructorId) throws ResourceNotFoundException{

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found :: " + instructorId));

        instructorRepository.delete(instructor);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  response;

    }
}
