package com.tonyyuzhang.sis.studentinfo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.*;

public class StudentDirectoryTest {
	private StudentDirectory dir;
	
	@Before
	public void setUp() throws IOException {
		dir = new StudentDirectory();
	}
	
	@After
	public void tearDown() throws IOException {
		dir.close();
		dir.remove();
	}
	
	@Test
	public void testRandomAccess() throws IOException {
		final int numberOfStudents = 10;
		for (int i = 0; i < numberOfStudents; i++)
			addStudent(dir, i);
		dir.close();
		
		
		dir = new StudentDirectory();
		for (int i = 0; i < numberOfStudents; i++)
			verifyStudentLookup(dir, i);
	}
/*	
	@Test
	public void testStoreAndRetrieve() throws IOException {
		final int numberOfStudents = 10;
		
		for (int i = 0; i< numberOfStudents; i++)
			addStudent(dir, i);
		
		for (int i = 0; i < numberOfStudents; i++)
			verifyStudentLookup(dir, i);
	}
*/	
	private void addStudent(StudentDirectory directory, int i)
			throws IOException {
		String id = "" + i;
		Student student = new Student(id);
		student.setId(id);
		student.addCredits(i);
		directory.add(student);
	}
	
	private void verifyStudentLookup(StudentDirectory directory, int i)
			throws IOException {
		String id = "" + i;
		Student student = dir.findById(id);
		assertEquals(id, student.getLastName());
		assertEquals(id, student.getId());
		assertEquals(i, student.getCredits());
	}

}
