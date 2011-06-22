package com.tonyyuzhang.sis.summer;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
import com.tonyyuzhang.sis.studentinfo.*;
import com.tonyyuzhang.sis.util.DateUtil;

public class SummerCourseSessionTest extends SessionTest{

	@Test
	public void testEndDate() {
		Date startDate = DateUtil.createDate(2003, 6, 9);
		Session session = createSession(new Course("ENGL", "200"), startDate);
		Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
		assertEquals(eightWeeksOut, session.getEndDate());
	}
	
	protected Session createSession(Course course, Date startDate) {
		return SummerCourseSession.create(course, startDate);
	}
}
