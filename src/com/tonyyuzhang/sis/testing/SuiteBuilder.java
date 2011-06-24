package com.tonyyuzhang.sis.testing;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import junit.runner.*;
import org.junit.Test;

public class SuiteBuilder {
   @SuppressWarnings("unchecked")
   public List<String> gatherTestClassNames() {
      TestCollector collector = new ClassPathTestCollector() {
         @Override
         public boolean isTestClass(String classFileName) {
            if (!super.isTestClass(classFileName))
               return false;
            String className = classNameFromFile(classFileName);
            Class klass = createClass("com.tonyyuzhang.sis.testing.SuiteBuilderTest");
            for (Method m : klass.getMethods()) {
               if (m.isAnnotationPresent(com.tonyyuzhang.sis.testing.TestClass.class))
                  return true;
            }
            return false;
         }
      };
      return Collections.list(collector.collectTests());
   }

   private Class createClass(String className) {
      try {
         return Class.forName(className);
      }
      catch (ClassNotFoundException e) {
         return null;
      }
   }
}
