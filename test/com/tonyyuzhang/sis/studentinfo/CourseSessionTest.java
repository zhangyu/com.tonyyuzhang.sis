package com.tonyyuzhang.sis.studentinfo;

import java.util.*;

import static org.junit.Assert.*;

import static com.tonyyuzhang.sis.util.DateUtil.createDate;

import org.junit.Test;

public class CourseSessionTest extends SessionTest {
	@Test
	public void testCourseDates() {
		Date startDate = createDate(2003, 1, 6);
		Session session = createSession(new Course("ENGL", "200"), startDate);
		Date sixteenWeeksOut = createDate(2003, 4, 25);
		assertEquals(sixteenWeeksOut, session.getEndDate());
	}
	
	@Test
	public void testCount() {
		CourseSession.resetCount();
		createSession(new Course("", ""), new Date());
		assertEquals(1, CourseSession.getCount());
		createSession(new Course("", ""), new Date());
		assertEquals(2, CourseSession.getCount());
	}
	
	protected Session createSession(Course course, Date startDate) {
		return CourseSession.create(course, startDate);
	}
}
