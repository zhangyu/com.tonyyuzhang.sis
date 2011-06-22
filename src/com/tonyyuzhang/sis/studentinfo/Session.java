package com.tonyyuzhang.sis.studentinfo;

import java.util.*;
import java.net.*;
import java.io.*;

@SuppressWarnings("serial")
abstract public class Session implements Comparable<Session>, Iterable<Student>, java.io.Serializable {
	private Course course;
	private transient List<Student> students = new ArrayList<Student>();
	private Date startDate;
	private int numberOfCredits;	
	private URL url;
	
	protected Session(Course course, Date startDate) {
		this.course = course;
		this.startDate = startDate;	
	}
   
	public int compareTo(Session that) {
		int compare = this.getDepartment().compareTo(that.getDepartment());
		if (compare == 0)
			compare = this.getNumber().compareTo(that.getNumber());
		return compare;
	}
	
	public Iterator<Student> iterator() {
		return students.iterator();
	}
	
	void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	
	public String getDepartment() {
		return course.getDepartment();
	}
   
	public String getNumber() {
		return course.getNumber();
	}
	
	int getNumberOfStudents() {
		return students.size();
	}
   
	public void enroll(Student student) {
		students.add(student);
		student.addCredits(numberOfCredits);
	}
   
	Student get(int index) {
		return students.get(index);
	}
	
	protected Date getStartDate() {
		return startDate;
	}
	
	public List<Student> getAllStudents() {
		return students;
	}
	
	abstract protected int getSessionLength();
	
	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		final int daysInWeek = 7;
		final int daysFromFridayToMonday = 3;
		int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return calendar.getTime();
	}
	
	double averageGpaForPartTimeStudents() {
		double total = 0.0;
		int count = 0;
		for(Student student: students) {
			if(student.isFullTime())
				continue;
			count++;
			total += student.getGpa();
		}
		return total / count;
	}
	
	public void setUrl(String urlString) throws SessionException {
		try {
			this.url = new URL(urlString);
		}
		catch (MalformedURLException e) {
			log(e);
			// re-throw a application specific exception
			throw new SessionException(e);
		}
	}
	
	private void log(Exception e) {
		//e.printStackTrace();
	}
	
	public URL getUrl() {
		return url;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}
	
	private void writeObject(ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeInt(students.size());
		for (Student student: students)
			output.writeObject(student.getLastName());
	}
	
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		students = new ArrayList<Student>();
		int size = input.readInt();
		for (int i = 0; i < size; i++) {
			String lastName = (String)input.readObject();
			students.add(Student.findByLastName(lastName));
		}
	}
}