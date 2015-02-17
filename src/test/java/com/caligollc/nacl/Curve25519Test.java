package com.caligollc.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;

/**
 * Created by wfreeman on 2/15/15.
 */
public class Curve25519Test {
	@Test
	public void TestBaseScalarMult() {
		String expectedHex = "89161fde887b2b53de549af483940106ecc114d6982daa98256de23bdf77661a";
		byte a[] = new byte[32], b[] = new byte[32];
		byte in[] = a;
		byte out[] = b;
		a[0] = 1;

		for (int i = 0; i < 5; i++) {
			out = Curve25519.scalarBaseMult(in);
			byte temp[] = in;
			in = out;
			out = temp;
		}
		assertEquals(expectedHex, DatatypeConverter.printHexBinary(in).toLowerCase());
	}
}