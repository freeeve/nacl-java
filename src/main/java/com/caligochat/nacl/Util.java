package com.caligochat.nacl;

import javax.xml.bind.DatatypeConverter;

public class Util {
	public static void printHex(String s, byte b[]) {
		System.out.println(s + " " + DatatypeConverter.printHexBinary(b).toLowerCase());
	}

	public static void printHex(String s, byte b[], int num) {
		System.out.println(s + " " + DatatypeConverter.printHexBinary(b).substring(0, num).toLowerCase());
	}
}
