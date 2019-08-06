package org.geogebra.common.main.exam;

import org.geogebra.common.BaseUnitTest;
import org.geogebra.common.jre.headless.AppCommon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExamEventsTest {

	private ExamEnvironment exam;
	
	@Before
	public void setup() {
		AppCommon app = BaseUnitTest.createAppCommon();
		exam = new ExamEnvironment(app);
	}

	@Test
	public void leavingAfterStartShouldTriggerRed() {
		exam.setStart(System.currentTimeMillis());
		exam.windowLeft();
		Assert.assertTrue(exam.isCheating());
	}

	@Test
	public void leavingBeforeStartShouldBeOK() {
		exam.windowLeft();
		exam.setStart(System.currentTimeMillis());
		Assert.assertFalse(exam.isCheating());
	}
}
