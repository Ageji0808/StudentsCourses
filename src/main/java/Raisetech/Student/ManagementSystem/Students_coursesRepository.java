package Raisetech.Student.ManagementSystem;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;




@Mapper
public interface Students_coursesRepository {



  @Select("SELECT * FROM students_courses")
  List<Students_courses> getAllStudents_courses();

}
