package de.collections;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StapelTest {
	
	private Stapel objectUnderTest;
	
	@Before
	public void setUp() {
		objectUnderTest = new Stapel();
	}

	@Test
	public void isEmpty_beiLeeremStapel_returnsTrue() {
		assertTrue(objectUnderTest.isEmpty());
	}

	@Test
	public void isEmpty_beiNichtLeeremStapel_returnsFalse() throws Exception{
		objectUnderTest.push(new Object());
		boolean ergebnis = objectUnderTest.isEmpty();
		assertFalse(ergebnis);
	}
	
	@Test
	public void isEmpty_beiWiederLeeremStapel_returnsTrue() throws Exception{
		objectUnderTest.push(new Object());
		objectUnderTest.pop();
		
		boolean ergebnis = objectUnderTest.isEmpty();
		assertTrue(ergebnis);
	}
	
	@Test
	public void push_HappyDay_success() throws Exception{
		final Object object = new Object();
		objectUnderTest.push(object);
		
		Object ergebnis = objectUnderTest.pop();
		
		
		assertSame(object, ergebnis);
		
	}

	@Test
	public void push_FillUpToLimit_noExceptionThrown() throws Exception{
		final Object object = new Object();
		
		
		fillUpToLimit(object);
	}

	@Test
	public void push_Overflow_throwsStapelException() throws Exception{
		try {
			final Object object = new Object();
			
			
			fillUpToLimit(object);
			objectUnderTest.push(object);
			fail("Upps");
		} catch (StapelException e) {
			assertEquals("Overflow", e.getMessage());
		}
		
	}
	@Test(expected = StapelException.class)
	public void push_OverflowVariante2_throwsStapelException() throws Exception{
		
			final Object object = new Object();
			
			
			fillUpToLimit(object);
			objectUnderTest.push(object);
			
		
		
	}

	private void fillUpToLimit(final Object object) throws StapelException {
		for (int i = 0; i < 10; i++) {
			objectUnderTest.push(object);
		}
	}

}
