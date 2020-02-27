package de.tiere.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.tiere.Schwein;


public class SchweinTest {
	
	private Schwein objectUnderTest;
	
	@Before 
	public void setup() {
		objectUnderTest = new Schwein();
	}

	@Test
	public void ctor_Default_correctInitialisation() {
		assertEquals("nobody", objectUnderTest.getName());
		assertEquals(10, objectUnderTest.getGewicht());
		
	}
	
	@Test
	public void ctor_OverrideWithNameParameter_correctInitialisation() {
		String piggy = "Miss Piggy";
		objectUnderTest = new Schwein(piggy);
		assertEquals(piggy, objectUnderTest.getName());
		assertEquals(10, objectUnderTest.getGewicht());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ctor_OverrideWithWrongNameParameter_throwsIllegalArgumentException() {
		String elsa = "elsa";
		objectUnderTest = new Schwein(elsa);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setName_NullValue_throwsIllegalArgumentException () {
		objectUnderTest.setName(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setName_WrongNameElsa_throwsIllegalArgumentException () {
		objectUnderTest.setName("Elsa");
	}
	
	@Test
	public void setName_ValidName_success () {
		objectUnderTest.setName("Piggy");
		assertEquals("Piggy", objectUnderTest.getName());
	}
	
	@Test
	public void fressen_ProperUse_success () {
		objectUnderTest.fressen();
		assertEquals(11, objectUnderTest.getGewicht());
	}
	
	
	
	
}
