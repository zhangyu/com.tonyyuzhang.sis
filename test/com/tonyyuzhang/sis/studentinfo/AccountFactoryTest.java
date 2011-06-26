package com.tonyyuzhang.sis.studentinfo;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import java.math.*;
import com.tonyyuzhang.sis.security.*;

public class AccountFactoryTest {
   private List<Method> updateMethods;
   private List<Method> readOnlyMethods;

   @Before
   public void setUp() throws Exception {
      updateMethods = new ArrayList<Method>();
      addUpdateMethod("setBankAba", String.class);
      addUpdateMethod("setBankAccountNumber", String.class);
      addUpdateMethod("setBankAccountType", Account.BankAccountType.class);
      addUpdateMethod("transferFromBank", BigDecimal.class);
      addUpdateMethod("credit", BigDecimal.class);
      
      readOnlyMethods = new ArrayList<Method>();
      addReadOnlyMethod("getBalance");
      addReadOnlyMethod("transactionAverage");
   }

   @SuppressWarnings("rawtypes")
   private void addReadOnlyMethod(String name) throws Exception {
      Class[] noParm = new Class[] {};
      readOnlyMethods.add(Accountable.class.getDeclaredMethod(name, noParm));
   }

   @SuppressWarnings("rawtypes")
   private void addUpdateMethod(String name, Class parmClass) throws Exception {
      updateMethods.add(Accountable.class.getDeclaredMethod(name, parmClass));      
   }
   
   @Test
   public void testUpdateAccess() throws Exception {
      Accountable account = AccountFactory.create(Permission.UPDATE);
      for (Method method: readOnlyMethods)
         verifyNoException(method, account);
      for (Method method: updateMethods)
         verifyNoException(method, account);
   }
   
   @Test
   public void testReadOnlyAccess() throws Exception {
      Accountable account = AccountFactory.create(Permission.READ_ONLY);
      for (Method method: updateMethods)
         verifyException(PermissionException.class, method, account);
      for (Method method: readOnlyMethods)
         verifyNoException(method, account);
   }
   
   @SuppressWarnings("rawtypes")
   private void verifyException(
         Class exceptionType, Method method, Object object) throws Exception {
      try {
         method.invoke(object, nullParmsFor(method));
         fail("expected exception");
      }
      catch (InvocationTargetException e) {
         assertEquals("expected exception", exceptionType, e.getCause().getClass());
      }
   }
   
   private void verifyNoException(Method method, Object object) throws Exception {
      try {
         method.invoke(object, nullParmsFor(method));
      }
      catch (InvocationTargetException e) {
         assertFalse("unexpected permission exception", PermissionException.class == e.getCause().getClass());
      }
   }

   private Object[] nullParmsFor(Method method) {
      return new Object[method.getParameterTypes().length];
   }

}
