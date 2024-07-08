package raisetech.student.management.repository;

import raisetech.student.management.data.Student;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper

public interface StudentsRepository {


  @Select("SELECT * FROM student WHERE is_deleted = false") // 削除されていない受講生のみを取得するクエリに変更
  List<Student> getAllStudents();

  @Insert("INSERT Into student values(#{id},#{name},#{hurigana},#{nickname},#{mailaddress},#{area},#{age},#{sex},#{remark},false)")
  void insertStudent(Student student);

  @Select("SELECT * FROM student WHERE id = #{id}")
  Student findStudentById(String id);

  @Update("UPDATE student SET name = #{name}, hurigana = #{hurigana}, nickname = #{nickname}, mailaddress = #{mailaddress}, " +
      "area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

}





