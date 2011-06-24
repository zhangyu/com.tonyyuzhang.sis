package com.tonyyuzhang.sis.testing;

import java.lang.reflect.Modifier;
import java.util.*;
import junit.framework.*;
import junit.runner.*;

public class SuiteBuilder {
   @SuppressWarnings("unchecked")
   public List<String> gatherTestClassNames() {
      TestCollector collector = new ClassPathTestCollector() {
         @Override
         public boolean isTestClass(String classFileName) {
            if (!super.isTestClass(classFileName))
               return false;
            if (classFileName.indexOf("tonyyuzhang") < 0)
               return false;
            String className = classNameFromFile(classFileName);
            @SuppressWarnings("rawtypes")
            Class klass = createClass(className);
            return
               klass.isAnnotationPresent(com.tonyyuzhang.sis.testing.TestClass.class) && 
               isConcrete(klass);
         }
      };
      return Collections.list(collector.collectTests());
   }

   @SuppressWarnings("rawtypes")
   protected boolean isConcrete(Class klass) {
      if (klass.isInterface())
         return false;
      int modifiers = klass.getModifiers();
      return !Modifier.isAbstract(modifiers);
   }

   @SuppressWarnings("rawtypes")
   private Class createClass(String name) {
      try {
         return Class.forName(name);
      }
      catch (ClassNotFoundException e) {
         return null;
      }
   }

   public TestSuite suite() {
      TestSuite suite = new TestSuite();
      for (String s: gatherTestClassNames())
         suite.addTest(new JUnit4TestAdapter(createClass(s)));
      return suite;
   }
}
