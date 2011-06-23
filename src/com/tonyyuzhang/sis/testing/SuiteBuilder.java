package com.tonyyuzhang.sis.testing;

import java.util.Collections;
import java.util.List;
import org.junit.;

public class SuiteBuilder {
   public List<String> gatherTestClassNames() {
      TestCollector collector = new ClassPathTestCollector() {
         @Override
         public boolean isTestClass(String classFileName) {
            return super.isTestClass(classFileName);
         }
      };
      return null;
   }
}
