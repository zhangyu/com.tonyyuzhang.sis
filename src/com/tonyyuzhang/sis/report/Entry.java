package com.tonyyuzhang.sis.report;

import com.tonyyuzhang.sis.studentinfo.Student;

class Entry {
	private Student.Grade grade;
	private String message;
	Entry(Student.Grade grade, String message) {
		this.grade = grade;
		this.message = message;
	}
	
	@Override
	public boolean equals(Object object) {
		if(this.getClass() != object.getClass())
			return false;
		Entry that = (Entry)object;
		return
			this.grade == that.grade &&
			this.message.equals(that.message);
	}
	
	@Override
	public int hashCode() {
		final int hashMultiplier = 41;
		int result = 7;
		result = result * hashMultiplier + this.grade.hashCode();
		result = result * hashMultiplier + this.message.hashCode();
		return result;
	}
}