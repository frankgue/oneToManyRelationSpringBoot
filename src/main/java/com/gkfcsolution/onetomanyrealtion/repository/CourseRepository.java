package com.gkfcsolution.onetomanyrealtion.repository;

import com.gkfcsolution.onetomanyrealtion.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByInstructorId(int instructorId);

    Optional<Course> findByIdAndInstructorId(int id, int instructorId);
}
