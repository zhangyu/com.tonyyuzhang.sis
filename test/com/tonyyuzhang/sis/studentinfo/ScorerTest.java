package com.tonyyuzhang.sis.studentinfo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScorerTest {

	@Test
	public void testCaptureScore() {
		Scorer scorer = new Scorer();
		assertEquals(75, scorer.score("75"));
	}

	@Test
	public void testIsValid() {
		Scorer scorer = new Scorer();
		assertTrue(scorer.isValid("123"));
		assertFalse(scorer.isValid("asda"));
	}
	
	@Test
	public void testBadScoreEntered() {
		Scorer scorer = new Scorer();
		try {
			scorer.score("abd");
			fail("expected NumberFormatException on bad input");
		}
		catch (NumberFormatException success) {
		
		}
	}
}
