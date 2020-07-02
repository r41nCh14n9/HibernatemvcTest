package tw.com.phctw.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.phctw.model.Student;


@Repository(value = "studentDao")
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
    private  SessionFactory sessionFactory;


	@Override
	public Student getStudentBySid(Long sid) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Student.class, sid);
		} catch (Exception e) {
			System.out.println("getStudentBySid : "+e);
		}
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		String hql = "FROM Student";
		List<Student> students = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Student> query = session.createQuery(hql,Student.class);
			students = query.list();
		} catch (Exception e) {
			System.out.println("getAllStudents : "+e);
			return null;
		}
		return students;
	}

	@Override
	public boolean deleteStudentBySid(Long sid) {
		String hql = "delete from Student where SID = "+ sid ;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery(hql);
			q.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateStudent : "+e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateStudent(Student student) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(student);
		} catch (Exception e) {
			System.out.println("updateStudent : "+e);
			return false;
		}
		return true;
	}

	@Override
	public boolean insertStudent(Student student) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(student);
		} catch (Exception e) {
			System.out.println("insertStudent : "+e);
			return false;
		}
		return true;
	}

	//login
	@Override
	public Student getStudentForLogin(Student s) {
		String hql = "from Student where SACC = :sacc and SPWD = :spwd";
//		String hql = "select from Student4 where SACC = "+s.getSacc()+" and SPWD = "+s.getSpwd();
		Student student = null;
		try {
			System.out.println("before query...");
			Session session = sessionFactory.getCurrentSession();
			Query<Student> q = session.createQuery(hql, Student.class);
			q.setParameter("sacc", s.getSacc());
			q.setParameter("spwd", s.getSpwd());
			System.out.println("after query...");
			student = q.uniqueResult();
		} catch (Exception e) {
			System.out.println("getStudentForLogin : "+e);
			return null;
		}
		return student;
	}
	
	//register
	@Override
	public Student getStudentBySacc(String sacc) {
		String hql = "from Student where Sacc = :sacc";
		Student student = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Student> q = session.createQuery(hql, Student.class);
			q.setParameter("sacc", sacc);
			student = q.uniqueResult();
		} catch (Exception e) {
			System.out.println("getStudentBySacc : "+e);
			return null;
		}
		return student;
	}
	
	//forget password
	@Override
	public Student getStudentBySaccAndSmail(String sacc, String smail) {
		String hql = "from Student where Sacc = :sacc and Smail = :smail";
		Student student = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Student> q = session.createQuery(hql, Student.class);
			q.setParameter("sacc", sacc);
			q.setParameter("smail", smail);
			student = q.uniqueResult();
		} catch (Exception e) {
			return null;
		}
		return student;
	}
	//reset password
	//給service做...
	
	
	
	
}
