package com.caligochat.nacl;

/**
 * Created by wfreeman on 2/11/15.
 */
public class Curve25519 {

    // basePoint is the x coordinate of the generator of the curve.
    private static byte basePoint[] = {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static void feCSwap(FieldElement f, FieldElement g, long b) {
        FieldElement x = new FieldElement();
        b = -b;
        for (int i = 0; i < x.arr.length; i++) {
            x.arr[i] = b & (f.arr[i] ^ g.arr[i]);
        }
        for (int i = 0; i < f.arr.length; i++) {
            f.arr[i] ^= x.arr[i];
        }
        for (int i = 0; i < g.arr.length; i++) {
            g.arr[i] ^= x.arr[i];
        }
    }

    private static FieldElement feSub(FieldElement a, FieldElement b) {
        FieldElement retVal = new FieldElement();
        for (int i = 0; i < retVal.arr.length; i++) {
            retVal.arr[i] = a.arr[i] - b.arr[i];
        }
        return retVal;
    }

    private static FieldElement feAdd(FieldElement a, FieldElement b) {
        FieldElement retVal = new FieldElement();
        for (int i = 0; i < retVal.arr.length; i++) {
            retVal.arr[i] = a.arr[i] + b.arr[i];
        }
        return retVal;
    }

    private static FieldElement feMul(FieldElement f, FieldElement g) {
        FieldElement retVal = new FieldElement();
        long f0 = f.arr[0];
        long f1 = f.arr[1];
        long f2 = f.arr[2];
        long f3 = f.arr[3];
        long f4 = f.arr[4];
        long f5 = f.arr[5];
        long f6 = f.arr[6];
        long f7 = f.arr[7];
        long f8 = f.arr[8];
        long f9 = f.arr[9];
        long g0 = g.arr[0];
        long g1 = g.arr[1];
        long g2 = g.arr[2];
        long g3 = g.arr[3];
        long g4 = g.arr[4];
        long g5 = g.arr[5];
        long g6 = g.arr[6];
        long g7 = g.arr[7];
        long g8 = g.arr[8];
        long g9 = g.arr[9];
        long g1_19 = 19l * g1; // 1.4*2^29
        long g2_19 = 19l * g2; // 1.4*2^30; still ok
        long g3_19 = 19l * g3;
        long g4_19 = 19l * g4;
        long g5_19 = 19l * g5;
        long g6_19 = 19l * g6;
        long g7_19 = 19l * g7;
        long g8_19 = 19l * g8;
        long g9_19 = 19l * g9;
        long f1_2 = 2l * f1;
        long f3_2 = 2l * f3;
        long f5_2 = 2l * f5;
        long f7_2 = 2l * f7;
        long f9_2 = 2l * f9;
        long f0g0 = f0 * g0;
        long f0g1 = f0 * g1;
        long f0g2 = f0 * g2;
        long f0g3 = f0 * g3;
        long f0g4 = f0 * g4;
        long f0g5 = f0 * g5;
        long f0g6 = f0 * g6;
        long f0g7 = f0 * g7;
        long f0g8 = f0 * g8;
        long f0g9 = f0 * g9;
        long f1g0 = f1 * g0;
        long f1g1_2 = f1_2 * g1;
        long f1g2 = f1 * g2;
        long f1g3_2 = f1_2 * g3;
        long f1g4 = f1 * g4;
        long f1g5_2 = f1_2 * g5;
        long f1g6 = f1 * g6;
        long f1g7_2 = f1_2 * g7;
        long f1g8 = f1 * g8;
        long f1g9_38 = f1_2 * g9_19;
        long f2g0 = f2 * g0;
        long f2g1 = f2 * g1;
        long f2g2 = f2 * g2;
        long f2g3 = f2 * g3;
        long f2g4 = f2 * g4;
        long f2g5 = f2 * g5;
        long f2g6 = f2 * g6;
        long f2g7 = f2 * g7;
        long f2g8_19 = f2 * g8_19;
        long f2g9_19 = f2 * g9_19;
        long f3g0 = f3 * g0;
        long f3g1_2 = f3_2 * g1;
        long f3g2 = f3 * g2;
        long f3g3_2 = f3_2 * g3;
        long f3g4 = f3 * g4;
        long f3g5_2 = f3_2 * g5;
        long f3g6 = f3 * g6;
        long f3g7_38 = f3_2 * g7_19;
        long f3g8_19 = f3 * g8_19;
        long f3g9_38 = f3_2 * g9_19;
        long f4g0 = f4 * g0;
        long f4g1 = f4 * g1;
        long f4g2 = f4 * g2;
        long f4g3 = f4 * g3;
        long f4g4 = f4 * g4;
        long f4g5 = f4 * g5;
        long f4g6_19 = f4 * g6_19;
        long f4g7_19 = f4 * g7_19;
        long f4g8_19 = f4 * g8_19;
        long f4g9_19 = f4 * g9_19;
        long f5g0 = f5 * g0;
        long f5g1_2 = f5_2 * g1;
        long f5g2 = f5 * g2;
        long f5g3_2 = f5_2 * g3;
        long f5g4 = f5 * g4;
        long f5g5_38 = f5_2 * g5_19;
        long f5g6_19 = f5 * g6_19;
        long f5g7_38 = f5_2 * g7_19;
        long f5g8_19 = f5 * g8_19;
        long f5g9_38 = f5_2 * g9_19;
        long f6g0 = f6 * g0;
        long f6g1 = f6 * g1;
        long f6g2 = f6 * g2;
        long f6g3 = f6 * g3;
        long f6g4_19 = f6 * g4_19;
        long f6g5_19 = f6 * g5_19;
        long f6g6_19 = f6 * g6_19;
        long f6g7_19 = f6 * g7_19;
        long f6g8_19 = f6 * g8_19;
        long f6g9_19 = f6 * g9_19;
        long f7g0 = f7 * g0;
        long f7g1_2 = f7_2 * g1;
        long f7g2 = f7 * g2;
        long f7g3_38 = f7_2 * g3_19;
        long f7g4_19 = f7 * g4_19;
        long f7g5_38 = f7_2 * g5_19;
        long f7g6_19 = f7 * g6_19;
        long f7g7_38 = f7_2 * g7_19;
        long f7g8_19 = f7 * g8_19;
        long f7g9_38 = f7_2 * g9_19;
        long f8g0 = f8 * g0;
        long f8g1 = f8 * g1;
        long f8g2_19 = f8 * g2_19;
        long f8g3_19 = f8 * g3_19;
        long f8g4_19 = f8 * g4_19;
        long f8g5_19 = f8 * g5_19;
        long f8g6_19 = f8 * g6_19;
        long f8g7_19 = f8 * g7_19;
        long f8g8_19 = f8 * g8_19;
        long f8g9_19 = f8 * g9_19;
        long f9g0 = f9 * g0;
        long f9g1_38 = f9_2 * g1_19;
        long f9g2_19 = f9 * g2_19;
        long f9g3_38 = f9_2 * g3_19;
        long f9g4_19 = f9 * g4_19;
        long f9g5_38 = f9_2 * g5_19;
        long f9g6_19 = f9 * g6_19;
        long f9g7_38 = f9_2 * g7_19;
        long f9g8_19 = f9 * g8_19;
        long f9g9_38 = f9_2 * g9_19;
        long h0 = f0g0 + f1g9_38 + f2g8_19 + f3g7_38 + f4g6_19 + f5g5_38 + f6g4_19 + f7g3_38 + f8g2_19 + f9g1_38;
        long h1 = f0g1 + f1g0 + f2g9_19 + f3g8_19 + f4g7_19 + f5g6_19 + f6g5_19 + f7g4_19 + f8g3_19 + f9g2_19;
        long h2 = f0g2 + f1g1_2 + f2g0 + f3g9_38 + f4g8_19 + f5g7_38 + f6g6_19 + f7g5_38 + f8g4_19 + f9g3_38;
        long h3 = f0g3 + f1g2 + f2g1 + f3g0 + f4g9_19 + f5g8_19 + f6g7_19 + f7g6_19 + f8g5_19 + f9g4_19;
        long h4 = f0g4 + f1g3_2 + f2g2 + f3g1_2 + f4g0 + f5g9_38 + f6g8_19 + f7g7_38 + f8g6_19 + f9g5_38;
        long h5 = f0g5 + f1g4 + f2g3 + f3g2 + f4g1 + f5g0 + f6g9_19 + f7g8_19 + f8g7_19 + f9g6_19;
        long h6 = f0g6 + f1g5_2 + f2g4 + f3g3_2 + f4g2 + f5g1_2 + f6g0 + f7g9_38 + f8g8_19 + f9g7_38;
        long h7 = f0g7 + f1g6 + f2g5 + f3g4 + f4g3 + f5g2 + f6g1 + f7g0 + f8g9_19 + f9g8_19;
        long h8 = f0g8 + f1g7_2 + f2g6 + f3g5_2 + f4g4 + f5g3_2 + f6g2 + f7g1_2 + f8g0 + f9g9_38;
        long h9 = f0g9 + f1g8 + f2g7 + f3g6 + f4g5 + f5g4 + f6g3 + f7g2 + f8g1 + f9g0;
        long carry[] = new long[10];

        carry[0] = ((h0 + (1 << 25)) >> 26);
        h1 += carry[0];
        h0 -= carry[0] << 26;
        carry[4] = ((h4 + (1 << 25)) >> 26);
        h5 += carry[4];
        h4 -= carry[4] << 26;

        carry[1] = ((h1 + (1 << 24)) >> 25);
        h2 += carry[1];
        h1 -= carry[1] << 25;
        carry[5] = ((h5 + (1 << 24)) >> 25);
        h6 += carry[5];
        h5 -= carry[5] << 25;

        carry[2] = ((h2 + (1 << 25)) >> 26);
        h3 += carry[2];
        h2 -= carry[2] << 26;
        carry[6] = ((h6 + (1 << 25)) >> 26);
        h7 += carry[6];
        h6 -= carry[6] << 26;

        carry[3] = ((h3 + (1 << 24)) >> 25);
        h4 += carry[3];
        h3 -= carry[3] << 25;
        carry[7] = ((h7 + (1 << 24)) >> 25);
        h8 += carry[7];
        h7 -= carry[7] << 25;

        carry[4] = ((h4 + (1 << 25)) >> 26);
        h5 += carry[4];
        h4 -= carry[4] << 26;
        carry[8] = ((h8 + (1 << 25)) >> 26);
        h9 += carry[8];
        h8 -= carry[8] << 26;

        carry[9] = ((h9 + (1 << 24)) >> 25);
        h0 += carry[9] * 19;
        h9 -= carry[9] << 25;

        carry[0] = ((h0 + (1 << 25)) >> 26);
        h1 += carry[0];
        h0 -= carry[0] << 26;

        retVal.arr[0] = (int) h0;
        retVal.arr[1] = (int) h1;
        retVal.arr[2] = (int) h2;
        retVal.arr[3] = (int) h3;
        retVal.arr[4] = (int) h4;
        retVal.arr[5] = (int) h5;
        retVal.arr[6] = (int) h6;
        retVal.arr[7] = (int) h7;
        retVal.arr[8] = (int) h8;
        retVal.arr[9] = (int) h9;
        return retVal;
    }

    private static long int64(long x) {
        return x;
    }

    private static FieldElement feSquare(FieldElement f) {
        return f.square();
    }

    // feInvert sets out = z^-1.
    private static FieldElement feInvert(FieldElement z) {
        FieldElement retVal = new FieldElement();
        FieldElement t0;
        FieldElement t1;
        FieldElement t2;
        FieldElement t3;
        int i;

        t0 = feSquare(z);
        for (i = 1; i < 1; i++) {
            t0 = feSquare(t0);
        }
        t1 = feSquare(t0);
        for (i = 1; i < 2; i++) {
            t1 = feSquare(t1);
        }
        t1 = feMul(z, t1);
        t0 = feMul(t0, t1);
        t2 = feSquare(t0);
        for (i = 1; i < 1; i++) {
            t2 = feSquare(t2);
        }
        t1 = feMul(t1, t2);
        t2 = feSquare(t1);
        for (i = 1; i < 5; i++) {
            t2 = feSquare(t2);
        }
        t1 = feMul(t2, t1);
        t2 = feSquare(t1);
        for (i = 1; i < 10; i++) {
            t2 = feSquare(t2);
        }
        t2 = feMul(t2, t1);
        t3 = feSquare(t2);
        for (i = 1; i < 20; i++) {
            t3 = feSquare(t3);
        }
        t2 = feMul(t3, t2);
        t2 = feSquare(t2);
        for (i = 1; i < 10; i++) {
            t2 = feSquare(t2);
        }
        t1 = feMul(t2, t1);
        t2 = feSquare(t1);
        for (i = 1; i < 50; i++) {
            t2 = feSquare(t2);
        }
        t2 = feMul(t2, t1);
        t3 = feSquare(t2);
        for (i = 1; i < 100; i++) {
            t3 = feSquare(t3);
        }
        t2 = feMul(t3, t2);
        t2 = feSquare(t2);
        for (i = 1; i < 50; i++) {
            t2 = feSquare(t2);
        }
        t1 = feMul(t2, t1);
        t1 = feSquare(t1);
        for (i = 1; i < 5; i++) {
            t1 = feSquare(t1);
        }
        return feMul(t1, t0);
    }

    // feMul121666 calculates h = f * 121666. Can overlap h with f.
    //
    // Preconditions:
    //    |f| bounded by 1.1*2^26,1.1*2^25,1.1*2^26,1.1*2^25,etc.
    //
    // Postconditions:
    //    |h| bounded by 1.1*2^25,1.1*2^24,1.1*2^25,1.1*2^24,etc.
    private static FieldElement feMul121666(FieldElement f) {
        long h0 = int64(f.arr[0]) * 121666;
        long h1 = int64(f.arr[1]) * 121666;
        long h2 = int64(f.arr[2]) * 121666;
        long h3 = int64(f.arr[3]) * 121666;
        long h4 = int64(f.arr[4]) * 121666;
        long h5 = int64(f.arr[5]) * 121666;
        long h6 = int64(f.arr[6]) * 121666;
        long h7 = int64(f.arr[7]) * 121666;
        long h8 = int64(f.arr[8]) * 121666;
        long h9 = int64(f.arr[9]) * 121666;
        long carry[] = new long[10];

        carry[9] = ((h9 + (1 << 24)) >> 25);
        h0 += carry[9] * 19;
        h9 -= carry[9] << 25;
        carry[1] = ((h1 + (1 << 24)) >> 25);
        h2 += carry[1];
        h1 -= carry[1] << 25;
        carry[3] = ((h3 + (1 << 24)) >> 25);
        h4 += carry[3];
        h3 -= carry[3] << 25;
        carry[5] = ((h5 + (1 << 24)) >> 25);
        h6 += carry[5];
        h5 -= carry[5] << 25;
        carry[7] = ((h7 + (1 << 24)) >> 25);
        h8 += carry[7];
        h7 -= carry[7] << 25;

        carry[0] = ((h0 + (1 << 25)) >> 26);
        h1 += carry[0];
        h0 -= carry[0] << 26;
        carry[2] = ((h2 + (1 << 25)) >> 26);
        h3 += carry[2];
        h2 -= carry[2] << 26;
        carry[4] = ((h4 + (1 << 25)) >> 26);
        h5 += carry[4];
        h4 -= carry[4] << 26;
        carry[6] = ((h6 + (1 << 25)) >> 26);
        h7 += carry[6];
        h6 -= carry[6] << 26;
        carry[8] = ((h8 + (1 << 25)) >> 26);
        h9 += carry[8];
        h8 -= carry[8] << 26;

        FieldElement h = new FieldElement();
        h.arr[0] = (int) (h0);
        h.arr[1] = (int) (h1);
        h.arr[2] = (int) (h2);
        h.arr[3] = (int) (h3);
        h.arr[4] = (int) (h4);
        h.arr[5] = (int) (h5);
        h.arr[6] = (int) (h6);
        h.arr[7] = (int) (h7);
        h.arr[8] = (int) (h8);
        h.arr[9] = (int) (h9);
        return h;
    }

    public static byte[] scalarMult(byte in[], byte base[]) {
        byte e[] = in.clone();
        byte out[];
        e[0] &= (byte) 248;
        e[31] &= (byte) 127;
        e[31] |= (byte) 64;

        FieldElement x1 = new FieldElement(base);
        FieldElement x2 = FieldElement.ONE();
        FieldElement x3 = new FieldElement(x1);
        FieldElement z3 = FieldElement.ONE();
        FieldElement z2 = FieldElement.ZERO(); // this isn't in the orig code

        long swap = 0;

        for (int pos = 254; pos >= 0; pos--) {
            long b = e[pos / 8] >> (pos & 7);
            b &= 1;
            swap ^= b;
            feCSwap(x2, x3, swap);
            feCSwap(z2, z3, swap);
            swap = b;

            FieldElement tmp0 = feSub(x3, z3);
            FieldElement tmp1 = feSub(x2, z2);
            x2 = feAdd(x2, z2);
            z2 = feAdd(x3, z3);
            z3 = feMul(tmp0, x2);
            z2 = feMul(z2, tmp1);
            tmp0 = feSquare(tmp1);
            tmp1 = feSquare(x2);
            x3 = feAdd(z3, z2);
            z2 = feSub(z3, z2);
            x2 = feMul(tmp1, tmp0);
            tmp1 = feSub(tmp1, tmp0);
            z2 = feSquare(z2);
            z3 = feMul121666(tmp1);
            x3 = feSquare(x3);
            tmp0 = feAdd(tmp0, z3);
            z3 = feMul(x1, z2);
            z2 = feMul(tmp1, tmp0);
        }

        feCSwap(x2, x3, swap);
        feCSwap(z2, z3, swap);

        z2 = feInvert(z2);
        x2 = feMul(x2, z2);
        out = x2.toBytes();
        return out;
    }

    public static byte[] scalarBaseMult(byte in[]) {
        return scalarMult(in, basePoint);
    }

    protected static class FieldElement {
        private long arr[] = new long[10];

        public FieldElement(long val[]) {
            for (int i = 0; i < 10; i++) {
                arr[i] = val[i];
            }
        }

        public FieldElement(FieldElement fe) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = fe.arr[i];
            }
        }

        public FieldElement(byte[] src) {
            long h0 = load4(src, 0);
            long h1 = load3(src, 4) << 6;
            long h2 = load3(src, 7) << 5;
            long h3 = load3(src, 10) << 3;
            long h4 = load3(src, 13) << 2;
            long h5 = load4(src, 16);
            long h6 = load3(src, 20) << 7;
            long h7 = load3(src, 23) << 5;
            long h8 = load3(src, 26) << 4;
            long h9 = load3(src, 29) << 2;

            long carry[] = new long[10];
            carry[9] = ((h9 + (1 << 24)) >> 25);
            h0 += carry[9] * 19;
            h9 -= carry[9] << 25;
            carry[1] = ((h1 + (1 << 24)) >> 25);
            h2 += carry[1];
            h1 -= carry[1] << 25;
            carry[3] = ((h3 + (1 << 24)) >> 25);
            h4 += carry[3];
            h3 -= carry[3] << 25;
            carry[5] = ((h5 + (1 << 24)) >> 25);
            h6 += carry[5];
            h5 -= carry[5] << 25;
            carry[7] = ((h7 + (1 << 24)) >> 25);
            h8 += carry[7];
            h7 -= carry[7] << 25;

            carry[0] = ((h0 + (1 << 25)) >> 26);
            h1 += carry[0];
            h0 -= carry[0] << 26;
            carry[2] = ((h2 + (1 << 25)) >> 26);
            h3 += carry[2];
            h2 -= carry[2] << 26;
            carry[4] = ((h4 + (1 << 25)) >> 26);
            h5 += carry[4];
            h4 -= carry[4] << 26;
            carry[6] = ((h6 + (1 << 25)) >> 26);
            h7 += carry[6];
            h6 -= carry[6] << 26;
            carry[8] = ((h8 + (1 << 25)) >> 26);
            h9 += carry[8];
            h8 -= carry[8] << 26;

            arr[0] = (int) h0;
            arr[1] = (int) h1;
            arr[2] = (int) h2;
            arr[3] = (int) h3;
            arr[4] = (int) h4;
            arr[5] = (int) h5;
            arr[6] = (int) h6;
            arr[7] = (int) h7;
            arr[8] = (int) h8;
            arr[9] = (int) h9;
        }

        public FieldElement() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = 0;
            }
        }

        // load3 reads a 24-bit, little-endian value from in.
        private static long load3(byte in[], int offset) {
            long r;
            r = (long) (0xFF & in[offset]);
            r |= (long) (0xFF & in[offset + 1]) << 8;
            r |= (long) (0xFF & in[offset + 2]) << 16;
            return r;
        }

        // load4 reads a 32-bit, little-endian value from in.
        private static long load4(byte in[], int offset) {
            long r;
            r = (long) (0xFF & in[offset]);
            r |= ((long) (0xFF & in[offset + 1])) << 8;
            r |= ((long) (0xFF & in[offset + 2])) << 16;
            r |= ((long) (0xFF & in[offset + 3])) << 24;
            return r;
        }

        public static FieldElement ZERO() {
            return new FieldElement();
        }

        public static FieldElement ONE() {
            FieldElement fe = ZERO();
            fe.arr[0] = 1;
            return fe;
        }

        @Override
        public boolean equals(Object obj) {
            FieldElement rhs = (FieldElement) obj;
            for (int i = 0; i < 10; i++) {
                if (arr[i] != rhs.arr[i]) return false;
            }
            return true;
        }

        @Override
        public String toString() {
            String s = "[";
            for (int i = 0; i < arr.length; i++) {
                s += arr[i];
                s += " ";
            }
            s = s.substring(0, s.length() - 1);
            s += "]";
            return s;
        }

        private byte[] toBytes() {
            byte retVal[] = new byte[32];
            long carry[] = new long[10];

            long q = (19 * arr[9] + (1 << 24)) >> 25;
            q = (arr[0] + q) >> 26;
            q = (arr[1] + q) >> 25;
            q = (arr[2] + q) >> 26;
            q = (arr[3] + q) >> 25;
            q = (arr[4] + q) >> 26;
            q = (arr[5] + q) >> 25;
            q = (arr[6] + q) >> 26;
            q = (arr[7] + q) >> 25;
            q = (arr[8] + q) >> 26;
            q = (arr[9] + q) >> 25;

            arr[0] += 19 * q;
            carry[0] = arr[0] >> 26;
            arr[1] += carry[0];
            arr[0] -= carry[0] << 26;
            carry[1] = arr[1] >> 25;
            arr[2] += carry[1];
            arr[1] -= carry[1] << 25;
            carry[2] = arr[2] >> 26;
            arr[3] += carry[2];
            arr[2] -= carry[2] << 26;
            carry[3] = arr[3] >> 25;
            arr[4] += carry[3];
            arr[3] -= carry[3] << 25;
            carry[4] = arr[4] >> 26;
            arr[5] += carry[4];
            arr[4] -= carry[4] << 26;
            carry[5] = arr[5] >> 25;
            arr[6] += carry[5];
            arr[5] -= carry[5] << 25;
            carry[6] = arr[6] >> 26;
            arr[7] += carry[6];
            arr[6] -= carry[6] << 26;
            carry[7] = arr[7] >> 25;
            arr[8] += carry[7];
            arr[7] -= carry[7] << 25;
            carry[8] = arr[8] >> 26;
            arr[9] += carry[8];
            arr[8] -= carry[8] << 26;
            carry[9] = arr[9] >> 25;
            arr[9] -= carry[9] << 25;

            retVal[0] = (byte) (arr[0] >> 0);
            retVal[1] = (byte) (arr[0] >> 8);
            retVal[2] = (byte) (arr[0] >> 16);
            retVal[3] = (byte) ((arr[0] >> 24) | (arr[1] << 2));
            retVal[4] = (byte) (arr[1] >> 6);
            retVal[5] = (byte) (arr[1] >> 14);
            retVal[6] = (byte) ((arr[1] >> 22) | (arr[2] << 3));
            retVal[7] = (byte) (arr[2] >> 5);
            retVal[8] = (byte) (arr[2] >> 13);
            retVal[9] = (byte) ((arr[2] >> 21) | (arr[3] << 5));
            retVal[10] = (byte) (arr[3] >> 3);
            retVal[11] = (byte) (arr[3] >> 11);
            retVal[12] = (byte) ((arr[3] >> 19) | (arr[4] << 6));
            retVal[13] = (byte) (arr[4] >> 2);
            retVal[14] = (byte) (arr[4] >> 10);
            retVal[15] = (byte) (arr[4] >> 18);
            retVal[16] = (byte) (arr[5] >> 0);
            retVal[17] = (byte) (arr[5] >> 8);
            retVal[18] = (byte) (arr[5] >> 16);
            retVal[19] = (byte) ((arr[5] >> 24) | (arr[6] << 1));
            retVal[20] = (byte) (arr[6] >> 7);
            retVal[21] = (byte) (arr[6] >> 15);
            retVal[22] = (byte) ((arr[6] >> 23) | (arr[7] << 3));
            retVal[23] = (byte) (arr[7] >> 5);
            retVal[24] = (byte) (arr[7] >> 13);
            retVal[25] = (byte) ((arr[7] >> 21) | (arr[8] << 4));
            retVal[26] = (byte) (arr[8] >> 4);
            retVal[27] = (byte) (arr[8] >> 12);
            retVal[28] = (byte) ((arr[8] >> 20) | (arr[9] << 6));
            retVal[29] = (byte) (arr[9] >> 2);
            retVal[30] = (byte) (arr[9] >> 10);
            retVal[31] = (byte) (arr[9] >> 18);
            return retVal;
        }

        public FieldElement square() {
            long f0 = arr[0];
            long f1 = arr[1];
            long f2 = arr[2];
            long f3 = arr[3];
            long f4 = arr[4];
            long f5 = arr[5];
            long f6 = arr[6];
            long f7 = arr[7];
            long f8 = arr[8];
            long f9 = arr[9];
            long f0_2 = 2 * f0;
            long f1_2 = 2 * f1;
            long f2_2 = 2 * f2;
            long f3_2 = 2 * f3;
            long f4_2 = 2 * f4;
            long f5_2 = 2 * f5;
            long f6_2 = 2 * f6;
            long f7_2 = 2 * f7;
            long f5_38 = 38 * f5; // 1.31*2^30
            long f6_19 = 19 * f6; // 1.31*2^30
            long f7_38 = 38 * f7; // 1.31*2^30
            long f8_19 = 19 * f8; // 1.31*2^30
            long f9_38 = 38 * f9; // 1.31*2^30
            long f0f0 = int64(f0) * int64(f0);
            long f0f1_2 = int64(f0_2) * int64(f1);
            long f0f2_2 = int64(f0_2) * int64(f2);
            long f0f3_2 = int64(f0_2) * int64(f3);
            long f0f4_2 = int64(f0_2) * int64(f4);
            long f0f5_2 = int64(f0_2) * int64(f5);
            long f0f6_2 = int64(f0_2) * int64(f6);
            long f0f7_2 = int64(f0_2) * int64(f7);
            long f0f8_2 = int64(f0_2) * int64(f8);
            long f0f9_2 = int64(f0_2) * int64(f9);
            long f1f1_2 = int64(f1_2) * int64(f1);
            long f1f2_2 = int64(f1_2) * int64(f2);
            long f1f3_4 = int64(f1_2) * int64(f3_2);
            long f1f4_2 = int64(f1_2) * int64(f4);
            long f1f5_4 = int64(f1_2) * int64(f5_2);
            long f1f6_2 = int64(f1_2) * int64(f6);
            long f1f7_4 = int64(f1_2) * int64(f7_2);
            long f1f8_2 = int64(f1_2) * int64(f8);
            long f1f9_76 = int64(f1_2) * int64(f9_38);
            long f2f2 = int64(f2) * int64(f2);
            long f2f3_2 = int64(f2_2) * int64(f3);
            long f2f4_2 = int64(f2_2) * int64(f4);
            long f2f5_2 = int64(f2_2) * int64(f5);
            long f2f6_2 = int64(f2_2) * int64(f6);
            long f2f7_2 = int64(f2_2) * int64(f7);
            long f2f8_38 = int64(f2_2) * int64(f8_19);
            long f2f9_38 = int64(f2) * int64(f9_38);
            long f3f3_2 = int64(f3_2) * int64(f3);
            long f3f4_2 = int64(f3_2) * int64(f4);
            long f3f5_4 = int64(f3_2) * int64(f5_2);
            long f3f6_2 = int64(f3_2) * int64(f6);
            long f3f7_76 = int64(f3_2) * int64(f7_38);
            long f3f8_38 = int64(f3_2) * int64(f8_19);
            long f3f9_76 = int64(f3_2) * int64(f9_38);
            long f4f4 = int64(f4) * int64(f4);
            long f4f5_2 = int64(f4_2) * int64(f5);
            long f4f6_38 = int64(f4_2) * int64(f6_19);
            long f4f7_38 = int64(f4) * int64(f7_38);
            long f4f8_38 = int64(f4_2) * int64(f8_19);
            long f4f9_38 = int64(f4) * int64(f9_38);
            long f5f5_38 = int64(f5) * int64(f5_38);
            long f5f6_38 = int64(f5_2) * int64(f6_19);
            long f5f7_76 = int64(f5_2) * int64(f7_38);
            long f5f8_38 = int64(f5_2) * int64(f8_19);
            long f5f9_76 = int64(f5_2) * int64(f9_38);
            long f6f6_19 = int64(f6) * int64(f6_19);
            long f6f7_38 = int64(f6) * int64(f7_38);
            long f6f8_38 = int64(f6_2) * int64(f8_19);
            long f6f9_38 = int64(f6) * int64(f9_38);
            long f7f7_38 = int64(f7) * int64(f7_38);
            long f7f8_38 = int64(f7_2) * int64(f8_19);
            long f7f9_76 = int64(f7_2) * int64(f9_38);
            long f8f8_19 = int64(f8) * int64(f8_19);
            long f8f9_38 = int64(f8) * int64(f9_38);
            long f9f9_38 = int64(f9) * int64(f9_38);
            long h0 = f0f0 + f1f9_76 + f2f8_38 + f3f7_76 + f4f6_38 + f5f5_38;
            long h1 = f0f1_2 + f2f9_38 + f3f8_38 + f4f7_38 + f5f6_38;
            long h2 = f0f2_2 + f1f1_2 + f3f9_76 + f4f8_38 + f5f7_76 + f6f6_19;
            long h3 = f0f3_2 + f1f2_2 + f4f9_38 + f5f8_38 + f6f7_38;
            long h4 = f0f4_2 + f1f3_4 + f2f2 + f5f9_76 + f6f8_38 + f7f7_38;
            long h5 = f0f5_2 + f1f4_2 + f2f3_2 + f6f9_38 + f7f8_38;
            long h6 = f0f6_2 + f1f5_4 + f2f4_2 + f3f3_2 + f7f9_76 + f8f8_19;
            long h7 = f0f7_2 + f1f6_2 + f2f5_2 + f3f4_2 + f8f9_38;
            long h8 = f0f8_2 + f1f7_4 + f2f6_2 + f3f5_4 + f4f4 + f9f9_38;
            long h9 = f0f9_2 + f1f8_2 + f2f7_2 + f3f6_2 + f4f5_2;
            long carry[] = new long[10];

            carry[0] = ((h0 + (1 << 25)) >> 26);
            h1 += carry[0];
            h0 -= carry[0] << 26;
            carry[4] = ((h4 + (1 << 25)) >> 26);
            h5 += carry[4];
            h4 -= carry[4] << 26;

            carry[1] = ((h1 + (1 << 24)) >> 25);
            h2 += carry[1];
            h1 -= carry[1] << 25;
            carry[5] = ((h5 + (1 << 24)) >> 25);
            h6 += carry[5];
            h5 -= carry[5] << 25;

            carry[2] = ((h2 + (1 << 25)) >> 26);
            h3 += carry[2];
            h2 -= carry[2] << 26;
            carry[6] = ((h6 + (1 << 25)) >> 26);
            h7 += carry[6];
            h6 -= carry[6] << 26;

            carry[3] = ((h3 + (1 << 24)) >> 25);
            h4 += carry[3];
            h3 -= carry[3] << 25;
            carry[7] = ((h7 + (1 << 24)) >> 25);
            h8 += carry[7];
            h7 -= carry[7] << 25;

            carry[4] = ((h4 + (1 << 25)) >> 26);
            h5 += carry[4];
            h4 -= carry[4] << 26;
            carry[8] = ((h8 + (1 << 25)) >> 26);
            h9 += carry[8];
            h8 -= carry[8] << 26;

            carry[9] = ((h9 + (1 << 24)) >> 25);
            h0 += carry[9] * 19;
            h9 -= carry[9] << 25;

            carry[0] = ((h0 + (1 << 25)) >> 26);
            h1 += carry[0];
            h0 -= carry[0] << 26;

            FieldElement h = new FieldElement();
            h.arr[0] = (int) (h0);
            h.arr[1] = (int) (h1);
            h.arr[2] = (int) (h2);
            h.arr[3] = (int) (h3);
            h.arr[4] = (int) (h4);
            h.arr[5] = (int) (h5);
            h.arr[6] = (int) (h6);
            h.arr[7] = (int) (h7);
            h.arr[8] = (int) (h8);
            h.arr[9] = (int) (h9);
            return h;
        }
    }
}
