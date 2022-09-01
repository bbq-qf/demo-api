package com.bjpowernode.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 */
 public class Student implements Serializable {
  private Integer id;
  private String age;
  private String name;

 @Override
 public String toString() {
  return "Student{" +
          "id=" + id +
          ", age='" + age + '\'' +
          ", name='" + name + '\'' +
          '}';
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Student student = (Student) o;
  return id.equals(student.id) && age.equals(student.age) && name.equals(student.name);
 }

 @Override
 public int hashCode() {
  return Objects.hash(id, age, name);
 }

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getAge() {
  return age;
 }

 public void setAge(String age) {
  this.age = age;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public Student() {
 }

 public Student(Integer id, String age, String name) {
  this.id = id;
  this.age = age;
  this.name = name;
 }
}
