package Prueba;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testDummyDesign {

	private DummyDesign dummy = new DummyDesign();

	@Test
	public void testIntegrante1() {
		Assert.assertEquals(1, dummy.integrante1());
	}

	@Test
	public void testIntegrante2() {
		Assert.assertEquals(2, dummy.integrante2());
	}

	@Test
	public void testIntegrante3() {
		// dummy = new DummyDesign();
		Assert.assertEquals(3, dummy.integrante3());
	}

	@Test
	public void testIntegrante4() {
		// dummy = new DummyDesign();
		Assert.assertEquals(4, dummy.integrante4());
	}

	@Test
	public void testIntegrante5() {
		Assert.assertEquals(5, dummy.integrante5());
	}

}
