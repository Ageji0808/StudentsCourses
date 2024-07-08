package raisetech.student.management.repository;

import raisetech.student.management.data.StudentsCourses;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface StudentsCoursesRepository {


  @Select("SELECT * FROM studentsCourses")
  List<StudentsCourses> getAllStudentsCourses();

  @Insert("INSERT studentsCourses(course_id,student_id,course_name) values(#{courseID},#{studentID},#{courseName})")
  void insertStudentsCourses(StudentsCourses studentsCourses);

  @Select("SELECT * FROM studentsCourses WHERE student_id = #{studentID}")
  List<StudentsCourses> findCourseByStudentId(String studentId);

  @Update("UPDATE studentsCourses SET course_name = #{courseName} WHERE course_id = #{courseID} AND student_id = #{studentID}")
  void updateStudentsCourses(StudentsCourses studentscourses);

  @Select("SELECT * FROM studentsCourses WHERE course_id = #{courseID}")
  List<StudentsCourses> findCourseById(String courseId);
}


