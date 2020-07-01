package tw.com.phctw.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.DivideOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.phctw.dao.StudentDao;
import tw.com.phctw.model.Student;
import tw.com.phctw.util.ForgetPwdMail;
import tw.com.phctw.util.VerificationMail;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentDao dao;

	@Autowired
	private ForgetPwdMail fpm;

	public Student getStudentBySid(Long sid) {
		return dao.getStudentBySid(sid);
	}

	public List<Student> getAllStudents() {
		return dao.getAllStudents();
	}

	public boolean deleteStudentBySid(Long sid) {
		return dao.deleteStudentBySid(sid);
	}

	public boolean insertStudent(Student student) {
		return dao.insertStudent(student);
	}

	public boolean updateStudent(Student student) {
		return dao.updateStudent(student);
	}

	// login
	public Student validateStudent(Student s) {
		s.setSpwd(getMD5Endocing(s.getSpwd()));
		Student student = dao.getStudentForLogin(s);
		if (student != null) {
			System.out.println("account exist.");
		} else {
			System.out.println("account is invalid.");
		}
		return student;
	}

	// register check
	public boolean checkSaccExist(String sacc) {
		if (dao.getStudentBySacc(sacc)!=null) {
			return true;
		} else {
			return false;
		}
	}

	// register
	public boolean register(Student student) {
//		System.out.println(student);
//		Integer max = Integer.parseInt(dao.findMaxId().split("A")[1]);
//		System.out.println(max);
//		String newSno = "A" + String.format("%06d", (max + 1));
		String encPwd = getMD5Endocing(student.getSpwd());
		student.setSpwd(encPwd);
//		System.out.println(student);
		// send confirm mail
//		String conf = vm.veriCode();
//		student.setConfirm(conf);
		if (dao.insertStudent(student)) {
			System.out.println("學生資料建立成功");
//			vm.send(student.getSmail(), conf);
			return true;
		} else {
			System.out.println("學生資料建立失敗");
			return false;
		}
	}

	// forgetPwd check
	public Student checkForgotenStd(String sacc, String smail) {
		Student student = dao.getStudentBySaccAndSmail(sacc, smail);
		if (student != null) {
			System.out.println("account exist.");
		} else {
			System.out.println("account is invalid.");
		}
		return student;
	}

	// reset Pwd
	public boolean resetPwd(Student student) {
		String newPwd = fpm.createPwd();
		String encPwd = getMD5Endocing(newPwd);
		String toAddr = student.getSmail();

		student.setSpwd(encPwd);
		
		if (dao.insertStudent(student)) {
			fpm.send(toAddr, newPwd);
			return true;
		} else {
			return false;
		}
	}

	public String getMD5Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

}
