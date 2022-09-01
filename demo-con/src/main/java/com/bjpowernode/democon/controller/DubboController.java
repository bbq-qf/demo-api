package com.bjpowernode.democon.controller;

import com.bjpowernode.model.Student;
import com.bjpowernode.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DubboController {
    //远程对象调用 dubbo 的注解注入
    @DubboReference(interfaceClass = StudentService.class,version = "1.0")
    private StudentService studentService;

    @PostMapping("/student/add")
    public String addStudent(Student student){
        String msg="0";
        int result = studentService.addStudent(student);
        if (result==-1){
            msg="此名称已被占用";
        } else if (result==0) {
            msg="创建失败";

        } else if (result==-2) {
            msg="id重复";

        } else if (result==1) {
            msg="创建成功";
        }
        return msg;
    }
    @GetMapping("/student/query")
    public String queryStudent(Integer id){
        String msg="";
        Student student=null;
        if (id != null && id > 0) {
             student = studentService.queryStudent(id);
            if (student != null) {
                msg="id为"+id+"的学生信息:"+student.toString();
            }
            else{

                msg="id不正确";
            }
        }else {
            msg="id为空";
        }

        return msg;
    }
}
