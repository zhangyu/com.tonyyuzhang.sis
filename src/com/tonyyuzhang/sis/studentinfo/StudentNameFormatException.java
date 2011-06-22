package com.tonyyuzhang.sis.studentinfo;

@SuppressWarnings("serial")
public class StudentNameFormatException extends IllegalArgumentException {
	public StudentNameFormatException(String message) {
		super(message);
	}
}