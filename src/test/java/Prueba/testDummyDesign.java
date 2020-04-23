package Prueba;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testDummyDesign {
	private DummyDesign dummy = new DummyDesign();
	
	
	@Test
	public void testIntegrante3() {
		//dummy = new DummyDesign();
		Assert.assertEquals(3, dummy.integrante3());
	}

}
