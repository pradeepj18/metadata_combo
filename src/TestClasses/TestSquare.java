package TestClasses;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestSquare {

	@Test
	public void test() {
		assertEquals(25,Square.getSquare(5));
		 
	}

}
