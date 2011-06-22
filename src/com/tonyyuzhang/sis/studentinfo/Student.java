package com.tonyyuzhang.sis.studentinfo;

import java.util.*;
import java.util.logging.*;
import java.io.*;

@SuppressWarnings("serial")
public class Student implements Serializable {
	
	public enum Grade {
		A(4), 
		B(3), 
		C(2), 
		D(1), 
		F(0);
	
		private int points;
	
		Grade(int points) {
			this.points = points;
		}
		
		int getPoints() {
			return points;
		}
	};
	
	public enum Flag {
		ON_CAMPUS(1),
		TAX_EXEMPT(2),
		MINOR(4),
		TROUBLEMAKER(8);
		
		private int mask;
		
		Flag(int mask) {
			this.mask = mask;
		}
	};
	
	private int settings = 0x0;
	
	static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	static final String IN_STATE = "CO";
	static final int MAX_NAME_PARTS = 3;
	static final String TOO_MANY_NAME_PARTS_MSG = 
		"Student name '%s' contains more than %d parts";
	private String name;
	private String firstName = "";
	private String lastName;
	private String middleName = "";
	private String id;
	private int credits;
	private String state = "";
	private List<Student.Grade> grades = new ArrayList<Student.Grade>();
	private GradingStrategy gradingStrategy = new BasicGradingStrategy();
	private List<Integer> charges = new ArrayList<Integer>();
	final static Logger logger = Logger.getLogger(Student.class.getName());
	
	public Student(String fullName)/*since StudentNameFormatException is a 
	subclass of IllegalArgumentException which is a subclass of 
	RuntimeException, Student constructor doesn't have to throw this exception*/
	{ 
		this.name = fullName;
		credits = 0;
		List<String> nameParts = split(fullName);
		if(nameParts.size() > MAX_NAME_PARTS){
			String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, 
				fullName, MAX_NAME_PARTS);
			Student.logger.info(message);
			throw new StudentNameFormatException(message);
		}
		setName(nameParts);
	}
	
	public void set(Flag...flags) {
		for (Flag flag: flags)
			settings |= flag.mask;
	}
	
	public void unset(Flag...flags) {
		for (Flag flag: flags)
			settings &= ~flag.mask;
	}
	
	public boolean isOn(Flag flag) {
		return (settings & flag.mask) == flag.mask;
	}
	
	public boolean isOff(Flag flag) {
		return !isOn(flag);
	}
	public String getName(){
		return name;
	}
	
	private void setName(List<String> nameParts) {
		this.lastName = removeLast(nameParts);
		String name = removeLast(nameParts);
		if(nameParts.isEmpty())
			this.firstName = name;
		else {
			this.middleName = name;
			this.firstName = removeLast(nameParts);
		}
	}
	
	private String removeLast(List<String> nameParts) {
		if(nameParts.isEmpty())
			return "";
		return nameParts.remove(nameParts.size() - 1);
	}

/*	
	private List<String> split(String fullName) {
		List<String> results = new ArrayList<String>();
		
		StringBuffer word = new StringBuffer();
		
		//using for-loop
		for(int index = 0; index < fullName.length(); index++) {
			char ch = fullName.charAt(index);
			if(!Character.isWhitespace(ch))
				word.append(ch);
			else
				if(word.length() > 0) {
					results.add(word.toString());
					word = new StringBuffer();
				}
		}
		
		// using while-loop
//		int index = 0;
//		while(index < fullName.length()) {
//			char ch = fullName.charAt(index);
//			if(ch != ' ')
//				word.append(ch);
//			else
//				if(word.length() > 0) {
//					results.add(word.toString());
//					word = new StringBuffer();
//				}
//			index++;			
//		}
		
		if(word.length() > 0)
			results.add(word.toString());
		return results;
	}
*/	

	private List<String> split(String fullName) {
		List<String> results = new ArrayList<String>();
		for(String name: fullName.split(" "))
			results.add(name);
		return results;
	}
		

	public String getFirstName() {
		return firstName;	
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	boolean isFullTime(){
		return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
	}
	
	int getCredits(){
		return credits;
	}
	
	void addCredits(int credits){
		this.credits += credits;
	}
	
	boolean isInState(){
		return state.equals(Student.IN_STATE);
	}
	
	void setState(String state){
		this.state = state;	
	}
	
	void addGrade(Student.Grade grade) {
		grades.add(grade);
	}
	
	double getGpa() {
		
		if (grades.isEmpty())
			return 0.0;
		Student.logger.config("begin getGap " + System.currentTimeMillis());
		double total = 0.0;
		for (Student.Grade grade: grades) {
			total += gradingStrategy.getGradePointsFor(grade);
		}
		double result = total / grades.size();
		Student.logger.config("end getGpa " + System.currentTimeMillis());
		return result;
	}
	
	void setGradingStrategy(GradingStrategy gradingStrategy) {
		this.gradingStrategy = gradingStrategy;
	}
	
	public void addCharge(int charge) {
		charges.add(charge);
	}
	
	public int totalCharges() {
		int total = 0;
		for(Integer charge: charges)
			total += charge;
		return total;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public static Student findByLastName(String lastName) {
		return new Student(lastName);
	}
}