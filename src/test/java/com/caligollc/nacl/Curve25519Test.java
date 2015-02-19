package com.caligollc.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;

/**
 * Created by wfreeman on 2/15/15.
 */
public class Curve25519Test {
	@Test
	public void TestScalarBaseMult() {
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
	public void TestScalarBaseMultEx1() {
		String expectedHex = "ae1fd4148083d221e177214cb6b5e4788d90bb8528202bfa515ec1db91d5400c";
		byte in[] = DatatypeConverter.parseHexBinary("b1b153f1afc12a7eebe99a68aea06640adab3e025a34af5f321690c05ad400da");
		byte out[] = Curve25519.scalarBaseMult(in);
		assertEquals(expectedHex, DatatypeConverter.printHexBinary(out).toLowerCase());
	}

	@Test
	public void TestScalarMult() {
		byte pubkey[] = DatatypeConverter.parseHexBinary("0c0c9e1e414642cc8192277104254850a8b0857272853e8814f462cfa1c8ed5e");
		byte privkey[] = DatatypeConverter.parseHexBinary("71e09479afae7e63db479fcfe82d25ff0fa48f80acf909d16a00d11a26715628");
		String expectedHex = "1e038d56c5777706409e317b9a0459c70a15ccfccbf2be5b26248641921b8632";
		byte result[] = Curve25519.scalarMult(privkey, pubkey);
		assertEquals(expectedHex, DatatypeConverter.printHexBinary(result).toLowerCase());
	}

	@Test
	public void TestScalarMultEx() {
		byte pubkey[] = DatatypeConverter.parseHexBinary("3d9bf48274750db0a7204fe7703c8d455eb5264bbf42b2f2ac25bf7babec3f38");
		byte privkey[] = DatatypeConverter.parseHexBinary("bec8e07d1f46e04560abbc01e0f4924b0fc349a406680aee691e5c7104ef6116");
		String expectedHex = "82b437552b08a842422767956d250847c7eb9b6e95484f3d0f10052db249325f";
		byte result[] = Curve25519.scalarMult(privkey, pubkey);
		assertEquals(expectedHex, DatatypeConverter.printHexBinary(result).toLowerCase());
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