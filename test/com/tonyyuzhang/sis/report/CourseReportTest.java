package com.tonyyuzhang.sis.report;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

import com.tonyyuzhang.sis.studentinfo.*;
import static com.tonyyuzhang.sis.report.ReportConstant.NEWLINE;
import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class CourseReportTest {

	@Test
	public void testReport(){
		final Date date = new Date();
		CourseReport report = new CourseReport();
		report.add(create("ENGL", "101", date));
		report.add(create("CZEC", "200", date));
		report.add(create("ITAL", "410", date));
		report.add(create("CZEC", "220", date));
		report.add(create("ITAL", "330", date));
		
		assertEquals(
			"CZEC 200" + NEWLINE +
			"CZEC 220" + NEWLINE +
			"ENGL 101" + NEWLINE +
			"ITAL 330" + NEWLINE +
			"ITAL 410" + NEWLINE,
			report.text()
		);
	}
	
	private Session create(String department, String number, Date date) {
		return CourseSession.create(new Course(department, number), date);
	}
}
