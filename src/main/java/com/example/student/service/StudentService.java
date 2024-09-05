package com.example.student.service;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.Student;
import com.example.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents()
    {
        return studentRepo.findAll();
    }

    public Student getStudentById(Integer id)
    {
        return studentRepo.findById(id).orElse(new Student());
    }

    public void addStudent(Student student)
    {
        studentRepo.save(student);
    }

    public void updateStudent(int id,Student updatedStudent)
    {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
            student.setName(updatedStudent.getName());
            student.setGender(updatedStudent.getGender());
            studentRepo.save(student);
    }

    public void deleteStudent(Integer id)
    {
        if (!studentRepo.existsById(id))
        {
            throw new StudentNotFoundException(id);
        }
        studentRepo.deleteById(id);
    }
}
