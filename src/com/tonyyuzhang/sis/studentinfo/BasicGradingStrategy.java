package com.tonyyuzhang.sis.studentinfo;
import java.io.*;

@SuppressWarnings("serial")
public class BasicGradingStrategy implements GradingStrategy, Serializable {
	public int getGradePointsFor(Student.Grade grade) {
		return grade.getPoints();
	}
}