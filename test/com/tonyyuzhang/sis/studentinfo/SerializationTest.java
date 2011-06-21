package com.tonyyuzhang.sis.studentinfo;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializationTest {
	@Test
	public void testLoadToNewVersion() throws Exception {
		CourseCatalog catalog = new CourseCatalog();
		catalog.load("CourseCatalogTest.testAdd.txt");
		assertEquals(2, catalog.getSessions().size());
	}
}
