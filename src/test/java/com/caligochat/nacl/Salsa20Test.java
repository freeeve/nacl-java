package com.caligochat.nacl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by wfreeman on 2/11/15.
 */
public class Salsa20Test {

	@Test
	public void TestXSalsa20A() throws NaclException {
		byte key[] = "this is 32-byte key for xsalsa20".getBytes();
		byte nonce[] = "24-byte nonce for xsalsa".getBytes();
		byte outResult[] = {0x00, 0x2d, 0x45, 0x13, (byte)0x84, 0x3f, (byte)0xc2, 0x40, (byte)0xc4, 0x01, (byte)0xe5, 0x41};
		byte in[] = "Hello world!".getBytes();
		byte out[];
		out = Salsa20.XORKeyStream(in, nonce, key);
		assertArrayEquals(outResult, out);
	}

}
