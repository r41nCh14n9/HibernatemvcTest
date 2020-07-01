package tw.com.phctw.dao;

import java.util.List;

import tw.com.phctw.model.Student;



public interface StudentDao {
	Student getStudentBySid(Long sid);
	
	List<Student> getAllStudents();

	boolean deleteStudentBySid(Long sid);

	boolean updateStudent(Student student);

	boolean insertStudent(Student student);
	
	Student getStudentForLogin(Student s);
	
	Student getStudentBySacc(String sacc);
	
	public Student getStudentBySaccAndSmail(String sacc, String smail);
}
