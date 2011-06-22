package com.tonyyuzhang.sis.studentinfo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PerformanceTest.class,
	BasicGradingStrategyTest.class,
	CourseSessionTest.class,
	DateUtilTest.class,
	HonorsGradingStrategyTest.class,
	ScorerTest.class,
	StudentTest.class,
	StudentDirectoryTest.class,
	CourseTest.class,
	AccountTest.class,
	CourseCatalogTest.class,
	StudentDirectoryTest.class,
	})
public class AllTests {
}
