package com.tonyyuzhang.sis.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class SuiteBuilderTest {
   @Test
   public void testGatherTestClassNames() {
      SuiteBuilder builder = new SuiteBuilder();
      List<String> classes = builder.gatherTestClassNames();
      assertTrue(classes.contains("com.tonyyuzhang.sis.testing.SuiteBuilderTest"));
   }
}
