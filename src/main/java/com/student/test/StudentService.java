package com.student.test;

import com.student.model.Student;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StudentService {

    private String backendURL = "http://localhost:8085/students/getStudents";

    public Student getStudent() {
        Student st = new Student();
        st.setName("Mike");
        st.setAge(10);
        st.setMarks(90);
        return st;
    }

    public List<Student> addStudent(Map<String, Object> stOne) {
        Student st = new Student();
        st.setName("Mike");
        st.setAge(10);
        st.setMarks(90);
        List<Student> stList = new ArrayList<>();
        stList.add(st);
        Student sta = new Student();
        sta.setName((String)stOne.get("name"));
        sta.setAge((Integer)stOne.get("age"));
        sta.setMarks((Integer)stOne.get("marks"));
        stList.add(sta);
        return stList;
    }

    public void setBackendURL(String URLBase, boolean get) {
        if(get) {
            this.backendURL = URLBase + "/students/getStudents";
        }else{
            this.backendURL = URLBase + "/students/addStudent";
        }
    }
}
