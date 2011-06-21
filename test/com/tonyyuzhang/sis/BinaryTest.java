package com.tonyyuzhang.sis;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTest {
	@Test
	public void testBitAnd() {
		assertEquals(0, 0 & 0);
		assertEquals(0, 0 & 1);
		assertEquals(0, 1 & 0);
		assertEquals(1, 1 & 1);
		assertEquals(0x1, 0x3 & 0x5); // assertEquals(0001, 0011 & 0101)
	}
	
	@Test
	public void testBitOr() {
		assertEquals(0, 0 | 0);
		assertEquals(1, 1 | 0);
		assertEquals(1, 0 | 1);
		assertEquals(1, 1 | 1);
		assertEquals(0x7, 0x5 | 0x3);
	}
	
	@Test
	public void testBitXor() {
		assertEquals(0, 0 ^ 0);
		assertEquals(1, 1 ^ 0);
		assertEquals(1, 0 ^ 1);
		assertEquals(0, 1 ^ 1);
		assertEquals(0x6, 0x5 ^ 0x3);
	}
	
	@Test
	public void testNegation() {
		int x = 0x7FFFFFF1; 			// 0111 1111 1111 1111 1111 1111 1111 0001
		assertEquals(0x8000000E, ~x);
	}
}
