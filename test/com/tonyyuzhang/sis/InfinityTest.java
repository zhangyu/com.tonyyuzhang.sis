package com.tonyyuzhang.sis;

import org.junit.Test;
import static org.junit.Assert.*;

public class InfinityTest {
	final float tolerance = 0.5f;
	final float x = 1f;
	
	@Test
	public void testInfinity() {
		assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY * 100, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY * -1, tolerance);
		
		assertEquals(Float.POSITIVE_INFINITY, x / 0f, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY, x / -0f, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY, -x / 0f, tolerance);
		
		assertEquals(0f, x / Float.POSITIVE_INFINITY, tolerance);
		assertEquals(-0f, x / Float.NEGATIVE_INFINITY, tolerance);
		assertEquals(x, x % Float.POSITIVE_INFINITY, tolerance);
		
		assertTrue(Float.isNaN(0f / 0f));
		assertTrue(Float.isNaN(0f % 0f));
		
		assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY / x, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY / x, tolerance);
		
		//interesting fact!!!
		assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % x));
		assertTrue(Float.isNaN(Float.POSITIVE_INFINITY / Float.POSITIVE_INFINITY));
		assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % Float.POSITIVE_INFINITY));
		assertTrue(Float.isNaN(Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY));
		assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % Float.NEGATIVE_INFINITY));
		assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY));
		assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY % Float.POSITIVE_INFINITY));
		assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY / Float.NEGATIVE_INFINITY));
		assertTrue(Float.isNaN(Float.NEGATIVE_INFINITY % Float.NEGATIVE_INFINITY));
	}
}
