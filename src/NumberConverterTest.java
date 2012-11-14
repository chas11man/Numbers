import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

public class NumberConverterTest
{
	private NumberConverter nc;
	
	@Before
	public void setupNumberConverter() {
		nc = new NumberConverter();
	}
	
	private List<String> stringToList(String input) {
		List<String> retList = new ArrayList<String>(Arrays.asList(input.split(",")));
		return retList;
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
	public void testOne() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method one = NumberConverter.class.getDeclaredMethod("one", String.class);
		one.setAccessible(true);
		
		assertEquals(1, one.invoke(nc, "one"));
		assertEquals(2, one.invoke(nc, "two"));
		assertEquals(3, one.invoke(nc, "three"));
		assertEquals(4, one.invoke(nc, "four"));
		assertEquals(5, one.invoke(nc, "five"));
		assertEquals(6, one.invoke(nc, "six"));
		assertEquals(7, one.invoke(nc, "seven"));
		assertEquals(8, one.invoke(nc, "eight"));
		assertEquals(9, one.invoke(nc, "nine"));
		
		assertEquals(0, one.invoke(nc, "twenty"));
		assertEquals(0, one.invoke(nc, "hundred"));
		assertEquals(0, one.invoke(nc, "negative"));
	}
	
	@Test
	public void testTeen() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method teen = NumberConverter.class.getDeclaredMethod("teen", String.class);
		teen.setAccessible(true);
		assertEquals(10, teen.invoke(nc, "ten"));
		assertEquals(11, teen.invoke(nc, "eleven"));
		assertEquals(12, teen.invoke(nc, "twelve"));
		assertEquals(13, teen.invoke(nc, "thirteen"));
		assertEquals(14, teen.invoke(nc, "fourteen"));
		assertEquals(15, teen.invoke(nc, "fifteen"));
		assertEquals(16, teen.invoke(nc, "sixteen"));
		assertEquals(17, teen.invoke(nc, "seventeen"));
		assertEquals(18, teen.invoke(nc, "eighteen"));
		assertEquals(19, teen.invoke(nc, "nineteen"));
		
		assertEquals(0, teen.invoke(nc, "one"));
		assertEquals(0, teen.invoke(nc, "thousand"));
		assertEquals(0, teen.invoke(nc, "seventy"));
		assertEquals(0, teen.invoke(nc, "negative"));
	}
	
	@Test
	public void testTen() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method ten = NumberConverter.class.getDeclaredMethod("ten", String.class);
		ten.setAccessible(true);
		
		assertEquals(20, ten.invoke(nc, "twenty"));
		assertEquals(30, ten.invoke(nc, "thirty"));
		assertEquals(40, ten.invoke(nc, "forty"));
		assertEquals(50, ten.invoke(nc, "fifty"));
		assertEquals(60, ten.invoke(nc, "sixty"));
		assertEquals(70, ten.invoke(nc, "seventy"));
		assertEquals(80, ten.invoke(nc, "eighty"));
		assertEquals(90, ten.invoke(nc, "ninety"));
		
		assertEquals(0, ten.invoke(nc, "ten"));
		assertEquals(0, ten.invoke(nc, "million"));
		assertEquals(0, ten.invoke(nc, "fifteen"));
		assertEquals(0, ten.invoke(nc, "naught"));
	}
	
	@Test
	public void testTwoDigitTypeCheck() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method twoDigitTypeCheck = NumberConverter.class.getDeclaredMethod("twoDigitTypeCheck", List.class);
		twoDigitTypeCheck.setAccessible(true);
		
		assertEquals(11, twoDigitTypeCheck.invoke(nc, stringToList("eleven")));
		assertEquals(1, twoDigitTypeCheck.invoke(nc, stringToList("one")));
		assertEquals(21, twoDigitTypeCheck.invoke(nc, stringToList("twenty,one")));
		assertEquals(80, twoDigitTypeCheck.invoke(nc, stringToList("eighty")));
		
		//Will not be allowed by ValidNumberCheck
		assertEquals(16, twoDigitTypeCheck.invoke(nc, stringToList("eleven,five")));
		assertEquals(0, twoDigitTypeCheck.invoke(nc, stringToList("hundred")));
		assertEquals(0, twoDigitTypeCheck.invoke(nc, stringToList("bad")));
	}
	
	@Test
	public void testPrefix() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method prefix = NumberConverter.class.getDeclaredMethod("prefix", List.class);
		prefix.setAccessible(true);
		
		assertEquals(0, prefix.invoke(nc, stringToList("")));
		assertEquals(5, prefix.invoke(nc, stringToList("five")));
		assertEquals(16, prefix.invoke(nc, stringToList("sixteen")));
		assertEquals(27, prefix.invoke(nc, stringToList("twenty,seven")));
		assertEquals(100, prefix.invoke(nc, stringToList("one,hundred")));
		assertEquals(201, prefix.invoke(nc, stringToList("two,hundred,one")));
		assertEquals(312, prefix.invoke(nc, stringToList("three,hundred,twelve")));
		assertEquals(423, prefix.invoke(nc, stringToList("four,hundred,twenty,three")));
		assertEquals(530, prefix.invoke(nc, stringToList("five,hundred,thirty")));
		
		//Will not be allowed by ValidNumberCheck
		assertEquals(0, prefix.invoke(nc, stringToList("eleven,hundred")));
		assertEquals(230, prefix.invoke(nc, stringToList("one,hundred,fifty,eighty")));
	}
	
	@Test
	public void testHundred() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method hundred = NumberConverter.class.getDeclaredMethod("hundred", List.class);
		hundred.setAccessible(true);
		
		assertEquals(600, hundred.invoke(nc, stringToList("six,hundred")));
		assertEquals(800, hundred.invoke(nc, stringToList("eight")));
		//Will not be allowed by ValidNumberCheck
		assertEquals(0, hundred.invoke(nc, stringToList("eleven")));
		assertEquals(0, hundred.invoke(nc, stringToList("twenty")));
		assertEquals(0, hundred.invoke(nc, stringToList("hundred")));
		assertEquals(0, hundred.invoke(nc, stringToList("zero")));
	}
	
	@Test
	public void testThousand() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method thousand = NumberConverter.class.getDeclaredMethod("thousand", List.class);
		thousand.setAccessible(true);
		
		assertEquals(600000, thousand.invoke(nc, stringToList("six,hundred")));
		assertEquals(8000, thousand.invoke(nc, stringToList("eight")));
		assertEquals(11000, thousand.invoke(nc, stringToList("eleven")));
		assertEquals(20000, thousand.invoke(nc, stringToList("twenty")));
		assertEquals(609000, thousand.invoke(nc, stringToList("six,hundred,nine")));
		assertEquals(712000, thousand.invoke(nc, stringToList("seven,hundred,twelve")));
		assertEquals(850000, thousand.invoke(nc, stringToList("eight,hundred,fifty")));
		assertEquals(967000, thousand.invoke(nc, stringToList("nine,hundred,sixty,seven")));
		//Will not be allowed by ValidNumberCheck
		assertEquals(0, thousand.invoke(nc, stringToList("zero")));
	}
	
	@Test
	public void testMillion() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method million = NumberConverter.class.getDeclaredMethod("million", List.class);
		million.setAccessible(true);
		
		assertEquals(600000000, million.invoke(nc, stringToList("six,hundred")));
		assertEquals(8000000, million.invoke(nc, stringToList("eight")));
		assertEquals(11000000, million.invoke(nc, stringToList("eleven")));
		assertEquals(20000000, million.invoke(nc, stringToList("twenty")));
		assertEquals(609000000, million.invoke(nc, stringToList("six,hundred,nine")));
		assertEquals(712000000, million.invoke(nc, stringToList("seven,hundred,twelve")));
		assertEquals(850000000, million.invoke(nc, stringToList("eight,hundred,fifty")));
		assertEquals(967000000, million.invoke(nc, stringToList("nine,hundred,sixty,seven")));
		//Will not be allowed by ValidNumberCheck
		assertEquals(0, million.invoke(nc, stringToList("zero")));
	}
	
	@Test
	public void testConvertNumber() {
		
	}
}