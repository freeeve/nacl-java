package com.caligochat.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;

/**
 * Created by wfreeman on 2/18/15.
 */
public class SalsaTest {
	@Test
	public void TestHSalsa20() {
		byte zeros[] = new byte[16];
		for(int i = 0; i < zeros.length; i++) {
			zeros[i] = 0;
		}
		byte sharedKey[] = DatatypeConverter.parseHexBinary("1e038d56c5777706409e317b9a0459c70a15ccfccbf2be5b26248641921b8632");
		sharedKey = Salsa.HSalsa20(zeros, sharedKey, Salsa.SIGMA);
		String expected = "b69bfe4e687580ee3df99689991c4e2081d8426853d42a483d503ccc769d0480";
		assertEquals(expected, DatatypeConverter.printHexBinary(sharedKey).toLowerCase());
	}
}
