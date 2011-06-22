package com.tonyyuzhang.sis.studentinfo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tonyyuzhang.sis.util.DateUtil;

import java.util.*;

public class DateUtilTest {

	@Test
	public void testCreateDate() {
		Date date = DateUtil.createDate(2000,1,1);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		  
		assertEquals(2000, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
	}

}
