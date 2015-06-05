package com.caligochat.nacl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wfreeman on 2/18/15.
 */
public class SubtleTest {
	@Test
	public void TestSubtleByteEq() {
		byte b = 17;
		byte c = 37;
		assertFalse(Subtle.constantTimeByteEq(b, c));
		b = (byte)0xFF;
		c = (byte)0xFF;
		boolean ret = Subtle.constantTimeByteEq(b, c);
		assertTrue(ret);
		b = (byte)0x0;
		c = (byte)0x0;
		assertTrue(Subtle.constantTimeByteEq(b, c));
	}

	@Test
	public void TestSubtleByteArrayEq() {
		byte b[] = {17};
		byte c[] = {37};
		assertFalse(Subtle.constantTimeCompare(b, c));
		byte b2[] = {};
		byte c2[] = {37};
		assertFalse(Subtle.constantTimeCompare(b2, c2));
		byte b3[] = {1,2,3,4,(byte)0xFF};
		byte c3[] = {1,2,3,4,(byte)0xFF};
		assertTrue(Subtle.constantTimeCompare(b3, c3));
	}
}
