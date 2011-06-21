package com.tonyyuzhang.sis.report;

import java.util.*;
import com.tonyyuzhang.sis.studentinfo.*;
import static com.tonyyuzhang.sis.report.ReportConstant.NEWLINE;

public class CourseReport {
	private List<Session> sessions = new ArrayList<Session>();
	
	public void add(Session session) {
		sessions.add(session);
	}
	
	public String text() {
		Collections.sort(sessions);
		StringBuilder builder = new StringBuilder();
		for(Session session: sessions)
			builder.append(
				session.getDepartment() + " " +
				session.getNumber() + NEWLINE);
		return builder.toString();
	}
}