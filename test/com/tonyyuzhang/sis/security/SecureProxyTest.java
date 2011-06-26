package com.tonyyuzhang.sis.security;


import static org.junit.Assert.*;

import java.lang.reflect.*;

import org.junit.Before;
import org.junit.Test;

public class SecureProxyTest {
   private static final String secureMethodName = "secure";
   private static final String insecureMethodName = "insecure";
   private Object object;
   private SecureProxy proxy;
   private boolean secureMethodCalled;
   private boolean insecureMethodCalled;
   
   @Before
   public void setUp() throws Exception {
      object = new Object() {
         @SuppressWarnings("unused")
         public void secure() {
            secureMethodCalled = true;
         }
         @SuppressWarnings("unused")
         public void insecure() {
            insecureMethodCalled = true;
         }
      };
      proxy = new SecureProxy(object, secureMethodName);
   }
   
   @Test
   public void testSecureMethod() throws Throwable {
      Method secureMethod = 
         object.getClass().getDeclaredMethod(
            secureMethodName, new Class[]{});
      try {
         proxy.invoke(proxy, secureMethod, new Object[]{});
         fail("expected PermissionException");
      }
      catch (PermissionException expected) {
         assertFalse(secureMethodCalled);
      }
   }
   
   @Test
   public void testInsecureMethod() throws Throwable {
      Method insecureMethod = 
         object.getClass().getDeclaredMethod(
            insecureMethodName, new Class[]{});
      proxy.invoke(proxy, insecureMethod, new Object[]{});
      assertTrue(insecureMethodCalled);
   }

}
