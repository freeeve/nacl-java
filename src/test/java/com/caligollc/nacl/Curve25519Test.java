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

		for (int i = 0; i < 200; i++) {
			out = Curve25519.scalarBaseMult(in);
			byte temp[] = in;
			in = out;
			out = temp;
		}
		assertEquals(expectedHex, DatatypeConverter.printHexBinary(in).toLowerCase());
	}

	@Test
	public void TestFeSquareFail() {
		long startarr[] = {12562720, 6296353, 54578601, 16746890, -54591682, -18847443, 46849294, 26819245, -35365487, -3788969};
		Curve25519.FieldElement start = new Curve25519.FieldElement(startarr);
		long expectedarr[] = {19358458, -14374687, 30832724, 1573657, 30534596, 6957402, 18251160, 11463428, -8323465, -4774430};
		Curve25519.FieldElement expected = new Curve25519.FieldElement(expectedarr);
		Curve25519.FieldElement square = start.square();
		assertEquals(expected, square);
	}
}