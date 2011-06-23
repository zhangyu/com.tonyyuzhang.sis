package com.tonyyuzhang.sis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	com.tonyyuzhang.sis.report.AllTests.class, 
	com.tonyyuzhang.sis.studentinfo.AllTests.class,
	com.tonyyuzhang.sis.summer.AllTests.class,
	com.tonyyuzhang.sis.ui.AllTests.class,
	com.tonyyuzhang.sis.db.AllTests.class,
	com.tonyyuzhang.sis.util.AllTests.class,
})
public class AllTests {
}
