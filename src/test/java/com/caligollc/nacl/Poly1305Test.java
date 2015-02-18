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
}
