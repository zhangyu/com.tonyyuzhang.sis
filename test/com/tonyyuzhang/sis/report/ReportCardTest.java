package com.tonyyuzhang.sis.report;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import com.tonyyuzhang.sis.studentinfo.*;

import java.util.*;
import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class ReportCardTest {
	private ReportCard card;
	
	@Before
	public void setup() throws Exception {
		card = new ReportCard();
	}
	
	@Test
	public void testGetMessage() {
		assertEquals(ReportCard.A_MESSAGE, card.getMessage(Student.Grade.A));
		assertEquals(ReportCard.B_MESSAGE, card.getMessage(Student.Grade.B));
		assertEquals(ReportCard.C_MESSAGE, card.getMessage(Student.Grade.C));
		assertEquals(ReportCard.D_MESSAGE, card.getMessage(Student.Grade.D));
		assertEquals(ReportCard.F_MESSAGE, card.getMessage(Student.Grade.F));
	}
	
	@Test
	public void testKeys() {
		Set<Student.Grade> expectedGrades = EnumSet.allOf(Student.Grade.class);
		Set<Student.Grade> grades = EnumSet.noneOf(Student.Grade.class);
		for (Student.Grade grade: card.getMessages().keySet())
			grades.add(grade);
		assertEquals(expectedGrades, grades);
	}
	
	@Test
	public void testValues() {
		List<String> expectedMessages = new ArrayList<String>();
		expectedMessages.add(ReportCard.A_MESSAGE);
		expectedMessages.add(ReportCard.B_MESSAGE);
		expectedMessages.add(ReportCard.C_MESSAGE);
		expectedMessages.add(ReportCard.D_MESSAGE);
		expectedMessages.add(ReportCard.F_MESSAGE);
		
		Collection<String> messages = card.getMessages().values();
		for (String message: messages)
			assertTrue(expectedMessages.contains(message));
		assertEquals(expectedMessages.size(), messages.size());
	}
	
	@Test
	public void testEntries() {
		Set<Entry> entries = new HashSet<Entry>();
		for (Map.Entry<Student.Grade, String> entry: card.getMessages().entrySet())
			entries.add(new Entry(entry.getKey(), entry.getValue()));
		Set<Entry> expectedEntries = new HashSet<Entry>();
		expectedEntries.add(new Entry(Student.Grade.A, ReportCard.A_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.B, ReportCard.B_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.C, ReportCard.C_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.D, ReportCard.D_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.F, ReportCard.F_MESSAGE));
		assertEquals(expectedEntries, entries);
	}
}
