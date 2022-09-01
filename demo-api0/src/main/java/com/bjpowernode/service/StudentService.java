package com.bjpowernode.service;

import com.bjpowernode.model.Student;

public interface StudentService {
    int addStudent(Student student);
    Student queryStudent(Integer id);


}
