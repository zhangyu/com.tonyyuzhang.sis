package com.tonyyuzhang.sis.ui;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.io.*;
import com.tonyyuzhang.sis.studentinfo.*;
import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class StudentUITest {
	private static final String name = "Leo Xerces Schmoo";
	
	@Test
	public void testCreateStudent() throws IOException {
		StringBuffer expectedOutput = new StringBuffer();
		StringBuffer input = new StringBuffer();
		setup(expectedOutput, input);
		byte[] buffer = input.toString().getBytes();
		
		InputStream inputStream = new ByteArrayInputStream(buffer);
		OutputStream outputStream = new ByteArrayOutputStream();
		
		InputStream consoleIn = System.in;
		PrintStream consoleOut = System.out;
		
		System.setIn(inputStream);
		System.setOut(new PrintStream(outputStream));
		
		try {
			StudentUI ui = new StudentUI();
			ui.run();
			assertEquals(expectedOutput.toString(), outputStream.toString());
			assertStudents(ui.getAddedStudents());
		}
		finally {
			System.setIn(consoleIn);
			System.setOut(consoleOut);
		}
	}
	
	private void setup(StringBuffer expectedOutput, StringBuffer input) {
		expectedOutput.append(StudentUI.MENU);
		input.append(line(StudentUI.ADD_OPTION));
		expectedOutput.append(StudentUI.NAME_PROMPT);
		input.append(line(name));
		expectedOutput.append(line(StudentUI.ADDED_MESSAGE));
		expectedOutput.append(StudentUI.MENU);
		input.append(line(StudentUI.QUIT_OPTION));
	}
	
	private String line(String input) {
		return String.format("%s%n", input);
	}
	
	private void assertStudents(List<Student> students) {
		assertEquals(1, students.size());
		Student student = students.get(0);
		assertEquals(name, student.getName());
	}
}
