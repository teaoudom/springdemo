package com.demo.example.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getList() {
        return studentRepo.findAll();
    }

    public void addStudent(Student student) {
      Optional<Student> studentOptional = studentRepo.findStudentByEmail(student.getEmail());
      if(studentOptional.isPresent())
            throw new IllegalStateException(("Email taken"));
      studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepo.existsById(studentId);
        if(!exists)
            throw new IllegalStateException("student with id "+studentId+" does not exists!");

        studentRepo.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
          Student student = studentRepo.findById(studentId).orElseThrow(()-> new IllegalStateException(
                  "student with id "+studentId+" does not exists"
          ));

          if(name != null &&
            name.length() > 0 &&
          !Objects.equals(student.getName(),name)){
              student.setName(name);
          }

          if(email != null &&
          email.length() > 0 &&
          !Objects.equals(student.getEmail(),email)){

              Optional<Student> studentOpt = studentRepo.findStudentByEmail(email);
              if(studentOpt.isPresent())
                    throw new IllegalStateException("email taken");
              student.setEmail(email);
          }
    }
}
