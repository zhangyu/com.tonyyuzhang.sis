package com.tonyyuzhang.sis.util;

import static org.junit.Assert.*;
import org.junit.Test;
import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class StringUtilTest {
   
   private static final String TEXT = "this is it, isn't it";

   @Test
   public void testOccurrencesOne() {
      assertEquals(1, StringUtil.occurrences(TEXT, "his"));
   }
   
   @Test
   public void testOccurrencesNone() {
      assertEquals(0,StringUtil.occurrences(TEXT, "smelt"));
   }
   
   @Test
   public void testOccurrencesMany() {
      assertEquals(3, StringUtil.occurrences(TEXT, "is"));
      assertEquals(2, StringUtil.occurrences(TEXT, "it"));
   }
   
   @Test
   public void testOccurrencesSearchStringTooLarge() {
      assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
   }

}
