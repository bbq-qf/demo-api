package com.bjpowernode.demopro2.dao;

import com.bjpowernode.model.Student;
public interface StudentDao {
    Student selectById(Integer id);

    Student selectByName(String stuName);



    int insertStudent(Student student);
}
