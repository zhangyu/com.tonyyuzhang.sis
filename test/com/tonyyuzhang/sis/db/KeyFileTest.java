package com.tonyyuzhang.sis.db;


import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import com.tonyyuzhang.sis.util.*;

public class KeyFileTest {
	private static final String FILENAME = "keyfiletest.idx";
	private static final String KEY = "key";
	private static final long POSITION = 1;
	private static final int LENGTH = 100;
	
	private KeyFile keyFile;
	@Before
	public void setUp() throws IOException {
		TestUtil.delete(FILENAME);
		keyFile = new KeyFile(FILENAME);
	}

	@After
	public void tearDown() throws IOException {
		TestUtil.delete(FILENAME);
		keyFile.close();
	}
	
	@Test
	public void testCreate() {
		assertEquals(0, keyFile.size());
	}
	
	@Test
	public void testAddEntry() {
		keyFile.add(KEY, POSITION, LENGTH);
		assertEquals(1, keyFile.size());
		assertTrue(keyFile.containsKey(KEY));
		assertEquals(POSITION, keyFile.getPosition(KEY));
		assertEquals(LENGTH, keyFile.getLength(KEY));
	}
	
	@Test
	public void testReopen() throws IOException {
		keyFile.add(KEY, POSITION, LENGTH);
		keyFile.close();
		
		keyFile = new KeyFile(FILENAME);
		assertEquals(1, keyFile.size());
		assertTrue(keyFile.containsKey(KEY));
		assertEquals(POSITION, keyFile.getPosition(KEY));
		assertEquals(LENGTH, keyFile.getLength(KEY));
	}
}
