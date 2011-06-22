package com.tonyyuzhang.sis.studentinfo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import static com.tonyyuzhang.sis.util.DateUtil.createDate;
import java.net.*;

abstract public class SessionTest {
	private Session session;
	private Date startDate;
	public static final int CREDITS = 3;
	
	abstract protected Session createSession(Course course, Date startDate);
	
	@Before
	public void setUp() throws Exception {
		startDate = createDate(2003, 1, 6);
		session = createSession(new Course("ENGL","101"), startDate);
		session.setNumberOfCredits(CREDITS);
	}

	@Test
	public void testCreate() {
		assertEquals("ENGL", session.getDepartment());
		assertEquals("101", session.getNumber());
		assertEquals(0, session.getNumberOfStudents());
		assertEquals(startDate, session.getStartDate());
	}
	
	@Test
	public void testEnrollStudents() {
		Student student1 = new Student("Cain DiVoe");
		session.enroll(student1);
		assertEquals(CREDITS, student1.getCredits());
		assertEquals(1, session.getNumberOfStudents());
		assertEquals(student1, session.get(0));
	  
		Student student2 = new Student("Coralee DeVaughn");
		session.enroll(student2);
		assertEquals(CREDITS, student2.getCredits());
		assertEquals(2, session.getNumberOfStudents());
		assertEquals(student2, session.get(1));
		assertEquals(student1, session.get(0));
	}
	
	@Test
	public void testComparable() {
		final Date date = new Date();
		Session sessionA = createSession(new Course("CMSC", "101"), date);
		Session sessionB = createSession(new Course("ENGL", "101"), date);
		assertTrue(sessionA.compareTo(sessionB) < 0);
		assertTrue(sessionB.compareTo(sessionA) > 0);
		
		Session sessionC = createSession(new Course("CMSC", "101"), date);
		assertEquals(0, sessionA.compareTo(sessionC));

		Session sessionD = createSession(new Course("CMSC", "210"), date);
		assertTrue(sessionC.compareTo(sessionD) < 0);
		assertTrue(sessionD.compareTo(sessionC) > 0);
	}
	
	@Test
	public void testAverageGpaForPartTimeStudents() {
		session.enroll(createFullTimeStudent());
		
		Student partTimer1 = new Student("1");
		partTimer1.addGrade(Student.Grade.A);
		session.enroll(partTimer1);
		
		session.enroll(createFullTimeStudent());
		
		Student partTimer2 = new Student("2");
		partTimer2.addGrade(Student.Grade.B);
		session.enroll(partTimer2);
		
		assertEquals(3.5, session.averageGpaForPartTimeStudents(), 0.05);		
	}
	
	private Student createFullTimeStudent() {
		Student student = new Student("a");
		student.addCredits(Student.CREDITS_REQUIRED_FOR_FULL_TIME);
		return student;
	}
	
	@Test
	public void testIterate() {
		enrollStudents(session);
		
		List<Student> results = new ArrayList<Student>();
		for(Student student: session)
			results.add(student);
		assertEquals(session.getAllStudents(), results);
	}
	
	private void enrollStudents(Session session) {
		session.enroll(new Student("a"));
		session.enroll(new Student("b"));
		session.enroll(new Student("c"));
	}
	
	@Test
	public void testSessionUrl() throws SessionException {
		final String url = "http://www.tonyyuzhang.com";
		session.setUrl(url);
		assertEquals(url, session.getUrl().toString());
	}
	
	@Test
	public void testInvalidSessionUrl() {
		final String url = "htps://www.tonyyuzhang.com";
		try {
			session.setUrl(url);
			fail("expected exception due to invalid protocal in URL");
		}
		catch (SessionException expectedException) {
			Throwable cause = expectedException.getCause();
			assertEquals(MalformedURLException.class, cause.getClass());
		}
	}

}
