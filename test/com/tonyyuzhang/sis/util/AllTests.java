package com.tonyyuzhang.sis.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	IOUtilTest.class, 
	ParityCheckerTest.class,
	PasswordGeneratorTest.class,
	})
public class AllTests {
}

