package com.student.controller;


import com.student.test.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.student.model.Student;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/students/getStudents")
    public ResponseEntity<Student> getStudents() {
       Student students = studentService.getStudent();
        ResponseEntity<Student> responseEntity = new ResponseEntity<>(students, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students/addStudent")
    public ResponseEntity<List<Student>> addStudent(@RequestBody Map<String, Object> student) {
        List<Student> students = studentService.addStudent(student);
        ResponseEntity<List<Student>> responseEntity = new ResponseEntity<>(students, HttpStatus.OK);
        return responseEntity;
    }
}
