package com.tonyyuzhang.sis.studentinfo;

public interface GradingStrategy {
	public int getGradePointsFor(Student.Grade grade);
}