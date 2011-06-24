package com.tonyyuzhang.sis.util;


import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class IOUtilTest {
	private static final String FILENAME1 = "IOUtilTest1.txt";
	private static final String FILENAME2 = "IOUtilTest2.txt";
	
	@Test
	public void testDeleteSingleFile() throws IOException {
		create(FILENAME1);
		assertTrue(IOUtil.delete(FILENAME1));
		TestUtil.assertGone(FILENAME1);
	}
	
	@Test
	public void testDeleteMultipleFiles() throws IOException {
		create(FILENAME1, FILENAME2);
		assertTrue(IOUtil.delete(FILENAME1, FILENAME2));
		TestUtil.assertGone(FILENAME1,FILENAME2);
	}
	
	@Test
	public void testDeleteNoFile() throws IOException {
		TestUtil.delete(FILENAME1);
		assertFalse(IOUtil.delete(FILENAME1));
	}
	
	@Test
	public void testDeletePartiallySucessful() throws IOException {
		create(FILENAME1);
		TestUtil.delete(FILENAME2);
		assertFalse(IOUtil.delete(FILENAME1, FILENAME2));
		TestUtil.assertGone(FILENAME1);
	}

	private void create(String...filenames) throws IOException {
		for (String filename: filenames) {
			TestUtil.delete(filename);
			new File(filename).createNewFile();
		}
	}
}
