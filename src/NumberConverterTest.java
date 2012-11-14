import static org.junit.Assert.*;

import java.lang.reflect.*;
import org.junit.Test;
import org.junit.Before;

public class NumberConverterTest
{
	private NumberConverter nc;
	
	@Before
	public void setupNumberConverter() {
		nc = new NumberConverter();
	}
	
	@Test
	public void testCheckIfTeen() {
		assertTrue(nc.checkIfTeen("ten"));
		assertTrue(nc.checkIfTeen("eleven"));
		assertTrue(nc.checkIfTeen("twelve"));
		assertTrue(nc.checkIfTeen("thirteen"));
		assertTrue(nc.checkIfTeen("fourteen"));
		assertTrue(nc.checkIfTeen("fifteen"));
		assertTrue(nc.checkIfTeen("sixteen"));
		assertTrue(nc.checkIfTeen("seventeen"));
		assertTrue(nc.checkIfTeen("eighteen"));
		assertTrue(nc.checkIfTeen("nineteen"));
		assertFalse(nc.checkIfTeen("hundred"));
		assertFalse(nc.checkIfTeen("seven"));
		assertFalse(nc.checkIfTeen("thirty"));
	}
	
	@Test
	public void testCheckIfOne() {
		assertTrue(nc.checkIfOne("one"));
		assertTrue(nc.checkIfOne("two"));
		assertTrue(nc.checkIfOne("three"));
		assertTrue(nc.checkIfOne("four"));
		assertTrue(nc.checkIfOne("five"));
		assertTrue(nc.checkIfOne("six"));
		assertTrue(nc.checkIfOne("seven"));
		assertTrue(nc.checkIfOne("eight"));
		assertTrue(nc.checkIfOne("nine"));
		assertFalse(nc.checkIfOne("fifteen"));
		assertFalse(nc.checkIfOne("twenty"));
		assertFalse(nc.checkIfOne("thousand"));
	}
	
	@Test
	public void testCheckIfNegative() {
		assertTrue(nc.checkIfNegative("negative"));
		assertTrue(nc.checkIfNegative("minus"));
		assertFalse(nc.checkIfNegative("five"));
		assertFalse(nc.checkIfNegative("naught"));
	}
	
	@Test
	public void testCheckIfZero() {
		assertTrue(nc.checkIfZero("zero"));
		assertTrue(nc.checkIfZero("naught"));
		assertFalse(nc.checkIfZero("seven"));
		assertFalse(nc.checkIfZero("negative"));
	}

	@Test
	public void testOne() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException
	{
		Method one = NumberConverter.class.getDeclaredMethod("one", String.class);
		one.setAccessible(true);
		
		assertEquals(one.invoke(nc, "one"), 1);
		assertEquals(one.invoke(nc, "two"), 2);
		assertEquals(one.invoke(nc, "three"), 3);
		assertEquals(one.invoke(nc, "four"), 4);
		assertEquals(one.invoke(nc, "five"), 5);
		assertEquals(one.invoke(nc, "six"), 6);
		assertEquals(one.invoke(nc, "seven"), 7);
		assertEquals(one.invoke(nc, "eight"), 8);
		assertEquals(one.invoke(nc, "nine"), 9);
		assertEquals(one.invoke(nc, "twenty"), 0);
		assertEquals(one.invoke(nc, "hundred"), 0);
		assertEquals(one.invoke(nc, "negative"), 0);
	}
}