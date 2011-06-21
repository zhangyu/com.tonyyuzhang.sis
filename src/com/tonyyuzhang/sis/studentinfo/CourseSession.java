package com.tonyyuzhang.sis.studentinfo;

import java.util.*;

@SuppressWarnings("serial")
public class CourseSession extends Session {
	private static int count;
	
	public static CourseSession create(Course course, Date startDate) {
		return new CourseSession(course, startDate);
	}
	
	protected CourseSession(Course course, Date startDate) {
		super(course, startDate);
		CourseSession.incrementCount();		
	}
	
	private static void incrementCount() {
		++count;
	}
	
	
	static void resetCount(){
		count = 0;
	}
   
	static int getCount(){
		return count;
	}
  
	protected int getSessionLength() {
		return 16;
	}
}