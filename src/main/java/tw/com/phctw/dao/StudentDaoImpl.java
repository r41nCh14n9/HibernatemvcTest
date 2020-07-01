package tw.com.phctw.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;
import tw.com.phctw.model.Student;


@Repository
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
    private SessionFactory sessionFactory;


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
		
		List<Student> students = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Student> query = session.createQuery("FROM Student",Student.class);
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
//		System.out.println(hql);
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
		String hql = "select from Student where Sacc = :sacc and Spwd = :spwd";
		Student student = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Student> q = session.createQuery(hql, Student.class);
			q.setParameter("sacc", s.getSacc());
			q.setParameter("spwd", s.getSpwd());
			student = q.uniqueResult();
		} catch (Exception e) {
			System.out.println("getStudentForLogin"+e);
			return null;
		}
		return student;
	}
	
	//register
	@Override
	public Student getStudentBySacc(String sacc) {
		String hql = "select from Student where Sacc = :sacc";
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
		String hql = "select from Student where Sacc = :sacc and Smail = :smail";
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
