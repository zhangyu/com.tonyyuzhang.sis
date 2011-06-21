package com.tonyyuzhang.sis.summer;

import java.util.*;
import com.tonyyuzhang.sis.studentinfo.*;

@SuppressWarnings("serial")
public class SummerCourseSession extends Session {
	
	public static SummerCourseSession create(Course course, Date startDate) {
		return new SummerCourseSession(course, startDate);
	}
	
	private SummerCourseSession(Course course, Date startDate) {
		super(course, startDate);
	}
	
	protected int getSessionLength() {
		return 8;
	}

}