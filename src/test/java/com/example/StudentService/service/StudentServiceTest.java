package com.example.StudentService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.StudentService.exceptions.NoStudentFoundException;
import com.example.StudentService.exceptions.NullStudentDataFoundException;
import com.example.StudentService.exceptions.StudentNotFoundException;
import com.example.StudentService.model.Course;
import com.example.StudentService.model.Student;
import com.example.StudentService.repository.StundentRepository;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StudentServiceTest {
	@InjectMocks
	StudentService studentService;

	@Mock
	StundentRepository studentRepository;

	@Test
	public void testSaveStudentForPositive() {
		Student student = new Student();
		student.setName("sai");
		student.setId(1l);
		student.setEmail("sai@gmail.com");
		List<Course> courses = new ArrayList();
		Course course = new Course();
		course.setId(12l);
		course.setTitle("Spring");
		courses.add(course);
		student.setCourses(courses);
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn((student));
		Student resStudent = studentService.saveStudent(student);
		Assert.assertNotNull(resStudent);
		Assert.assertEquals(student.getName(), resStudent.getName());
	}

	@Test
	public void testSaveStudentForNegative() {
		Student student1 = new Student();
		student1.setName("sai");
		student1.setId(1l);
		Student student = new Student();
		student.setName("sai");
		student.setId(1l);
		student.setEmail("sai@gmail.com");
		List<Course> courses = new ArrayList();
		Course course = new Course();
		course.setId(12l);
		course.setTitle("Spring");
		courses.add(course);
		student.setCourses(courses);
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn((student1));
		Student resStudent = studentService.saveStudent(student);
		Assert.assertNotNull(resStudent);
		Assert.assertEquals(student.getName(), resStudent.getName());
	}

	@Test(expected = NullStudentDataFoundException.class)
	public void testSaveStudentForExce() {
		Student student = new Student();
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenThrow(NullStudentDataFoundException.class);
		Student resStudent = studentService.saveStudent(student);
	}

	@Test
	public void testFindByIdForPositive() {
		Student student = new Student();
		student.setName("sai");
		student.setId(1l);
		student.setEmail("sai@gmail.com");
		List<Course> courses = new ArrayList();
		Course course = new Course();
		course.setId(12l);
		course.setTitle("Spring");
		courses.add(course);
		student.setCourses(courses);
		Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student));
		Student resStudent = studentService.getStudent(1l);
		Assert.assertNotNull(resStudent);
		Assert.assertEquals(student.getEmail(), resStudent.getEmail());
	}

	@Test(expected = StudentNotFoundException.class)
	public void testFindByIdForExce() {
		Student student = new Student();
		student.setName("sai");
		student.setId(1l);
		Mockito.when(studentRepository.findById(2l)).thenReturn(Optional.of(student));
		Student resStudent = studentService.getStudent(1l);
	}

	@Test
	public void testGetAllStudentsForPosite() {
		List<Student> students = new ArrayList();
		Student student = new Student();
		student.setName("kumar");
		student.setId(2l);
		student.setEmail("kumar@gmail.com");
		List<Course> courses = new ArrayList();
		Course course = new Course();
		course.setId(12l);
		course.setTitle("SpringBoot");
		courses.add(course);
		student.setCourses(courses);
		students.add(student);
		Mockito.when(studentRepository.findAll()).thenReturn(students);
		List<Student> resStudents = studentService.getAllStudents();
		Assert.assertNotNull(resStudents);
		Assert.assertEquals(resStudents.size(), students.size());

	}

	@Test(expected = NoStudentFoundException.class)
	public void testGetAllStudentsForExc() {
		Mockito.when(studentRepository.findAll()).thenThrow(NoStudentFoundException.class);
		List<Student> resStudents = studentService.getAllStudents();

	}

}
