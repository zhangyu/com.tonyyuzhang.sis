package com.tonyyuzhang.sis.util;

import org.junit.Test;
import static org.junit.Assert.*;
import com.tonyyuzhang.sis.testing.TestClass;

@TestClass
public class PasswordGeneratorTest {
	@Test
	public void testGeneratePassword() {
		PasswordGenerator generator = new PasswordGenerator();
		generator.setRandom(new MockRandom('A'));
		assertEquals("ABCDEFGH", generator.generatePassword());
		generator.setRandom(new MockRandom('C'));
		assertEquals("CDEFGHIJ", generator.generatePassword());
	}
	
	class MockRandom extends java.util.Random {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int i;
		MockRandom(char startCharValue) {
			i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
		}
		@Override
		protected int next(int bits) {
			return i++;
		}
	}
}
