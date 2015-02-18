package com.caligollc.nacl;

/**
 * Created by wfreeman on 2/11/15.
 */
public class Poly1305 {
	public static int TAG_SIZE = 16;

	private static double alpham80 = 0.00000000558793544769287109375d;
	private static double alpham48 = 24.0d;
	private static double alpham16 = 103079215104.0d;
	private static double alpha0   = 6755399441055744.0d;
	private static double alpha18  = 1770887431076116955136.0d;
	private static double alpha32  = 29014219670751100192948224.0d;
	private static double alpha50  = 7605903601369376408980219232256.0d;
	private static double alpha64  = 124615124604835863084731911901282304.0d;
	private static double alpha82  = 32667107224410092492483962313449748299776.0d;
	private static double alpha96  = 535217884764734955396857238543560676143529984.0d;
	private static double alpha112 = 35076039295941670036888435985190792471742381031424.0d;
	private static double alpha130 = 9194973245195333150150082162901855101712434733101613056.0d;
	private static double scale    = 0.0000000000000000000000000000000000000036734198463196484624023016788195177431833298649127735047148490821200539357960224151611328125d;
	private static double offset0  = 6755408030990331.0d;
	private static double offset1  = 29014256564239239022116864.0d;
	private static double offset2  = 124615283061160854719918951570079744.0d;
	private static double offset3  = 535219245894202480694386063513315216128475136.0d;

	private static long uint32(long x) {
		return 0xFFFFFFFF & x;
	}

	private static long int64(long x) {
		return x;
	}

	private static long uint64(long x) {
		return x;
	}

	private static double longBitsToDouble(long bits) {
		int s = ((bits >> 63) == 0) ? 1 : -1;
		int e = (int)((bits >> 52) & 0x7ffL);
		long m = (e == 0) ?
			(bits & 0xfffffffffffffL) << 1 :
			(bits & 0xfffffffffffffL) | 0x10000000000000L;
		return (double)s*(double)m*Math.pow(2, e-1075);
	}

	private static void debug(String name, long l) {
		System.out.printf("%s %d\n", name, l);
	}

	private static void debug(String name, double d) {
		System.out.printf("%s %d\n", name, Double.doubleToRawLongBits(d));
	}

	public static boolean verify(byte mac[], byte m[], byte key[]) {
		byte tmp[] = sum(m, key);
		return Subtle.constantTimeCompare(tmp, mac);
	}

	// Sum generates an authenticator for m using a one-time key and puts the
	// 16-byte result into out. Authenticating two different messages with the same
	// key allows an attacker to forge messages at will.
	public static byte[] sum(byte m[], byte key[]) {
		byte r[] = key.clone();
		byte s[] = new byte[16];
		for(int i = 0; i < s.length; i++) {
			s[i] = key[i+16];
		}

		double y7;
		double y6;
		double y1;
		double	y0        ;
		double	y5        ;
		double	y4        ;
		double	x7        ;
		double	x6        ;
		double	x1        ;
		double	x0        ;
		double	y3        ;
		double	y2        ;
		double	x5        ;
		double	r3lowx0   ;
		double	x4        ;
		double	r0lowx6   ;
		double	x3        ;
		double	r3highx0  ;
		double	x2        ;
		double	r0highx6  ;
		double	r0lowx0   ;
		double	sr1lowx6  ;
		double	r0highx0  ;
		double	sr1highx6 ;
		double	sr3low    ;
		double	r1lowx0   ;
		double	sr2lowx6  ;
		double	r1highx0  ;
		double	sr2highx6 ;
		double	r2lowx0   ;
		double	sr3lowx6  ;
		double	r2highx0  ;
		double	sr3highx6 ;
		double	r1highx4  ;
		double	r1lowx4   ;
		double	r0highx4  ;
		double	r0lowx4   ;
		double	sr3highx4 ;
		double	sr3lowx4  ;
		double	sr2highx4 ;
		double	sr2lowx4  ;
		double	r0lowx2   ;
		double	r0highx2  ;
		double	r1lowx2   ;
		double	r1highx2  ;
		double	r2lowx2   ;
		double	r2highx2  ;
		double	sr3lowx2  ;
		double	sr3highx2 ;
		double	z0        ;
		double	z1        ;
		double	z2        ;
		double	z3        ;
		long	m0        ;
		long	m1        ;
		long	m2        ;
		long	m3        ;
		long	m00       ;//uint32
		long	m01       ;//uint32
		long	m02       ;//uint32
		long	m03       ;//uint32
		long	m10       ;//uint32
		long	m11       ;//uint32
		long	m12       ;//uint32
		long	m13       ;//uint32
		long	m20       ;//uint32
		long	m21       ;//uint32
		long	m22       ;//uint32
		long	m23       ;//uint32
		long	m30       ;//uint32
		long	m31       ;//uint32
		long	m32       ;//uint32
		long	m33       ;//uint64
		long	lbelow2   ;//int32
		long	lbelow3   ;//int32
		long	lbelow4   ;//int32
		long	lbelow5   ;//int32
		long	lbelow6   ;//int32
		long	lbelow7   ;//int32
		long	lbelow8   ;//int32
		long	lbelow9   ;//int32
		long	lbelow10  ;//int32
		long	lbelow11  ;//int32
		long	lbelow12  ;//int32
		long	lbelow13  ;//int32
		long	lbelow14  ;//int32
		long	lbelow15  ;//int32
		long	s00       ;//uint32
		long	s01       ;//uint32
		long	s02       ;//uint32
		long	s03       ;//uint32
		long	s10       ;//uint32
		long	s11       ;//uint32
		long	s12       ;//uint32
		long	s13       ;//uint32
		long	s20       ;//uint32
		long	s21       ;//uint32
		long	s22       ;//uint32
		long	s23       ;//uint32
		long	s30       ;//uint32
		long	s31       ;//uint32
		long	s32       ;//uint32
		long	s33       ;//uint32
		long	bits32    ;//uint64
		long	f         ;//uint64
		long	f0        ;//uint64
		long	f1        ;//uint64
		long	f2        ;//uint64
		long	f3        ;//uint64
		long	f4        ;//uint64
		long	g         ;//uint64
		long g0;//uint64
		long	g1;//uint64
		long	g2        ;//uint64
		long g3;//uint64
		long	g4;//uint64

		long p = 0;

		int l = m.length;
		//debug("l", l);

		long r00 = 0xFF & r[0];
		//debug("r00", r00);
		long r01 = 0xFF & r[1];
		//debug("r01", r01);
		long r02 = 0xFF & r[2];
		//debug("r02", r02);
		long r0 = 2151;
		//debug("r0", r0);

		long r03 = 0xFF & r[3];
		//debug("r03", r03);
		r03 &= 15;
		//debug("r03", r03);
		r0 <<= 51;
		//debug("r0", r0);

		long r10 = 0xFF & r[4];
		//debug("r10", r10);
		r10 &= 252;
		//debug("r10", r10);
		r01 <<= 8;
		//debug("r01", r01);
		r0 += r00;
		//debug("r0", r0);

		long r11 = 0xFF & r[5];
		//debug("r11", r11);
		r02 <<= 16;
		//debug("r02", r02);
		r0 += r01;
		//debug("r0", r0);

		long r12 = 0xFF & r[6];
		//debug("r12", r12);
		r03 <<= 24;
		//debug("r03", r03);
		r0 += r02;
		//debug("r0", r0);

		long r13 = 0xFF & r[7];
		//debug("r13", r13);
		r13 &= 15;
		//debug("r13", r13);
		long r1 = 2215;
		//debug("r1", r1);
		r0 += r03;
		//debug("r0", r0);

		long d0 = r0;
		//debug("d0", d0);
		r1 <<= 51;
		//debug("r1", r1);
		long r2 = 2279;
		//debug("r2", r2);

		long r20 = 0xFF & r[8];
		//debug("r20", r20);
		r20 &= 252;
		//debug("r20", r20);
		r11 <<= 8;
		//debug("r11", r11);
		r1 += r10;
		//debug("r1", r1);

		long r21 = 0xFF & r[9];
		//debug("r21", r21);
		r12 <<= 16;
		//debug("r12", r12);
		r1 += r11;
		//debug("r1", r1);

		long r22 = 0xFF & r[10];
		//debug("r22", r22);
		r13 <<= 24;
		//debug("r13", r13);
		r1 += r12;
		//debug("r1", r1);

		long r23 = 0xFF & r[11];
		//debug("r23", r23);
		r23 &= 15;
		//debug("r23", r23);
		r2 <<= 51;
		//debug("r2", r2);
		r1 += r13;
		//debug("r1", r1);

		long d1 = r1;
		//debug("d1", d1);
		r21 <<= 8;
		//debug("r21", r21);
		r2 += r20;
		//debug("r2", r2);

		long r30 = 0xFF & r[12];
		//debug("r30", r30);
		r30 &= 252;
		//debug("r30", r30);
		r22 <<= 16;
		//debug("r22", r22);
		r2 += r21;
		//debug("r2", r2);

		long r31 = 0xFF & r[13];
		//debug("r31", r31);
		r23 <<= 24;
		//debug("r23", r23);
		r2 += r22;
		//debug("r2", r2);

		long r32 = 0xFF & r[14];
		//debug("r32", r32);
		r2 += r23;
		//debug("r2", r2);
		long r3 = 2343;
		//debug("r3", r3);

		long d2 = r2;
		//debug("d2", d2);
		r3 <<= 51;
		//debug("r3", r3);

		long r33 = 0xFF & r[15];
		//debug("r33", r33);
		r33 &= 15;
		//debug("r33", r33);
		r31 <<= 8;
		//debug("r31", r31);
		r3 += r30;
		//debug("r3", r3);

		r32 <<= 16;
		//debug("r32", r32);
		r3 += r31;
		//debug("r3", r3);

		r33 <<= 24;
		//debug("r33", r33);
		r3 += r32;
		//debug("r3", r3);

		r3 += r33;
		//debug("r3", r3);
		double h0 = alpha32 - alpha32;
		//debug("h0", h0);

		long d3 = r3;
		//debug("d3", d3);
		double h1 = alpha32 - alpha32;
		//debug("h1", h1);

		double h2 = alpha32 - alpha32;
		//debug("h2", h2);

		double h3 = alpha32 - alpha32;
		//debug("h3", h3);

		double h4 = alpha32 - alpha32;
		//debug("h4", h4);

		double r0low = Double.longBitsToDouble(d0);
		//debug("r0low", r0low);
		double h5 = alpha32 - alpha32;
		//debug("h5", h5);

		double r1low = longBitsToDouble(d1);
		//debug("r1low", r1low);
		double h6 = alpha32 - alpha32;
		//debug("h6", h6);

		double r2low = Double.longBitsToDouble(d2);
		//debug("r2low", r2low);
		double h7 = alpha32 - alpha32;
		//debug("h7", h7);

		r0low -= alpha0;
		//debug("r0low", r0low);

		r1low -= alpha32;
		//debug("r1low", r1low);

		r2low -= alpha64;
		//debug("r2low", r2low);

		double r0high = r0low + alpha18;
		//debug("r0high", r0high);

		double r3low = Double.longBitsToDouble(d3);
		//debug("r3low", r3low);

		double r1high = r1low + alpha50;
		//debug("r1high", r1high);
		double sr1low = scale * r1low;
		//debug("sr1low", sr1low);

		double r2high = r2low + alpha82;
		//debug("r2high", r2high);
		double sr2low = scale * r2low;
		//debug("sr2low", sr2low);

		r0high -= alpha18;
		//debug("r0high", r0high);
		double r0high_stack = r0high;
		//debug("r0high_stack", r0high_stack);

		r3low -= alpha96;
		//debug("r3low", r3low);

		r1high -= alpha50;
		//debug("r1high", r1high);
		double r1high_stack = r1high;
		//debug("r1high_stack", r1high_stack);

		double sr1high = sr1low + alpham80;
		//debug("sr1high", sr1high);

		r0low -= r0high;
		//debug("r0low", r0low);

		r2high -= alpha82;
		//debug("r2high", r2high);
		sr3low = scale * r3low;
		//debug("sr3low", sr3low);

		double sr2high = sr2low + alpham48;
		//debug("sr2high", sr2high);

		r1low -= r1high;
		//debug("r1low", r1low);
		double r1low_stack = r1low;
		//debug("r1low_stack", r1low_stack);

		sr1high -= alpham80;
		//debug("sr1high", sr1high);
		double sr1high_stack = sr1high;
		//debug("sr1high_stack", sr1high_stack);

		r2low -= r2high;
		//debug("r2low", r2low);
		double r2low_stack = r2low;
		//debug("r2low_stack", r2low_stack);

		sr2high -= alpham48;
		//debug("sr2high", sr2high);
		double sr2high_stack = sr2high;
		//debug("sr2high_stack", sr2high_stack);

		double r3high = r3low + alpha112;
		//debug("r3high", r3high);
		double r0low_stack = r0low;
		//debug("r0low_stack", r0low_stack);

		sr1low -= sr1high;
		//debug("sr1low", sr1low);
		double sr1low_stack = sr1low;
		//debug("sr1low_stack", sr1low_stack);

		double sr3high = sr3low + alpham16;
		//debug("sr3high", sr3high);
		double r2high_stack = r2high;
		//debug("r2high_stack", r2high_stack);

		sr2low -= sr2high;
		//debug("sr2low", sr2low);
		double sr2low_stack = sr2low;
		//debug("sr2low_stack", sr2low_stack);

		r3high -= alpha112;
		//debug("r3high", r3high);
		double r3high_stack = r3high;
		//debug("r3high_stack", r3high_stack);

		sr3high -= alpham16;
		//debug("sr3high", sr3high);
		double sr3high_stack = sr3high;
		//debug("sr3high_stack", sr3high_stack);

		r3low -= r3high;
		//debug("r3low", r3low);
		double r3low_stack = r3low;
		//debug("r3low_stack", r3low_stack);

		sr3low -= sr3high;
		//debug("sr3low", sr3low);
		double sr3low_stack = sr3low;
		//debug("sr3low_stack", sr3low_stack);



		if (!(l < 16)) {
			m00 = 0xFF & m[(int)p];
			//debug("m00", m00);
			m0 = 2151;
			//debug("m0", m0);

			m0 <<= 51;
			//debug("m0", m0);
			m1 = 2215;
			//debug("m1", m1);
			m01 = 0xFF & m[(int)p+1];
			//debug("m01", m01);

			m1 <<= 51;
			//debug("m1", m1);
			m2 = 2279;
			//debug("m2", m2);
			m02 = 0xFF & m[(int)p+2];
			//debug("m02", m02);

			m2 <<= 51;
			//debug("m2", m2);
			m3 = 2343;
			//debug("m3", m3);
			m03 = 0xFF & (m[(int)p+3]);
			//debug("m03", m03);

			m10 = 0xFF & (m[(int)p+4]);
			//debug("m10", m10);
			m01 <<= 8;
			//debug("m01", m01);
			m0 += int64(m00);
			//debug("m0", m0);

			m11 = 0xFF & (m[(int)p+5]);
			//debug("m11", m11);
			m02 <<= 16;
			//debug("m02", m02);
			m0 += int64(m01);
			//debug("m0", m0);

			m12 = 0xFF & (m[(int)p+6]);
			//debug("m12", m12);
			m03 <<= 24;
			//debug("m03", m03);
			m0 += int64(m02);
			//debug("m0", m0);

			m13 = 0xFF & (m[(int)p+7]);
			//debug("m13", m13);
			m3 <<= 51;
			//debug("m3", m3);
			m0 += int64(m03);
			//debug("m0", m0);

			m20 = 0xFF & (m[(int)p+8]);
			//debug("m20", m20);
			m11 <<= 8;
			//debug("m11", m11);
			m1 += int64(m10);
			//debug("m1", m1);

			m21 = 0xFF & (m[(int)p+9]);
			//debug("m21", m21);
			m12 <<= 16;
			//debug("m12", m12);
			m1 += int64(m11);
			//debug("m1", m1);

			m22 = 0xFF & (m[(int)p+10]);
			//debug("m22", m22);
			m13 <<= 24;
			//debug("m13", m13);
			m1 += int64(m12);
			//debug("m1", m1);

			m23 = 0xFF & (m[(int)p+11]);
			//debug("m23", m23);
			m1 += int64(m13);
			//debug("m1", m1);

			m30 = 0xFF & (m[(int)p+12]);
			//debug("m30", m30);
			m21 <<= 8;
			//debug("m21", m21);
			m2 += int64(m20);
			//debug("m2", m2);

			m31 = 0xFF & (m[(int)p+13]);
			//debug("m31", m31);
			m22 <<= 16;
			//debug("m22", m22);
			m2 += int64(m21);
			//debug("m2", m2);

			m32 = 0xFF & (m[(int)p+14]);
			//debug("m32", m32);
			m23 <<= 24;
			//debug("m23", m23);
			m2 += int64(m22);
			//debug("m2", m2);

			m33 = 0xFF & (m[(int)p+15]);
			//debug("m33", m33);
			m2 += int64(m23);
			//debug("m2", m2);

			d0 = m0;
			//debug("d0", d0);
			m31 <<= 8;
			//debug("m31", m31);
			m3 += int64(m30);
			//debug("m3", m3);

			d1 = m1;
			//debug("d1", d1);
			m32 <<= 16;
			//debug("m32", m32);
			m3 += int64(m31);
			//debug("m3", m3);

			d2 = m2;
			//debug("d2", d2);
			m33 += 256;
			//debug("m33", m33);

			m33 <<= 24;
			//debug("m33", m33);
			m3 += int64(m32);
			//debug("m3", m3);

			m3 += int64(m33);
			//debug("m3", m3);
			d3 = m3;
			//debug("d3", d3);

			p += 16;
			//debug("p", p);
			l -= 16;
			//debug("l", l);

			z0 = Double.longBitsToDouble(uint64(d0));
			//debug("z0", z0);

			z1 = Double.longBitsToDouble(uint64(d1));
			//debug("z1", z1);

			z2 = Double.longBitsToDouble(uint64(d2));
			//debug("z2", z2);

			z3 = Double.longBitsToDouble(uint64(d3));
			//debug("z3", z3);

			z0 -= alpha0;
			//debug("z0", z0);

			z1 -= alpha32;
			//debug("z1", z1);

			z2 -= alpha64;
			//debug("z2", z2);

			z3 -= alpha96;
			//debug("z3", z3);

			h0 += z0;
			//debug("h0", h0);

			h1 += z1;
			//debug("h1", h1);

			h3 += z2;
			//debug("h3", h3);

			h5 += z3;
			//debug("h5", h5);

			while (l >= 16) {
				//multiplyaddatleast16bytes:

				m2 = 2279;
				//debug("m2", m2);
				m20 = 0xFF & (m[(int)p+8]);
				//debug("m20", m20);
				y7 = h7 + alpha130;
				//debug("y7", y7);

				m2 <<= 51;
				//debug("m2", m2);
				m3 = 2343;
				//debug("m3", m3);
				m21 = 0xFF & (m[(int)p+9]);
				//debug("m21", m21);
				y6 = h6 + alpha130;
				//debug("y6", y6);

				m3 <<= 51;
				//debug("m3", m3);
				m0 = 2151;
				//debug("m0", m0);
				m22 = 0xFF & (m[(int)p+10]);
				//debug("m22", m22);
				y1 = h1 + alpha32;
				//debug("y1", y1);

				m0 <<= 51;
				//debug("m0", m0);
				m1 = 2215;
				//debug("m1", m1);
				m23 = 0xFF & (m[(int)p+11]);
				//debug("m23", m23);
				y0 = h0 + alpha32;
				//debug("y0", y0);

				m1 <<= 51;
				//debug("m1", m1);
				m30 = 0xFF & (m[(int)p+12]);
				//debug("m30", m30);
				y7 -= alpha130;
				//debug("y7", y7);

				m21 <<= 8;
				//debug("m21", m21);
				m2 += int64(m20);
				//debug("m2", m2);
				m31 = 0xFF & (m[(int)p+13]);
				//debug("m31", m31);
				y6 -= alpha130;
				//debug("y6", y6);

				m22 <<= 16;
				//debug("m22", m22);
				m2 += int64(m21);
				//debug("m2", m2);
				m32 = 0xFF & (m[(int)p+14]);
				//debug("m32", m32);
				y1 -= alpha32;
				//debug("y1", y1);

				m23 <<= 24;
				//debug("m23", m23);
				m2 += int64(m22);
				//debug("m2", m2);
				m33 = 0xFF & (m[(int)p+15]);
				//debug("m33", m33);
				y0 -= alpha32;
				//debug("y0", y0);

				m2 += int64(m23);
				//debug("m2", m2);
				m00 = 0xFF & (m[(int)p+0]);
				//debug("m00", m00);
				y5 = h5 + alpha96;
				//debug("y5", y5);

				m31 <<= 8;
				//debug("m31", m31);
				m3 += int64(m30);
				//debug("m3", m3);
				m01 = 0xFF & (m[(int)p+1]);
				//debug("m01", m01);
				y4 = h4 + alpha96;
				//debug("y4", y4);

				m32 <<= 16;
				//debug("m32", m32);
				m02 = 0xFF & (m[(int)p+2]);
				//debug("m02", m02);
				x7 = h7 - y7;
				//debug("x7", x7);
				y7 *= scale;
				//debug("y7", y7);

				m33 += 256;
				//debug("m33", m33);
				m03 = 0xFF & (m[(int)p+3]);
				//debug("m03", m03);
				x6 = h6 - y6;
				//debug("x6", x6);
				y6 *= scale;
				//debug("y6", y6);

				m33 <<= 24;
				//debug("m33", m33);
				m3 += int64(m31);
				//debug("m3", m3);
				m10 = 0xFF & (m[(int)p+4]);
				//debug("m10", m10);
				x1 = h1 - y1;
				//debug("x1", x1);

				m01 <<= 8;
				//debug("m01", m01);
				m3 += int64(m32);
				//debug("m3", m3);
				m11 = 0xFF & (m[(int)p+5]);
				//debug("m11", m11);
				x0 = h0 - y0;
				//debug("x0", x0);

				m3 += int64(m33);
				//debug("m3", m3);
				m0 += int64(m00);
				//debug("m0", m0);
				m12 = 0xFF & (m[(int)p+6]);
				//debug("m12", m12);
				y5 -= alpha96;
				//debug("y5", y5);

				m02 <<= 16;
				//debug("m02", m02);
				m0 += int64(m01);
				//debug("m0", m0);
				m13 = 0xFF & (m[(int)p+7]);
				//debug("m13", m13);
				y4 -= alpha96;
				//debug("y4", y4);

				m03 <<= 24;
				//debug("m03", m03);
				m0 += int64(m02);
				//debug("m0", m0);
				d2 = m2;
				//debug("d2", d2);
				x1 += y7;
				//debug("x1", x1);

				m0 += int64(m03);
				//debug("m0", m0);
				d3 = m3;
				//debug("d3", d3);
				x0 += y6;
				//debug("x0", x0);

				m11 <<= 8;
				//debug("m11", m11);
				m1 += int64(m10);
				//debug("m1", m1);
				d0 = m0;
				//debug("d0", d0);
				x7 += y5;
				//debug("x7", x7);

				m12 <<= 16;
				//debug("m12", m12);
				m1 += int64(m11);
				//debug("m1", m1);
				x6 += y4;
				//debug("x6", x6);

				m13 <<= 24;
				//debug("m13", m13);
				m1 += int64(m12);
				//debug("m1", m1);
				y3 = h3 + alpha64;
				//debug("y3", y3);

				m1 += int64(m13);
				//debug("m1", m1);
				d1 = m1;
				//debug("d1", d1);
				y2 = h2 + alpha64;
				//debug("y2", y2);

				x0 += x1;
				//debug("x0", x0);

				x6 += x7;
				//debug("x6", x6);

				y3 -= alpha64;
				//debug("y3", y3);
				r3low = r3low_stack;
				//debug("r3low", r3low);

				y2 -= alpha64;
				//debug("y2", y2);
				r0low = r0low_stack;
				//debug("r0low", r0low);

				x5 = h5 - y5;
				//debug("x5", x5);
				r3lowx0 = r3low * x0;
				//debug("r3lowx0", r3lowx0);
				r3high = r3high_stack;
				//debug("r3high", r3high);

				x4 = h4 - y4;
				//debug("x4", x4);
				r0lowx6 = r0low * x6;
				//debug("r0lowx6", r0lowx6);
				r0high = r0high_stack;
				//debug("r0high", r0high);

				x3 = h3 - y3;
				//debug("x3", x3);
				r3highx0 = r3high * x0;
				//debug("r3highx0", r3highx0);
				sr1low = sr1low_stack;
				//debug("sr1low", sr1low);

				x2 = h2 - y2;
				//debug("x2", x2);
				r0highx6 = r0high * x6;
				//debug("r0highx6", r0highx6);
				sr1high = sr1high_stack;
				//debug("sr1high", sr1high);

				x5 += y3;
				//debug("x5", x5);
				r0lowx0 = r0low * x0;
				//debug("r0lowx0", r0lowx0);
				r1low = r1low_stack;
				//debug("r1low", r1low);

				h6 = r3lowx0 + r0lowx6;
				//debug("h6", h6);
				sr1lowx6 = sr1low * x6;
				//debug("sr1lowx6", sr1lowx6);
				r1high = r1high_stack;
				//debug("r1high", r1high);

				x4 += y2;
				//debug("x4", x4);
				r0highx0 = r0high * x0;
				//debug("r0highx0", r0highx0);
				sr2low = sr2low_stack;
				//debug("sr2low", sr2low);

				h7 = r3highx0 + r0highx6;
				//debug("h7", h7);
				sr1highx6 = sr1high * x6;
				//debug("sr1highx6", sr1highx6);
				sr2high = sr2high_stack;
				//debug("sr2high", sr2high);

				x3 += y1;
				//debug("x3", x3);
				r1lowx0 = r1low * x0;
				//debug("r1lowx0", r1lowx0);
				r2low = r2low_stack;
				//debug("r2low", r2low);

				h0 = r0lowx0 + sr1lowx6;
				//debug("h0", h0);
				sr2lowx6 = sr2low * x6;
				//debug("sr2lowx6", sr2lowx6);
				r2high = r2high_stack;
				//debug("r2high", r2high);

				x2 += y0;
				//debug("x2", x2);
				r1highx0 = r1high * x0;
				//debug("r1highx0", r1highx0);
				sr3low = sr3low_stack;
				//debug("sr3low", sr3low);

				h1 = r0highx0 + sr1highx6;
				//debug("h1", h1);
				sr2highx6 = sr2high * x6;
				//debug("sr2highx6", sr2highx6);
				sr3high = sr3high_stack;
				//debug("sr3high", sr3high);

				x4 += x5;
				//debug("x4", x4);
				r2lowx0 = r2low * x0;
				//debug("r2lowx0", r2lowx0);
				z2 = Double.longBitsToDouble(uint64(d2));
				//debug("z2", z2);

				h2 = r1lowx0 + sr2lowx6;
				//debug("h2", h2);
				sr3lowx6 = sr3low * x6;
				//debug("sr3lowx6", sr3lowx6);

				x2 += x3;
				//debug("x2", x2);
				r2highx0 = r2high * x0;
				//debug("r2highx0", r2highx0);
				z3 = Double.longBitsToDouble(uint64(d3));
				//debug("z3", z3);

				h3 = r1highx0 + sr2highx6;
				//debug("h3", h3);
				sr3highx6 = sr3high * x6;
				//debug("sr3highx6", sr3highx6);

				r1highx4 = r1high * x4;
				//debug("r1highx4", r1highx4);
				z2 -= alpha64;
				//debug("z2", z2);

				h4 = r2lowx0 + sr3lowx6;
				//debug("h4", h4);
				r1lowx4 = r1low * x4;
				//debug("r1lowx4", r1lowx4);

				r0highx4 = r0high * x4;
				//debug("r0highx4", r0highx4);
				z3 -= alpha96;
				//debug("z3", z3);

				h5 = r2highx0 + sr3highx6;
				//debug("h5", h5);
				r0lowx4 = r0low * x4;
				//debug("r0lowx4", r0lowx4);

				h7 += r1highx4;
				//debug("h7", h7);
				sr3highx4 = sr3high * x4;
				//debug("sr3highx4", sr3highx4);

				h6 += r1lowx4;
				//debug("h6", h6);
				sr3lowx4 = sr3low * x4;
				//debug("sr3lowx4", sr3lowx4);

				h5 += r0highx4;
				//debug("h5", h5);
				sr2highx4 = sr2high * x4;
				//debug("sr2highx4", sr2highx4);

				h4 += r0lowx4;
				//debug("h4", h4);
				sr2lowx4 = sr2low * x4;
				//debug("sr2lowx4", sr2lowx4);

				h3 += sr3highx4;
				//debug("h3", h3);
				r0lowx2 = r0low * x2;
				//debug("r0lowx2", r0lowx2);

				h2 += sr3lowx4;
				//debug("h2", h2);
				r0highx2 = r0high * x2;
				//debug("r0highx2", r0highx2);

				h1 += sr2highx4;
				//debug("h1", h1);
				r1lowx2 = r1low * x2;
				//debug("r1lowx2", r1lowx2);

				h0 += sr2lowx4;
				//debug("h0", h0);
				r1highx2 = r1high * x2;
				//debug("r1highx2", r1highx2);

				h2 += r0lowx2;
				//debug("h2", h2);
				r2lowx2 = r2low * x2;
				//debug("r2lowx2", r2lowx2);

				h3 += r0highx2;
				//debug("h3", h3);
				r2highx2 = r2high * x2;
				//debug("r2highx2", r2highx2);

				h4 += r1lowx2;
				//debug("h4", h4);
				sr3lowx2 = sr3low * x2;
				//debug("sr3lowx2", sr3lowx2);

				h5 += r1highx2;
				//debug("h5", h5);
				sr3highx2 = sr3high * x2;
				//debug("sr3highx2", sr3highx2);

				p += 16;
				//debug("p", p);
				l -= 16;
				//debug("l", l);
				h6 += r2lowx2;
				//debug("h6", h6);

				h7 += r2highx2;
				//debug("h7", h7);

				z1 = Double.longBitsToDouble(uint64(d1));
				//debug("z1", z1);
				h0 += sr3lowx2;
				//debug("h0", h0);

				z0 = Double.longBitsToDouble(uint64(d0));
				//debug("z0", z0);
				h1 += sr3highx2;
				//debug("h1", h1);

				z1 -= alpha32;
				//debug("z1", z1);

				z0 -= alpha0;
				//debug("z0", z0);

				h5 += z3;
				//debug("h5", h5);

				h3 += z2;
				//debug("h3", h3);

				h1 += z1;
				//debug("h1", h1);

				h0 += z0;
				//debug("h0", h0);

			}

			// multiplyaddatmost15bytes:
			y7 = h7 + alpha130;
			//debug("y7", y7);

			y6 = h6 + alpha130;
			//debug("y6", y6);

			y1 = h1 + alpha32;
			//debug("y1", y1);

			y0 = h0 + alpha32;
			//debug("y0", y0);

			y7 -= alpha130;
			//debug("y7", y7);

			y6 -= alpha130;
			//debug("y6", y6);

			y1 -= alpha32;
			//debug("y1", y1);

			y0 -= alpha32;
			//debug("y0", y0);

			y5 = h5 + alpha96;
			//debug("y5", y5);

			y4 = h4 + alpha96;
			//debug("y4", y4);

			x7 = h7 - y7;
			//debug("x7", x7);
			y7 *= scale;
			//debug("y7", y7);

			x6 = h6 - y6;
			//debug("x6", x6);
			y6 *= scale;
			//debug("y6", y6);

			x1 = h1 - y1;
			//debug("x1", x1);

			x0 = h0 - y0;
			//debug("x0", x0);

			y5 -= alpha96;
			//debug("y5", y5);

			y4 -= alpha96;
			//debug("y4", y4);

			x1 += y7;
			//debug("x1", x1);

			x0 += y6;
			//debug("x0", x0);

			x7 += y5;
			//debug("x7", x7);

			x6 += y4;
			//debug("x6", x6);

			y3 = h3 + alpha64;
			//debug("y3", y3);

			y2 = h2 + alpha64;
			//debug("y2", y2);

			x0 += x1;
			//debug("x0", x0);

			x6 += x7;
			//debug("x6", x6);

			y3 -= alpha64;
			//debug("y3", y3);
			r3low = r3low_stack;
			//debug("r3low", r3low);

			y2 -= alpha64;
			//debug("y2", y2);
			r0low = r0low_stack;
			//debug("r0low", r0low);

			x5 = h5 - y5;
			//debug("x5", x5);
			r3lowx0 = r3low * x0;
			//debug("r3lowx0", r3lowx0);
			r3high = r3high_stack;
			//debug("r3high", r3high);

			x4 = h4 - y4;
			//debug("x4", x4);
			r0lowx6 = r0low * x6;
			//debug("r0lowx6", r0lowx6);
			r0high = r0high_stack;
			//debug("r0high", r0high);

			x3 = h3 - y3;
			//debug("x3", x3);
			r3highx0 = r3high * x0;
			//debug("r3highx0", r3highx0);
			sr1low = sr1low_stack;
			//debug("sr1low", sr1low);

			x2 = h2 - y2;
			//debug("x2", x2);
			r0highx6 = r0high * x6;
			//debug("r0highx6", r0highx6);
			sr1high = sr1high_stack;
			//debug("sr1high", sr1high);

			x5 += y3;
			//debug("x5", x5);
			r0lowx0 = r0low * x0;
			//debug("r0lowx0", r0lowx0);
			r1low = r1low_stack;
			//debug("r1low", r1low);

			h6 = r3lowx0 + r0lowx6;
			//debug("h6", h6);
			sr1lowx6 = sr1low * x6;
			//debug("sr1lowx6", sr1lowx6);
			r1high = r1high_stack;
			//debug("r1high", r1high);

			x4 += y2;
			//debug("x4", x4);
			r0highx0 = r0high * x0;
			//debug("r0highx0", r0highx0);
			sr2low = sr2low_stack;
			//debug("sr2low", sr2low);

			h7 = r3highx0 + r0highx6;
			//debug("h7", h7);
			sr1highx6 = sr1high * x6;
			//debug("sr1highx6", sr1highx6);
			sr2high = sr2high_stack;
			//debug("sr2high", sr2high);

			x3 += y1;
			//debug("x3", x3);
			r1lowx0 = r1low * x0;
			//debug("r1lowx0", r1lowx0);
			r2low = r2low_stack;
			//debug("r2low", r2low);

			h0 = r0lowx0 + sr1lowx6;
			//debug("h0", h0);
			sr2lowx6 = sr2low * x6;
			//debug("sr2lowx6", sr2lowx6);
			r2high = r2high_stack;
			//debug("r2high", r2high);

			x2 += y0;
			//debug("x2", x2);
			r1highx0 = r1high * x0;
			//debug("r1highx0", r1highx0);
			sr3low = sr3low_stack;
			//debug("sr3low", sr3low);

			h1 = r0highx0 + sr1highx6;
			//debug("h1", h1);
			sr2highx6 = sr2high * x6;
			//debug("sr2highx6", sr2highx6);
			sr3high = sr3high_stack;
			//debug("sr3high", sr3high);

			x4 += x5;
			//debug("x4", x4);
			r2lowx0 = r2low * x0;
			//debug("r2lowx0", r2lowx0);

			h2 = r1lowx0 + sr2lowx6;
			//debug("h2", h2);
			sr3lowx6 = sr3low * x6;
			//debug("sr3lowx6", sr3lowx6);

			x2 += x3;
			//debug("x2", x2);
			r2highx0 = r2high * x0;
			//debug("r2highx0", r2highx0);

			h3 = r1highx0 + sr2highx6;
			//debug("h3", h3);
			sr3highx6 = sr3high * x6;
			//debug("sr3highx6", sr3highx6);

			r1highx4 = r1high * x4;
			//debug("r1highx4", r1highx4);

			h4 = r2lowx0 + sr3lowx6;
			//debug("h4", h4);
			r1lowx4 = r1low * x4;
			//debug("r1lowx4", r1lowx4);

			r0highx4 = r0high * x4;
			//debug("r0highx4", r0highx4);

			h5 = r2highx0 + sr3highx6;
			//debug("h5", h5);
			r0lowx4 = r0low * x4;
			//debug("r0lowx4", r0lowx4);

			h7 += r1highx4;
			//debug("h7", h7);
			sr3highx4 = sr3high * x4;
			//debug("sr3highx4", sr3highx4);

			h6 += r1lowx4;
			//debug("h6", h6);
			sr3lowx4 = sr3low * x4;
			//debug("sr3lowx4", sr3lowx4);

			h5 += r0highx4;
			//debug("h5", h5);
			sr2highx4 = sr2high * x4;
			//debug("sr2highx4", sr2highx4);

			h4 += r0lowx4;
			//debug("h4", h4);
			sr2lowx4 = sr2low * x4;
			//debug("sr2lowx4", sr2lowx4);

			h3 += sr3highx4;
			//debug("h3", h3);
			r0lowx2 = r0low * x2;
			//debug("r0lowx2", r0lowx2);

			h2 += sr3lowx4;
			//debug("h2", h2);
			r0highx2 = r0high * x2;
			//debug("r0highx2", r0highx2);

			h1 += sr2highx4;
			//debug("h1", h1);
			r1lowx2 = r1low * x2;
			//debug("r1lowx2", r1lowx2);

			h0 += sr2lowx4;
			//debug("h0", h0);
			r1highx2 = r1high * x2;
			//debug("r1highx2", r1highx2);

			h2 += r0lowx2;
			//debug("h2", h2);
			r2lowx2 = r2low * x2;
			//debug("r2lowx2", r2lowx2);

			h3 += r0highx2;
			//debug("h3", h3);
			r2highx2 = r2high * x2;
			//debug("r2highx2", r2highx2);

			h4 += r1lowx2;
			//debug("h4", h4);
			sr3lowx2 = sr3low * x2;
			//debug("sr3lowx2", sr3lowx2);

			h5 += r1highx2;
			//debug("h5", h5);
			sr3highx2 = sr3high * x2;
			//debug("sr3highx2", sr3highx2);

			h6 += r2lowx2;
			//debug("h6", h6);

			h7 += r2highx2;
			//debug("h7", h7);

			h0 += sr3lowx2;
			//debug("h0", h0);

			h1 += sr3highx2;
			//debug("h1", h1);
		}

		// addatmost15bytes:

		if (l > 0) {
			lbelow2 = l - 2;
			//debug("lbelow2", lbelow2);

			lbelow3 = l - 3;
			//debug("lbelow3", lbelow3);

			lbelow2 >>= 31;
			//debug("lbelow2", lbelow2);
			lbelow4 = l - 4;
			//debug("lbelow4", lbelow4);

			m00 = uint32(m[(int)p+0]);
			//debug("m00", m00);
			lbelow3 >>= 31;
			//debug("lbelow3", lbelow3);
			p += lbelow2;
			//debug("p", p);

			m01 = uint32(m[(int)p+1]);
			//debug("m01", m01);
			lbelow4 >>= 31;
			//debug("lbelow4", lbelow4);
			p += lbelow3;
			//debug("p", p);

			m02 = uint32(m[(int)p+2]);
			//debug("m02", m02);
			p += lbelow4;
			//debug("p", p);
			m0 = 2151;
			//debug("m0", m0);

			m03 = uint32(m[(int)p+3]);
			//debug("m03", m03);
			m0 <<= 51;
			//debug("m0", m0);
			m1 = 2215;
			//debug("m1", m1);

			m0 += int64(m00);
			//debug("m0", m0);
			m01 &= ~uint32(lbelow2);
			//debug("m01", m01);

			m02 &= ~uint32(lbelow3);
			//debug("m02", m02);
			m01 -= uint32(lbelow2);
			//debug("m01", m01);

			m01 <<= 8;
			//debug("m01", m01);
			m03 &= ~uint32(lbelow4);
			//debug("m03", m03);

			m0 += int64(m01);
			//debug("m0", m0);
			lbelow2 -= lbelow3;
			//debug("lbelow2", lbelow2);

			m02 += uint32(lbelow2);
			//debug("m02", m02);
			lbelow3 -= lbelow4;
			//debug("lbelow3", lbelow3);

			m02 <<= 16;
			//debug("m02", m02);
			m03 += uint32(lbelow3);
			//debug("m03", m03);

			m03 <<= 24;
			//debug("m03", m03);
			m0 += int64(m02);
			//debug("m0", m0);

			m0 += int64(m03);
			//debug("m0", m0);
			lbelow5 = l - 5;
			//debug("lbelow5", lbelow5);

			lbelow6 = l - 6;
			//debug("lbelow6", lbelow6);
			lbelow7 = l - 7;
			//debug("lbelow7", lbelow7);

			lbelow5 >>= 31;
			//debug("lbelow5", lbelow5);
			lbelow8 = l - 8;
			//debug("lbelow8", lbelow8);

			lbelow6 >>= 31;
			//debug("lbelow6", lbelow6);
			p += lbelow5;
			//debug("p", p);

			m10 = uint32(m[(int)p+4]);
			//debug("m10", m10);
			lbelow7 >>= 31;
			//debug("lbelow7", lbelow7);
			p += lbelow6;
			//debug("p", p);

			m11 = uint32(m[(int)p+5]);
			//debug("m11", m11);
			lbelow8 >>= 31;
			//debug("lbelow8", lbelow8);
			p += lbelow7;
			//debug("p", p);

			m12 = uint32(m[(int)p+6]);
			//debug("m12", m12);
			m1 <<= 51;
			//debug("m1", m1);
			p += lbelow8;
			//debug("p", p);

			m13 = uint32(m[(int)p+7]);
			//debug("m13", m13);
			m10 &= ~uint32(lbelow5);
			//debug("m10", m10);
			lbelow4 -= lbelow5;
			//debug("lbelow4", lbelow4);

			m10 += uint32(lbelow4);
			//debug("m10", m10);
			lbelow5 -= lbelow6;
			//debug("lbelow5", lbelow5);

			m11 &= ~uint32(lbelow6);
			//debug("m11", m11);
			m11 += uint32(lbelow5);
			//debug("m11", m11);

			m11 <<= 8;
			//debug("m11", m11);
			m1 += int64(m10);
			//debug("m1", m1);

			m1 += int64(m11);
			//debug("m1", m1);
			m12 &= ~uint32(lbelow7);
			//debug("m12", m12);

			lbelow6 -= lbelow7;
			//debug("lbelow6", lbelow6);
			m13 &= ~uint32(lbelow8);
			//debug("m13", m13);

			m12 += uint32(lbelow6);
			//debug("m12", m12);
			lbelow7 -= lbelow8;
			//debug("lbelow7", lbelow7);

			m12 <<= 16;
			//debug("m12", m12);
			m13 += uint32(lbelow7);
			//debug("m13", m13);

			m13 <<= 24;
			//debug("m13", m13);
			m1 += int64(m12);
			//debug("m1", m1);

			m1 += int64(m13);
			//debug("m1", m1);
			m2 = 2279;
			//debug("m2", m2);

			lbelow9 = l - 9;
			//debug("lbelow9", lbelow9);
			m3 = 2343;
			//debug("m3", m3);

			lbelow10 = l - 10;
			//debug("lbelow10", lbelow10);
			lbelow11 = l - 11;
			//debug("lbelow11", lbelow11);

			lbelow9 >>= 31;
			//debug("lbelow9", lbelow9);
			lbelow12 = l - 12;
			//debug("lbelow12", lbelow12);

			lbelow10 >>= 31;
			//debug("lbelow10", lbelow10);
			p += lbelow9;
			//debug("p", p);

			m20 = uint32(m[(int)p+8]);
			//debug("m20", m20);
			lbelow11 >>= 31;
			//debug("lbelow11", lbelow11);
			p += lbelow10;
			//debug("p", p);

			m21 = uint32(m[(int)p+9]);
			//debug("m21", m21);
			lbelow12 >>= 31;
			//debug("lbelow12", lbelow12);
			p += lbelow11;
			//debug("p", p);

			m22 = uint32(m[(int)p+10]);
			//debug("m22", m22);
			m2 <<= 51;
			//debug("m2", m2);
			p += lbelow12;
			//debug("p", p);

			m23 = uint32(m[(int)p+11]);
			//debug("m23", m23);
			m20 &= ~uint32(lbelow9);
			//debug("m20", m20);
			lbelow8 -= lbelow9;
			//debug("lbelow8", lbelow8);

			m20 += uint32(lbelow8);
			//debug("m20", m20);
			lbelow9 -= lbelow10;
			//debug("lbelow9", lbelow9);

			m21 &= ~uint32(lbelow10);
			//debug("m21", m21);
			m21 += uint32(lbelow9);
			//debug("m21", m21);

			m21 <<= 8;
			//debug("m21", m21);
			m2 += int64(m20);
			//debug("m2", m2);

			m2 += int64(m21);
			//debug("m2", m2);
			m22 &= ~uint32(lbelow11);
			//debug("m22", m22);

			lbelow10 -= lbelow11;
			//debug("lbelow10", lbelow10);
			m23 &= ~uint32(lbelow12);
			//debug("m23", m23);

			m22 += uint32(lbelow10);
			//debug("m22", m22);
			lbelow11 -= lbelow12;
			//debug("lbelow11", lbelow11);

			m22 <<= 16;
			//debug("m22", m22);
			m23 += uint32(lbelow11);
			//debug("m23", m23);

			m23 <<= 24;
			//debug("m23", m23);
			m2 += int64(m22);
			//debug("m2", m2);

			m3 <<= 51;
			//debug("m3", m3);
			lbelow13 = l - 13;
			//debug("lbelow13", lbelow13);

			lbelow13 >>= 31;
			//debug("lbelow13", lbelow13);
			lbelow14 = l - 14;
			//debug("lbelow14", lbelow14);

			lbelow14 >>= 31;
			//debug("lbelow14", lbelow14);
			p += lbelow13;
			//debug("p", p);
			lbelow15 = l - 15;
			//debug("lbelow15", lbelow15);

			m30 = uint32(m[(int)p+12]);
			//debug("m30", m30);
			lbelow15 >>= 31;
			//debug("lbelow15", lbelow15);
			p += lbelow14;
			//debug("p", p);

			m31 = uint32(m[(int)p+13]);
			//debug("m31", m31);
			p += lbelow15;
			//debug("p", p);
			m2 += int64(m23);
			//debug("m2", m2);

			m32 = uint32(m[(int)p+14]);
			//debug("m32", m32);
			m30 &= ~uint32(lbelow13);
			//debug("m30", m30);
			lbelow12 -= lbelow13;
			//debug("lbelow12", lbelow12);

			m30 += uint32(lbelow12);
			//debug("m30", m30);
			lbelow13 -= lbelow14;
			//debug("lbelow13", lbelow13);

			m3 += int64(m30);
			//debug("m3", m3);
			m31 &= ~uint32(lbelow14);
			//debug("m31", m31);

			m31 += uint32(lbelow13);
			//debug("m31", m31);
			m32 &= ~uint32(lbelow15);
			//debug("m32", m32);

			m31 <<= 8;
			//debug("m31", m31);
			lbelow14 -= lbelow15;
			//debug("lbelow14", lbelow14);

			m3 += int64(m31);
			//debug("m3", m3);
			m32 += uint32(lbelow14);
			//debug("m32", m32);
			d0 = m0;
			//debug("d0", d0);

			m32 <<= 16;
			//debug("m32", m32);
			m33 = uint64(lbelow15 + 1);
			//debug("m33", m33);
			d1 = m1;
			//debug("d1", d1);

			m33 <<= 24;
			//debug("m33", m33);
			m3 += int64(m32);
			//debug("m3", m3);
			d2 = m2;
			//debug("d2", d2);

			m3 += int64(m33);
			//debug("m3", m3);
			d3 = m3;
			//debug("d3", d3);

			z3 = Double.longBitsToDouble(uint64(d3));;
			//debug("z3", z3);

			z2 = Double.longBitsToDouble(uint64(d2));
			//debug("z2", z2);

			z1 = Double.longBitsToDouble(uint64(d1));
			//debug("z1", z1);

			z0 = Double.longBitsToDouble(uint64(d0));
			//debug("z0", z0);

			z3 -= alpha96;
			//debug("z3", z3);

			z2 -= alpha64;
			//debug("z2", z2);

			z1 -= alpha32;
			//debug("z1", z1);

			z0 -= alpha0;
			//debug("z0", z0);

			h5 += z3;
			//debug("h5", h5);

			h3 += z2;
			//debug("h3", h3);

			h1 += z1;
			//debug("h1", h1);

			h0 += z0;
			//debug("h0", h0);

			y7 = h7 + alpha130;
			//debug("y7", y7);

			y6 = h6 + alpha130;
			//debug("y6", y6);

			y1 = h1 + alpha32;
			//debug("y1", y1);

			y0 = h0 + alpha32;
			//debug("y0", y0);

			y7 -= alpha130;
			//debug("y7", y7);

			y6 -= alpha130;
			//debug("y6", y6);

			y1 -= alpha32;
			//debug("y1", y1);

			y0 -= alpha32;
			//debug("y0", y0);

			y5 = h5 + alpha96;
			//debug("y5", y5);

			y4 = h4 + alpha96;
			//debug("y4", y4);

			x7 = h7 - y7;
			//debug("x7", x7);
			y7 *= scale;
			//debug("y7", y7);

			x6 = h6 - y6;
			//debug("x6", x6);
			y6 *= scale;
			//debug("y6", y6);

			x1 = h1 - y1;
			//debug("x1", x1);

			x0 = h0 - y0;
			//debug("x0", x0);

			y5 -= alpha96;
			//debug("y5", y5);

			y4 -= alpha96;
			//debug("y4", y4);

			x1 += y7;
			//debug("x1", x1);

			x0 += y6;
			//debug("x0", x0);

			x7 += y5;
			//debug("x7", x7);

			x6 += y4;
			//debug("x6", x6);

			y3 = h3 + alpha64;
			//debug("y3", y3);

			y2 = h2 + alpha64;
			//debug("y2", y2);

			x0 += x1;
			//debug("x0", x0);

			x6 += x7;
			//debug("x6", x6);

			y3 -= alpha64;
			//debug("y3", y3);
			r3low = r3low_stack;
			//debug("r3low", r3low);

			y2 -= alpha64;
			//debug("y2", y2);
			r0low = r0low_stack;
			//debug("r0low", r0low);

			x5 = h5 - y5;
			//debug("x5", x5);
			r3lowx0 = r3low * x0;
			//debug("r3lowx0", r3lowx0);
			r3high = r3high_stack;
			//debug("r3high", r3high);

			x4 = h4 - y4;
			//debug("x4", x4);
			r0lowx6 = r0low * x6;
			//debug("r0lowx6", r0lowx6);
			r0high = r0high_stack;
			//debug("r0high", r0high);

			x3 = h3 - y3;
			//debug("x3", x3);
			r3highx0 = r3high * x0;
			//debug("r3highx0", r3highx0);
			sr1low = sr1low_stack;
			//debug("sr1low", sr1low);

			x2 = h2 - y2;
			//debug("x2", x2);
			r0highx6 = r0high * x6;
			//debug("r0highx6", r0highx6);
			sr1high = sr1high_stack;
			//debug("sr1high", sr1high);

			x5 += y3;
			//debug("x5", x5);
			r0lowx0 = r0low * x0;
			//debug("r0lowx0", r0lowx0);
			r1low = r1low_stack;
			//debug("r1low", r1low);

			h6 = r3lowx0 + r0lowx6;
			//debug("h6", h6);
			sr1lowx6 = sr1low * x6;
			//debug("sr1lowx6", sr1lowx6);
			r1high = r1high_stack;
			//debug("r1high", r1high);

			x4 += y2;
			//debug("x4", x4);
			r0highx0 = r0high * x0;
			//debug("r0highx0", r0highx0);
			sr2low = sr2low_stack;
			//debug("sr2low", sr2low);

			h7 = r3highx0 + r0highx6;
			//debug("h7", h7);
			sr1highx6 = sr1high * x6;
			//debug("sr1highx6", sr1highx6);
			sr2high = sr2high_stack;
			//debug("sr2high", sr2high);

			x3 += y1;
			//debug("x3", x3);
			r1lowx0 = r1low * x0;
			//debug("r1lowx0", r1lowx0);
			r2low = r2low_stack;
			//debug("r2low", r2low);

			h0 = r0lowx0 + sr1lowx6;
			//debug("h0", h0);
			sr2lowx6 = sr2low * x6;
			//debug("sr2lowx6", sr2lowx6);
			r2high = r2high_stack;
			//debug("r2high", r2high);

			x2 += y0;
			//debug("x2", x2);
			r1highx0 = r1high * x0;
			//debug("r1highx0", r1highx0);
			sr3low = sr3low_stack;
			//debug("sr3low", sr3low);

			h1 = r0highx0 + sr1highx6;
			//debug("h1", h1);
			sr2highx6 = sr2high * x6;
			//debug("sr2highx6", sr2highx6);
			sr3high = sr3high_stack;
			//debug("sr3high", sr3high);

			x4 += x5;
			//debug("x4", x4);
			r2lowx0 = r2low * x0;
			//debug("r2lowx0", r2lowx0);

			h2 = r1lowx0 + sr2lowx6;
			//debug("h2", h2);
			sr3lowx6 = sr3low * x6;
			//debug("sr3lowx6", sr3lowx6);

			x2 += x3;
			//debug("x2", x2);
			r2highx0 = r2high * x0;
			//debug("r2highx0", r2highx0);

			h3 = r1highx0 + sr2highx6;
			//debug("h3", h3);
			sr3highx6 = sr3high * x6;
			//debug("sr3highx6", sr3highx6);

			r1highx4 = r1high * x4;
			//debug("r1highx4", r1highx4);

			h4 = r2lowx0 + sr3lowx6;
			//debug("h4", h4);
			r1lowx4 = r1low * x4;
			//debug("r1lowx4", r1lowx4);

			r0highx4 = r0high * x4;
			//debug("r0highx4", r0highx4);

			h5 = r2highx0 + sr3highx6;
			//debug("h5", h5);
			r0lowx4 = r0low * x4;
			//debug("r0lowx4", r0lowx4);

			h7 += r1highx4;
			//debug("h7", h7);
			sr3highx4 = sr3high * x4;
			//debug("sr3highx4", sr3highx4);

			h6 += r1lowx4;
			//debug("h6", h6);
			sr3lowx4 = sr3low * x4;
			//debug("sr3lowx4", sr3lowx4);

			h5 += r0highx4;
			//debug("h5", h5);
			sr2highx4 = sr2high * x4;
			//debug("sr2highx4", sr2highx4);

			h4 += r0lowx4;
			//debug("h4", h4);
			sr2lowx4 = sr2low * x4;
			//debug("sr2lowx4", sr2lowx4);

			h3 += sr3highx4;
			//debug("h3", h3);
			r0lowx2 = r0low * x2;
			//debug("r0lowx2", r0lowx2);

			h2 += sr3lowx4;
			//debug("h2", h2);
			r0highx2 = r0high * x2;
			//debug("r0highx2", r0highx2);

			h1 += sr2highx4;
			//debug("h1", h1);
			r1lowx2 = r1low * x2;
			//debug("r1lowx2", r1lowx2);

			h0 += sr2lowx4;
			//debug("h0", h0);
			r1highx2 = r1high * x2;
			//debug("r1highx2", r1highx2);

			h2 += r0lowx2;
			//debug("h2", h2);
			r2lowx2 = r2low * x2;
			//debug("r2lowx2", r2lowx2);

			h3 += r0highx2;
			//debug("h3", h3);
			r2highx2 = r2high * x2;
			//debug("r2highx2", r2highx2);

			h4 += r1lowx2;
			//debug("h4", h4);
			sr3lowx2 = sr3low * x2;
			//debug("sr3lowx2", sr3lowx2);

			h5 += r1highx2;
			//debug("h5", h5);
			sr3highx2 = sr3high * x2;
			//debug("sr3highx2", sr3highx2);

			h6 += r2lowx2;
			//debug("h6", h6);

			h7 += r2highx2;
			//debug("h7", h7);

			h0 += sr3lowx2;
			//debug("h0", h0);

			h1 += sr3highx2;
			//debug("h1", h1);
		}

		//nomorebytes:

		y7 = h7 + alpha130;
		//debug("y7", y7);

		y0 = h0 + alpha32;
		//debug("y0", y0);

		y1 = h1 + alpha32;
		//debug("y1", y1);

		y2 = h2 + alpha64;
		//debug("y2", y2);

		y7 -= alpha130;
		//debug("y7", y7);

		y3 = h3 + alpha64;
		//debug("y3", y3);

		y4 = h4 + alpha96;
		//debug("y4", y4);

		y5 = h5 + alpha96;
		//debug("y5", y5);

		x7 = h7 - y7;
		//debug("x7", x7);
		y7 *= scale;
		//debug("y7", y7);

		y0 -= alpha32;
		//debug("y0", y0);

		y1 -= alpha32;
		//debug("y1", y1);

		y2 -= alpha64;
		//debug("y2", y2);

		h6 += x7;
		//debug("h6", h6);

		y3 -= alpha64;
		//debug("y3", y3);

		y4 -= alpha96;
		//debug("y4", y4);

		y5 -= alpha96;
		//debug("y5", y5);

		y6 = h6 + alpha130;
		//debug("y6", y6);

		x0 = h0 - y0;
		//debug("x0", x0);

		x1 = h1 - y1;
		//debug("x1", x1);

		x2 = h2 - y2;
		//debug("x2", x2);

		y6 -= alpha130;
		//debug("y6", y6);

		x0 += y7;
		//debug("x0", x0);

		x3 = h3 - y3;
		//debug("x3", x3);

		x4 = h4 - y4;
		//debug("x4", x4);

		x5 = h5 - y5;
		//debug("x5", x5);

		x6 = h6 - y6;
		//debug("x6", x6);

		y6 *= scale;
		//debug("y6", y6);

		x2 += y0;
		//debug("x2", x2);

		x3 += y1;
		//debug("x3", x3);

		x4 += y2;
		//debug("x4", x4);

		x0 += y6;
		//debug("x0", x0);

		x5 += y3;
		//debug("x5", x5);

		x6 += y4;
		//debug("x6", x6);

		x2 += x3;
		//debug("x2", x2);

		x0 += x1;
		//debug("x0", x0);

		x4 += x5;
		//debug("x4", x4);

		x6 += y5;
		//debug("x6", x6);

		x2 += offset1;
		//debug("x2", x2);
		d1 = int64(Double.doubleToLongBits(x2));
		//debug("d1", d1);

		x0 += offset0;
		//debug("x0", x0);
		d0 = int64(Double.doubleToLongBits(x0));
		//debug("d0", d0);

		x4 += offset2;
		//debug("x4", x4);
		d2 = int64(Double.doubleToLongBits(x4));
		//debug("d2", d2);

		x6 += offset3;
		//debug("x6", x6);
		d3 = int64(Double.doubleToLongBits(x6));
		//debug("d3", d3);

		f0 = uint64(d0);
		//debug("f0", f0);

		f1 = uint64(d1);
		//debug("f1", f1);
		bits32 = 0xFFFFFFFFFFFFFFFFl;
		//debug("bits32", bits32);

		f2 = uint64(d2);
		//debug("f2", f2);
		bits32 >>>= 32;
		//debug("bits32", bits32);

		f3 = uint64(d3);
		//debug("f3", f3);
		f = f0 >> 32;
		//debug("f", f);

		f0 &= bits32;
		//debug("f0", f0);
		f &= 255;
		//debug("f", f);

		f1 += f;
		//debug("f1", f1);
		g0 = f0 + 5;
		//debug("g0", g0);

		g = g0 >> 32;
		//debug("g", g);
		g0 &= bits32;
		//debug("g0", g0);

		f = f1 >> 32;
		//debug("f", f);
		f1 &= bits32;
		//debug("f1", f1);

		f &= 255;
		//debug("f", f);
		g1 = f1 + g;
		//debug("g1", g1);

		g = g1 >> 32;
		//debug("g", g);
		f2 += f;
		//debug("f2", f2);

		f = f2 >> 32;
		//debug("f", f);
		g1 &= bits32;
		//debug("g1", g1);

		f2 &= bits32;
		//debug("f2", f2);
		f &= 255;
		//debug("f", f);

		f3 += f;
		//debug("f3", f3);
		g2 = f2 + g;
		//debug("g2", g2);

		g = g2 >> 32;
		//debug("g", g);
		g2 &= bits32;
		//debug("g2", g2);

		f4 = f3 >> 32;
		//debug("f4", f4);
		f3 &= bits32;
		//debug("f3", f3);

		f4 &= 255;
		//debug("f4", f4);
		g3 = f3 + g;
		//debug("g3", g3);

		g = g3 >> 32;
		//debug("g", g);
		g3 &= bits32;
		//debug("g3", g3);

		g4 = f4 + g;
		//debug("g4", g4);

		g4 = g4 - 4;
		//debug("g4", g4);
		s00 = 0xFF & (s[0]);
		//debug("s00", s00);

		f = uint64(int64(g4) >> 63);
		//debug("f", f);
		s01 = 0xFF & (s[1]);
		//debug("s01", s01);

		f0 &= f;
		//debug("f0", f0);
		g0  &= ~f;
		//debug("g0", g0);
		s02 = 0xFF & (s[2]);
		//debug("s02", s02);

		f1 &= f;
		//debug("f1", f1);
		f0 |= g0;
		//debug("f0", f0);
		s03 = 0xFF & (s[3]);
		//debug("s03", s03);

		g1 &= ~f;
		//debug("g1", g1);
		f2 &= f;
		//debug("f2", f2);
		s10 = 0xFF & (s[4]);
		//debug("s10", s10);

		f3 &= f;
		//debug("f3", f3);
		g2 &= ~f;
		//debug("g2", g2);
		s11 = 0xFF & (s[5]);
		//debug("s11", s11);

		g3 &= ~f;
		//debug("g3", g3);
		f1 |= g1;
		//debug("f1", f1);
		s12 = 0xFF & (s[6]);
		//debug("s12", s12);

		f2 |= g2;
		//debug("f2", f2);
		f3 |= g3;
		//debug("f3", f3);
		s13 = 0xFF & (s[7]);
		//debug("s13", s13);

		s01 <<= 8;
		//debug("s01", s01);
		f0 += uint64(s00);
		//debug("f0", f0);
		s20 = 0xFF & (s[8]);
		//debug("s20", s20);

		s02 <<= 16;
		//debug("s02", s02);
		f0 += uint64(s01);
		//debug("f0", f0);
		s21 = 0xFF & (s[9]);
		//debug("s21", s21);

		s03 <<= 24;
		//debug("s03", s03);
		f0 += uint64(s02);
		//debug("f0", f0);
		s22 = 0xFF & (s[10]);
		//debug("s22", s22);

		s11 <<= 8;
		//debug("s11", s11);
		f1 += uint64(s10);
		//debug("f1", f1);
		s23 = 0xFF & (s[11]);
		//debug("s23", s23);

		s12 <<= 16;
		//debug("s12", s12);
		f1 += uint64(s11);
		//debug("f1", f1);
		s30 = 0xFF & (s[12]);
		//debug("s30", s30);

		s13 <<= 24;
		//debug("s13", s13);
		f1 += (s12);
		//debug("f1", f1);
		s31 = 0xFF & s[13];
		//debug("s31", s31);

		f0 += (s03);
		//debug("f0", f0);
		f1 += (s13);
		//debug("f1", f1);
		s32 = 0xFF & (s[14]);
		//debug("s32", s32);

		s21 <<= 8;
		//debug("s21", s21);
		f2 += (s20);
		//debug("f2", f2);
		s33 = 0xFF & (s[15]);
		//debug("s33", s33);

		s22 <<= 16;
		//debug("s22", s22);
		f2 += (s21);
		//debug("f2", f2);

		s23 <<= 24;
		//debug("s23", s23);
		f2 += (s22);
		//debug("f2", f2);

		s31 <<= 8;
		//debug("s31", s31);
		f3 += (s30);
		//debug("f3", f3);

		s32 <<= 16;
		//debug("s32", s32);
		f3 += (s31);
		//debug("f3", f3);

		s33 <<= 24;
		//debug("s33", s33);
		f3 += (s32);
		//debug("f3", f3);

		f2 += (s23);
		//debug("f2", f2);
		f3 += s33;
		//debug("f3", f3);

		byte out[] = new byte[16];
		out[0] = (byte)(f0);
		f0 >>= 8;
		out[1] = (byte)(f0);
		f0 >>= 8;
		out[2] = (byte)(f0);
		f0 >>= 8;
		out[3] = (byte)(f0);
		f0 >>= 8;
		f1 += f0;

		out[4] = (byte)(f1);
		f1 >>= 8;
		out[5] = (byte)(f1);
		f1 >>= 8;
		out[6] = (byte)(f1);
		f1 >>= 8;
		out[7] = (byte)(f1);
		f1 >>= 8;
		f2 += f1;

		out[8] = (byte)(f2);
		f2 >>= 8;
		out[9] = (byte)(f2);
		f2 >>= 8;
		out[10] = (byte)(f2);
		f2 >>= 8;
		out[11] = (byte)(f2);
		f2 >>= 8;
		f3 += f2;

		out[12] = (byte)f3;
		f3 >>= 8;
		out[13] = (byte)f3;
		f3 >>= 8;
		out[14] = (byte)f3;
		f3 >>= 8;
		out[15] = (byte)f3;
		return out;
	}
}
