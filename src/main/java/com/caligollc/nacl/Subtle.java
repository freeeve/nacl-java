package com.caligollc.nacl;

/**
 * Created by wfreeman on 2/18/15.
 */
public class Subtle {
	public static boolean constantTimeCompare(byte x[], byte y[]) {
		if (x.length != y.length) {
			return false;
		}
		byte v = 0;
		for (int i = 0; i < x.length; i++) {
			v |= x[i] ^ y[i];
		}
		return constantTimeByteEq(v, (byte)0);
	}
	public static boolean constantTimeByteEq(byte x, byte y) {
		byte z = (byte)~(x ^ y);
		z &= (byte)(z >> 4);
		z &= (byte)(z >> 2);
		z &= (byte)(z >> 1);
		return z == -1;
	}
}
