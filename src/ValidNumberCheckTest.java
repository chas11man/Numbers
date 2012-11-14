import static org.junit.Assert.*;

import java.lang.reflect.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ValidNumberCheckTest
{
	private ValidNumberCheck vnc;
	
	@Before
	public void ValidNumberCheckSetup() {
		vnc = new ValidNumberCheck();
	}
	
	@Test
	public void testValidWordGood() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method validWord = ValidNumberCheck.class.getDeclaredMethod("validWord", String.class);
		validWord.setAccessible(true);
		
		assertTrue((boolean)validWord.invoke(vnc, "one"));
		assertTrue((boolean)validWord.invoke(vnc, "two"));
		assertTrue((boolean)validWord.invoke(vnc, "three"));
		assertTrue((boolean)validWord.invoke(vnc, "four"));
		assertTrue((boolean)validWord.invoke(vnc, "five"));
		assertTrue((boolean)validWord.invoke(vnc, "six"));
		assertTrue((boolean)validWord.invoke(vnc, "seven"));
		assertTrue((boolean)validWord.invoke(vnc, "eight"));
		assertTrue((boolean)validWord.invoke(vnc, "nine"));
		assertTrue((boolean)validWord.invoke(vnc, "ten"));
		assertTrue((boolean)validWord.invoke(vnc, "eleven"));
		assertTrue((boolean)validWord.invoke(vnc, "twelve"));
		assertTrue((boolean)validWord.invoke(vnc, "thirteen"));
		assertTrue((boolean)validWord.invoke(vnc, "fourteen"));
		assertTrue((boolean)validWord.invoke(vnc, "fifteen"));
		assertTrue((boolean)validWord.invoke(vnc, "sixteen"));
		assertTrue((boolean)validWord.invoke(vnc, "seventeen"));
		assertTrue((boolean)validWord.invoke(vnc, "eighteen"));
		assertTrue((boolean)validWord.invoke(vnc, "nineteen"));
		assertTrue((boolean)validWord.invoke(vnc, "twenty"));
		assertTrue((boolean)validWord.invoke(vnc, "thirty"));
		assertTrue((boolean)validWord.invoke(vnc, "forty"));
		assertTrue((boolean)validWord.invoke(vnc, "fifty"));
		assertTrue((boolean)validWord.invoke(vnc, "sixty"));
		assertTrue((boolean)validWord.invoke(vnc, "seventy"));
		assertTrue((boolean)validWord.invoke(vnc, "eighty"));
		assertTrue((boolean)validWord.invoke(vnc, "ninety"));
		assertTrue((boolean)validWord.invoke(vnc, "hundred"));
		assertTrue((boolean)validWord.invoke(vnc, "thousand"));
		assertTrue((boolean)validWord.invoke(vnc, "million"));
		assertTrue((boolean)validWord.invoke(vnc, "zero"));
		assertTrue((boolean)validWord.invoke(vnc, "naught"));
		assertTrue((boolean)validWord.invoke(vnc, "negative"));
		assertTrue((boolean)validWord.invoke(vnc, "minus"));
	}
	
	@Test
	public void testValidWordBad() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {
		Method validWord = ValidNumberCheck.class.getDeclaredMethod("validWord", String.class);
		validWord.setAccessible(true);
		
		assertFalse((boolean)validWord.invoke(vnc, "aasdfa"));
	}
}
