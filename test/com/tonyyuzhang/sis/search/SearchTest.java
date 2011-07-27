package com.tonyyuzhang.sis.search;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.tonyyuzhang.sis.testing.TestClass;
import com.tonyyuzhang.sis.util.LineWriter;
import com.tonyyuzhang.sis.util.TestUtil;

import java.io.*;

@TestClass
public class SearchTest {
   public static final String[] TEST_HTML = {
      "<html>",
      "<body>",
      "Book: Agile Jave, by Jeff Langr<br />",
      "Synopsis: Mr Langr teaches you<br />",
      "Java via test-driven development.<br />",
      "</body>",
      "</html>"
   };
   
   public static final String FILE = "temp/testFileSearch.html";
   public static final String URL = "file:" + FILE;
   
   @Before
   public void setUp() throws Exception {
      TestUtil.delete(FILE);
      LineWriter.write(FILE, TEST_HTML);
   }
   
   @After
   public void tearDown() throws Exception {
      TestUtil.delete(FILE);
   }
   
   @Test
   public void testCreate() throws IOException {
      Search search = new Search(URL, "x");
      assertEquals(URL, search.getUrl());
      assertEquals("x", search.getText());
   }
   
   @Test
   public void testPossitiveSearch() throws IOException {
      Search search = new Search(URL, "Jeff Langr");
      search.execute();
      assertTrue(search.matches() >= 1);
      assertFalse(search.errored());
   }

   @Test
   public void testNegativeSearch() throws IOException {
      Search search = new Search(URL, "Zhang Yu");
      search.execute();
      assertEquals(0, search.matches());
      assertFalse(search.errored());
   }
   
   @Test
   public void testErroredSearch() throws IOException {
      final String badUrl = "file:" + "temp/testFileSearch1.html";
      Search search = new Search(badUrl, "whatever");
      search.execute();
      assertTrue(search.errored());
      assertEquals(FileNotFoundException.class, search.getError().getClass());
   }
}
