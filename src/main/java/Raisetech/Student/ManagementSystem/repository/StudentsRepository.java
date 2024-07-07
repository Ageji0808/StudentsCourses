package Raisetech.Student.ManagementSystem.repository;

import Raisetech.Student.ManagementSystem.data.Student;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper

public interface StudentsRepository {


  @Select("SELECT * FROM student")
  List<Student> getAllStudents();

  @Insert("INSERT student(id,name) values(#{id},#{name})")
  void insertStudents(Student student);

}



