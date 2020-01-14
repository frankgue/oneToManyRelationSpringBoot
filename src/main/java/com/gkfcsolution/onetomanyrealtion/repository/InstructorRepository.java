package com.gkfcsolution.onetomanyrealtion.repository;

import com.gkfcsolution.onetomanyrealtion.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
