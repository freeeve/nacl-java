package com.caligollc.nacl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by wfreeman on 2/11/15.
 */
public class Salsa20Test {

	/* I think we're missing data for this test...
	@Test
	public void TestSalsa20A() throws NaclException {
		byte key[] = DatatypeConverter.parseHexBinary("0053A6F94C9FF24598EB3E91E4378ADD3083D6297CCF2275C81B6EC11467BA0D");
		byte iv[] = DatatypeConverter.parseHexBinary("0D74DB42A91077DE");
		int numBytes = 131072;
		byte xorResult[] = DatatypeConverter.parseHexBinary("C349B6A51A3EC9B712EAED3F90D8BCEE69B7628645F251A996F55260C62EF31FD6C6B0AEA94E136C9D984AD2DF3578F78E457527B03A0450580DD874F63B1AB9");
		byte in[] = new byte[131072];
		for(int i = 0; i < in.length; i++) {
			in[i] = 0;
		}
		byte out[];
		assertTrue("numBytes is not a multiple of 64", numBytes%64 == 0);

		byte xor[] = new byte[64];
		out = Salsa20.XORKeyStream(in, iv, key);
		String outstr = DatatypeConverter.printHexBinary(out);
		System.out.println(outstr);
		while (out.length > 0) {
			for (int i = 0; i < 64; i++) {
				xor[i] ^= out[i];
			}
			byte temp[] = out.clone();
			out = new byte[temp.length-64];
			for (int i = 0; i < out.length - 64; i++) {
				out[i] = temp[i+64];
			}
		}
		assertArrayEquals(xorResult, xor);
	}
	*/

	@Test
	public void TestXSalsa20A() throws NaclException {
		byte key[] = "this is 32-byte key for xsalsa20".getBytes();
		byte nonce[] = "24-byte nonce for xsalsa".getBytes();
		byte outResult[] = {0x00, 0x2d, 0x45, 0x13, (byte)0x84, 0x3f, (byte)0xc2, 0x40, (byte)0xc4, 0x01, (byte)0xe5, 0x41};
		byte in[] = "Hello world!".getBytes();
		byte out[];
		//System.out.println("in " + DatatypeConverter.printHexBinary(in));
		out = Salsa20.XORKeyStream(in, nonce, key);
		//System.out.println("out " + DatatypeConverter.printHexBinary(out));
		assertArrayEquals(outResult, out);
	}

}
