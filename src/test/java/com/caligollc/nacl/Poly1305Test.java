package com.caligollc.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;

/**
 * Created by wfreeman on 2/17/15.
 */
public class Poly1305Test {
	@Test
	public void TestVerifyA() {
		byte clear[] = "Hello world!".getBytes();
		byte key[] = "this is 32-byte key for Poly1305".getBytes();
		byte expected[] = {(byte)0xa6, (byte)0xf7, (byte)0x45, (byte)0x00, (byte)0x8f, (byte)0x81, (byte)0xc9, (byte)0x16, (byte)0xa2, (byte)0x0d, (byte)0xcc, (byte)0x74, (byte)0xee, (byte)0xf2, (byte)0xb2, (byte)0xf0};
		byte out[] = Poly1305.sum(clear, key);
		assertEquals(DatatypeConverter.printHexBinary(expected), DatatypeConverter.printHexBinary(out));
	}

	@Test
	public void TestVerifyB() {
		byte clear[] = new byte[2007];
		for(int i = 0; i < clear.length; i++){
			clear[i] = 0;
		}
		byte key[] = "this is 32-byte key for Poly1305".getBytes();
		byte expected[] = {(byte)0xda, (byte)0x84, (byte)0xbc, (byte)0xab, (byte)0x02, (byte)0x67, 0x6c, 0x38, (byte)0xcd, (byte)0xb0, 0x15, 0x60, 0x42, 0x74, (byte)0xc2, (byte)0xaa};
		byte out[] = Poly1305.sum(clear, key);
		assertEquals(DatatypeConverter.printHexBinary(expected), DatatypeConverter.printHexBinary(out));
	}

	@Test
	public void TestVerifyEx() {
		String expected = "9de86f0c1999b99146c24c36a41c66c6";
		byte key[] = DatatypeConverter.parseHexBinary("c9d5f8b6d6964d8a9bbcc891d50ef11814d75249ce8c47dafe9322c4589b301d");
		byte data[] = DatatypeConverter.parseHexBinary("14d2953d5df9044c146e3ae11ff925c0ec35002c99fc50582e59b4b745d6bbe71f3d56d9eff86e07778fb90d5744160b5dc2571b0554035a7646e68581e5ed64ae22c1b9f7bd994f8aca2039533e4aad1e1ade0a8e768223aa9c57881d644daba9a1ba09ef9d160d1de148194a8c9d67b45e6572cc57c000d9");
		byte result[] = Poly1305.sum(data, key);
		assertEquals(expected, DatatypeConverter.printHexBinary(result).toLowerCase());
	}
}
