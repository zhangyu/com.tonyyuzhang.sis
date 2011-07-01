package com.tonyyuzhang.sis.util;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class LineWriterTest {
   final String file = "LineWrterTest.testCreate.txt";
   @Test
   public void testMultipleRecords() throws IOException {
      try {
         LineWriter.write(file, new String[] {"a", "b"});
         
         BufferedReader reader = null;
         try {
            reader = new BufferedReader(new FileReader(file));
            assertEquals("a", reader.readLine());
            assertEquals("b", reader.readLine());
            assertNull(reader.readLine());
         }
         finally {
            if (reader != null)
               reader.close();
         }
      }
      finally {
         TestUtil.delete(file);
      }
   }
}
