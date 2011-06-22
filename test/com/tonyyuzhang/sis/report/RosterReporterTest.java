package com.tonyyuzhang.sis.report;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.tonyyuzhang.sis.studentinfo.*;
import com.tonyyuzhang.sis.util.DateUtil;

import java.io.*;

public class RosterReporterTest {
	private Session session;
	
	@Before
	public void setup() throws Exception {
		session = CourseSession.create(new Course("ENGL", "101"), DateUtil.createDate(2003, 1, 6));
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));
	}
	
	@Test
	public void testRosterReport() throws IOException {
		  Writer writer = new StringWriter();
		  new RosterReporter(session).writeReport(writer);		  
		  assertReportContents(writer.toString());
	}
	
	@Test
	public void testFiledReport() throws IOException {
		final String filename = "testFiledReport.txt";
		try {
			delete(filename);
			new RosterReporter(session).writeReport(filename);
			
			StringBuffer buffer = new StringBuffer();
			String line;
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null)
				buffer.append(String.format(line + "%n"));
			reader.close();
			
			assertReportContents(buffer.toString());
		}
		finally {
			delete(filename);
		}
	}
	
	private void delete(String filename) {
		File file = new File(filename);
		if(file.exists())
			assertTrue("unable to delete" + filename, file.delete());
	}

	private void assertReportContents(String rosterReport) {
		assertEquals(String.format(RosterReporter.ROSTER_REPORT_HEADER
	               + "A%n"
				   + "B%n"
				   + RosterReporter.ROSTER_REPORT_FOOTER, 2)
				   , rosterReport);
	}
}
