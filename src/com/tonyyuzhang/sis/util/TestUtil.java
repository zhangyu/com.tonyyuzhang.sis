package com.tonyyuzhang.sis.util;

import java.io.*;
import static org.junit.Assert.*;

public class TestUtil {
	
	public static void delete(String filename) {
		File file = new File(filename);
		if(file.exists())
			assertTrue(file.delete());
	}
	
	public static void assertGone(String... filenames) {
		for (String filename: filenames)
			assertFalse(new File(filename).exists());
	}
	
}