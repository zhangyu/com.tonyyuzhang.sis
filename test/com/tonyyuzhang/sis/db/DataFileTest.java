package com.tonyyuzhang.sis.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class DataFileTest {
	private static final String ID1 = "12345";
	private static final String ID2 = "23456";
	private static final String FILEBASE = "DataFileTest";
	
	private DataFile db;
	private TestData testData1;
	private TestData testData2;
	
	@Before
	public void setUp() throws IOException {
		db = DataFile.create(FILEBASE);
		assertEquals(0, db.size());
		
		testData1 = new TestData(ID1, "datum1a", 1);
		testData2 = new TestData(ID2, "datum2a", 2);
	}

	@After
	public void tearDown() throws IOException {
		db.close();
		db.deleteFiles();
	}

	@Test
	public void testAdd() throws IOException {
		db.add(ID1, testData1);
		assertEquals(1, db.size());
		
		db.add(ID2, testData2);
		assertEquals(2, db.size());
		
		assertTestDataEquals(testData1, (TestData)db.findBy(ID1));
		assertTestDataEquals(testData2, (TestData)db.findBy(ID2));
	}
	
	@Test
	public void testPersistence() throws IOException {
		db.add(ID1, testData1);
		db.add(ID2, testData2);
		db.close();
		
		db = DataFile.open(FILEBASE);
		assertEquals(2, db.size());
		
		assertTestDataEquals(testData1, (TestData)db.findBy(ID1));
		assertTestDataEquals(testData2, (TestData)db.findBy(ID2));
		
		db = DataFile.create(FILEBASE);
		assertEquals(0, db.size());
	}
	
	@Test
	public void testKeyNotFound() throws IOException {
		assertNull(db.findBy(ID2));
	}
	
	private void assertTestDataEquals(TestData expected, TestData actual) {
		assertEquals(expected.id, actual.id);
		assertEquals(expected.field1, actual.field1);
		assertEquals(expected.field2, actual.field2);
	}
	
	static class TestData implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		String id;
		String field1;
		int field2;
		TestData(String id, String field1, int field2) {
			this.id = id;
			this.field1 = field1;
			this.field2 = field2;
		}
	}
}
